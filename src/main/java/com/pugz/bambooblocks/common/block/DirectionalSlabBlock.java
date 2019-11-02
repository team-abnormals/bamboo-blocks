package com.pugz.bambooblocks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class DirectionalSlabBlock extends SlabBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public DirectionalSlabBlock(Block.Properties properties) {
        super(properties);
        setDefaultState(getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TYPE, AXIS, WATERLOGGED);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        BlockState state = context.getWorld().getBlockState(pos);
        Direction face = context.getNearestLookingDirection();
        if (state.getBlock() == this) {
            return (BlockState)((BlockState)state.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false).with(AXIS, state.get(AXIS));
        } else {
            IFluidState fluidState = context.getWorld().getFluidState(pos);
            boolean waterlogged = fluidState.getFluid() == Fluids.WATER;
            if (context.getHitVec().y - (double) pos.getY() <= 0.5D) {
                return getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, waterlogged).with(AXIS, context.getNearestLookingDirection().getAxis());
            } else {
                return getDefaultState().with(TYPE, SlabType.TOP).with(WATERLOGGED, waterlogged).with(AXIS, context.getNearestLookingDirection().getAxis());
            }
        }
    }
}