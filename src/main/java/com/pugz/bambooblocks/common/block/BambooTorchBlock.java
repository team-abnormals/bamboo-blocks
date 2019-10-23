package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.core.registry.BlockRegistry;
import com.pugz.bambooblocks.core.util.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import java.util.Random;

import static net.minecraft.block.BambooBlock.PROPERTY_BAMBOO_LEAVES;

public class BambooTorchBlock extends TorchBlock {

    protected static final VoxelShape TORCH = Block.makeCuboidShape(5.5D, 0.0D, 5.5D, 10.5D, 14.0D, 10.5D);
    protected static final VoxelShape TORCH_LARGE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);
    protected static final IntegerProperty SIZE = IntegerProperty.create("size", 0, 2);

    public BambooTorchBlock() {
        super(BlockProperties.BAMBOO_TORCH);
        setDefaultState(getDefaultState().with(SIZE, 0));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(SIZE);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState downState = worldIn.getBlockState(pos.down());
        return func_220055_a(worldIn, pos.down(), Direction.UP) || downState.getBlock() instanceof LeavesBlock || downState.getBlock() instanceof BambooBlock;
    }

    @Override
    public Vec3d getOffset(BlockState state, IBlockReader world, BlockPos pos) {
        BlockState downState = world.getBlockState(pos.down());
        Block.OffsetType block$offsettype = getOffsetType();
        if (downState.getBlock() instanceof BambooBlock) {
            long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
            return new Vec3d(((double)((float)(i & 15L) / 15.0F) - 0.5D) * 0.5D, block$offsettype == Block.OffsetType.XYZ ? ((double)((float)(i >> 4 & 15L) / 15.0F) - 1.0D) * 0.2D : 0.0D, ((double)((float)(i >> 8 & 15L) / 15.0F) - 0.5D) * 0.5D);
        }
        return Vec3d.ZERO;
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        BlockState downState = worldIn.getBlockState(pos.down());
        if (downState.getBlock() instanceof BambooBlock) {
            Vec3d vec3d = downState.getOffset(worldIn, pos);
            return state.get(SIZE) < 2 ? TORCH.withOffset(vec3d.x, vec3d.y, vec3d.z) : TORCH_LARGE.withOffset(vec3d.x, vec3d.y, vec3d.z);
        }
        else {
            return state.get(SIZE) < 2 ? TORCH : TORCH_LARGE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (worldIn.getBlockState(pos.down()).getBlock() instanceof BambooBlock) {
            return state.get(SIZE) < 2 ? TORCH : TORCH_LARGE;
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
            return getDefaultState().with(SIZE, getSizeByBambooLeavesSize(downState.get(PROPERTY_BAMBOO_LEAVES)));
        }
        else if (downState.getBlock() instanceof BambooSaplingBlock && context.getFace() == Direction.UP) {
            world.setBlockState(pos.down(), Blocks.BAMBOO.getDefaultState(), 2);
            return getDefaultState();
        }
        return getDefaultState();
    }

    private int getSizeByBambooLeavesSize(BambooLeaves leaves) {
        switch (leaves) {
            case NONE:
                return 0;
            case SMALL:
                return 1;
            case LARGE:
                return 2;
        }
        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.9D;
        double d2 = (double) pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, d0 + getOffset(state, worldIn, pos).x, d1, d2 + getOffset(state, worldIn, pos).z, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(ParticleTypes.FLAME, d0 + getOffset(state, worldIn, pos).x, d1, d2 + getOffset(state, worldIn, pos).z, 0.0D, 0.0D, 0.0D);
    }
}