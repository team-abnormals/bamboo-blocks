package com.minecraftabnormals.bamboo_blocks.common.block;

import static net.minecraft.block.BambooBlock.PROPERTY_BAMBOO_LEAVES;

import java.util.Random;

import javax.annotation.Nullable;

import com.teamabnormals.abnormals_core.core.utils.ItemStackUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class BambooTorchBlock extends TorchBlock {

	protected static final VoxelShape TORCH = Block.makeCuboidShape(5.5D, 0.0D, 5.5D, 10.5D, 14.0D, 10.5D);
	protected static final VoxelShape TORCH_LARGE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);
	
	protected static final IntegerProperty SIZE = IntegerProperty.create("size", 0, 1);
	protected static final BooleanProperty LEAVES = BooleanProperty.create("leaves");

	public BambooTorchBlock(Block.Properties properties, IParticleData particle) {
		super(properties, particle);
		this.setDefaultState(this.getStateContainer().getBaseState().with(SIZE, 0).with(LEAVES, false));
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (ItemStackUtils.isInGroup(this.asItem(), group)) {
			int targetIndex = ItemStackUtils.findIndexOfItem(Items.TORCH, items);
			if (targetIndex != -1) {
				items.add(targetIndex + 1, new ItemStack(this));
			} else {
				super.fillItemGroup(group, items);
			}
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SIZE, LEAVES);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP) || downState.getBlock() instanceof LeavesBlock || downState.getBlock() instanceof BambooBlock;
	}

	@Override
    public AbstractBlock.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            Vector3d vec3d = state.getOffset(worldIn, pos);
			return state.get(SIZE) == 0 ? TORCH.withOffset(vec3d.x, vec3d.y, vec3d.z) : TORCH_LARGE.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof BambooBlock) {
			return state.get(SIZE) == 0 ? TORCH : TORCH_LARGE;
		}
		return VoxelShapes.empty();
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState downState = world.getBlockState(pos.down());
		if (downState.getBlock() instanceof BambooBlock) {
			return this.getDefaultState()
					.with(LEAVES, downState.get(PROPERTY_BAMBOO_LEAVES) != BambooLeaves.NONE || downState.get(BambooBlock.PROPERTY_AGE) == 0)
					.with(SIZE, downState.get(BambooBlock.PROPERTY_AGE));
		} else if (downState.getBlock() instanceof BambooSaplingBlock && context.getFace() == Direction.UP) {
			world.setBlockState(pos.down(), Blocks.BAMBOO.getDefaultState(), 3);
			return this.getDefaultState();
		}
		return this.getDefaultState();
	}

	public IParticleData getFlameParticle() {
		if(this.field_235607_e_ == null) {
			ParticleType<?> enderFlame = ForgeRegistries.PARTICLE_TYPES.getValue(new ResourceLocation("endergetic", "ender_flame"));
			return enderFlame != null ? (IParticleData) enderFlame : ParticleTypes.SOUL_FIRE_FLAME;
		}
		return this.field_235607_e_;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		Vector3d offset = state.getOffset(worldIn.getBlockReader(pos.getX(), pos.getY()), pos);
		double d0 = (double) pos.getX() + 0.5D + offset.getX();
		double d1 = (double) pos.getY() + 0.9D + offset.getY();
		double d2 = (double) pos.getZ() + 0.5D + offset.getZ();
		worldIn.addParticle(ParticleTypes.SMOKE,d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(this.getFlameParticle(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}