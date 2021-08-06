package com.minecraftabnormals.bamboo_blocks.common.block;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import net.minecraft.block.*;
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

import javax.annotation.Nullable;
import java.util.Random;

public class BambooTorchBlock extends TorchBlock {
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.TORCH);

	protected static final VoxelShape TORCH = Block.box(5.5D, 0.0D, 5.5D, 10.5D, 14.0D, 10.5D);
	protected static final VoxelShape TORCH_LARGE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);

	protected static final IntegerProperty SIZE = IntegerProperty.create("size", 0, 1);
	protected static final BooleanProperty LEAVES = BooleanProperty.create("leaves");

	public BambooTorchBlock(AbstractBlock.Properties properties, IParticleData particle) {
		super(properties, particle);
		this.registerDefaultState(this.getStateDefinition().any().setValue(SIZE, 0).setValue(LEAVES, false));
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SIZE, LEAVES);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		return canSupportCenter(worldIn, pos.below(), Direction.UP) || downState.getBlock() instanceof LeavesBlock || downState.getBlock() instanceof BambooBlock;
	}

	@Override
	public AbstractBlock.OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.XZ;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vector3d vec3d = state.getOffset(worldIn, pos);
		return state.getValue(SIZE) == 0 ? TORCH.move(vec3d.x, vec3d.y, vec3d.z) : TORCH_LARGE.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (worldIn.getBlockState(pos.below()).getBlock() instanceof BambooBlock) {
			return state.getValue(SIZE) == 0 ? TORCH : TORCH_LARGE;
		}
		return VoxelShapes.empty();
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState downState = world.getBlockState(pos.below());
		if (downState.getBlock() instanceof BambooBlock) {
			return this.defaultBlockState()
					.setValue(LEAVES, downState.getValue(BambooBlock.LEAVES) != BambooLeaves.NONE || downState.getValue(BambooBlock.AGE) == 0)
					.setValue(SIZE, downState.getValue(BambooBlock.AGE));
		} else if (downState.getBlock() instanceof BambooSaplingBlock && context.getClickedFace() == Direction.UP) {
			world.setBlock(pos.below(), Blocks.BAMBOO.defaultBlockState(), 3);
			return this.defaultBlockState();
		}
		return this.defaultBlockState();
	}

	public IParticleData getFlameParticle() {
		if (this.flameParticle == null) {
			ParticleType<?> enderFlame = ForgeRegistries.PARTICLE_TYPES.getValue(new ResourceLocation("endergetic", "ender_flame"));
			return enderFlame != null ? (IParticleData) enderFlame : ParticleTypes.SOUL_FIRE_FLAME;
		}
		return this.flameParticle;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		Vector3d offset = state.getOffset(worldIn.getChunkForCollisions(pos.getX(), pos.getY()), pos);
		double d0 = (double) pos.getX() + 0.5D + offset.x();
		double d1 = (double) pos.getY() + 0.9D + offset.y();
		double d2 = (double) pos.getZ() + 0.5D + offset.z();
		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(this.getFlameParticle(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}