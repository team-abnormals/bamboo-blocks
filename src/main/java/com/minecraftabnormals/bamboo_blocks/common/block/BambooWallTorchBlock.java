package com.minecraftabnormals.bamboo_blocks.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Random;

public class BambooWallTorchBlock extends WallTorchBlock {
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 16.0D, 16.0D),
			Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 16.0D, 5.0D),
			Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 16.0D, 10.5D),
			Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 16.0D, 10.5D)));

	public BambooWallTorchBlock(AbstractBlock.Properties properties, IParticleData particle) {
		super(properties, particle);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getShapeByState(state);
	}

	public static VoxelShape getShapeByState(BlockState state) {
		return SHAPES.get(state.getValue(FACING));
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos oppositePos = pos.relative(direction.getOpposite());
		BlockState oppositeState = world.getBlockState(oppositePos);
		return oppositeState.isFaceSturdy(world, oppositePos, direction) || oppositeState.getBlock() instanceof LeavesBlock;
	}

	public IParticleData getFlameParticle() {
		if (this.flameParticle == null) {
			ParticleType<?> enderFlame = ForgeRegistries.PARTICLE_TYPES.getValue(new ResourceLocation("endergetic", "ender_flame"));
			return enderFlame != null ? (IParticleData) enderFlame : ParticleTypes.SOUL_FIRE_FLAME;
		}
		return this.flameParticle;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		Direction direction = stateIn.getValue(FACING);
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.8D;
		double d2 = (double) pos.getZ() + 0.5D;
		Direction direction1 = direction.getOpposite();
		worldIn.addParticle(ParticleTypes.SMOKE, d0 + 0.18D * (double) direction1.getStepX(), d1 + 0.22D, d2 + 0.18D * (double) direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(this.getFlameParticle(), d0 + 0.18D * (double) direction1.getStepX(), d1 + 0.22D, d2 + 0.18D * (double) direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
	}
}