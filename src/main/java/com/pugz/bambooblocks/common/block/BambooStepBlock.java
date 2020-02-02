package com.pugz.bambooblocks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.shapes.VoxelShape;

public class BambooStepBlock extends Block {

    public static DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    //public static EnumProperty<Side> SIDE = EnumProperty.create("side", Side.class);
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public BambooStepBlock(Block.Properties properties) {
        super(properties);
        //setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(SIDE, Side.TOP_LEFT));
    }

    /*

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch (state.get(SIDE)) {
            case TOP_LEFT:
                break;
            case TOP_RIGHT:
                break;
            case MIDDLE_LEFT:
                break;
            case MIDDLE_RIGHT:
                break;
            case BOTTOM_LEFT:
                break;
            case BOTTOM_RIGHT:
                break;
        }
        return SHAPE;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockPos lvt_2_1_ = p_196258_1_.getPos();
        BlockState lvt_3_1_ = p_196258_1_.getWorld().getBlockState(lvt_2_1_);
        if (lvt_3_1_.getBlock() == this) {
            return (BlockState)((BlockState)lvt_3_1_.with(, SlabType.DOUBLE)).with(WATERLOGGED, false);
        } else {
            IFluidState lvt_4_1_ = p_196258_1_.getWorld().getFluidState(lvt_2_1_);
            BlockState lvt_5_1_ = (BlockState)((BlockState)this.getDefaultState().with(, SlabType.BOTTOM)).with(WATERLOGGED, lvt_4_1_.getFluid() == Fluids.WATER);
            Direction lvt_6_1_ = p_196258_1_.getFace();
            return lvt_6_1_ != Direction.DOWN && (lvt_6_1_ == Direction.UP || p_196258_1_.getHitVec().y - (double)lvt_2_1_.getY() <= 0.5D) ? lvt_5_1_ : (BlockState)lvt_5_1_.with(, SlabType.TOP);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        double xPos = context.getHitVec().x - (double)pos.getX();
        double yPos = context.getHitVec().y - (double)pos.getY();
        double zPos = context.getHitVec().z - (double)pos.getZ();
        if (yPos >= 0.66D && yPos <= 1.0D) {
            if (state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST) {
            }
            else if (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH) {
            }
        }
        else if (yPos >= 0.33D && yPos <= 0.66D) {
            if (state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST) {
            }
            else if (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH) {
            }
        }
        else if (yPos >= 0.0D && yPos <= 0.33D) {
            if (state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST) {
            }
            else if (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH) {
            }
        }
        return getDefaultState();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SIDE);
    }

    public enum Side implements IStringSerializable {

        TOP_LEFT("top_left"),
        TOP_RIGHT("top_left"),
        MIDDLE_LEFT("middle_left"),
        MIDDLE_RIGHT("middle_right"),
        BOTTOM_LEFT("bottom_left"),
        BOTTOM_RIGHT("bottom_right");

        private String name;

        private Side(String nameIn) {
            name = nameIn;
        }

        public String getName() {
            return name;
        }
    }
    */
}