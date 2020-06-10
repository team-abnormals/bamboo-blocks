package com.pugz.bambooblocks.common.block;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pugz.bambooblocks.core.BambooBlocksRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BambooWallTorchBlock extends WallTorchBlock {

	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 16.0D, 16.0D), 
			Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 16.0D, 5.0D),
			Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 16.0D, 10.5D), 
			Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 16.0D, 10.5D)));

	public BambooWallTorchBlock() {
		super(BambooBlocksRegistry.Properties.BAMBOO_TORCH);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getShapeByState(state);
	}

	public static VoxelShape getShapeByState(BlockState state) {
		return SHAPES.get(state.get(HORIZONTAL_FACING));
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		Direction direction = (Direction) state.get(HORIZONTAL_FACING);
		BlockPos oppositePos = pos.offset(direction.getOpposite());
		BlockState oppositeState = world.getBlockState(oppositePos);
		return oppositeState.isSolidSide(world, oppositePos, direction) || oppositeState.getBlock() instanceof LeavesBlock;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		Direction direction = stateIn.get(HORIZONTAL_FACING);
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.8D;
		double d2 = (double) pos.getZ() + 0.5D;
		Direction direction1 = direction.getOpposite();
		worldIn.addParticle(ParticleTypes.SMOKE, d0 + 0.18D * (double) direction1.getXOffset(), d1 + 0.22D, d2 + 0.18D * (double) direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.FLAME, d0 + 0.18D * (double) direction1.getXOffset(), d1 + 0.22D, d2 + 0.18D * (double) direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
	}
}