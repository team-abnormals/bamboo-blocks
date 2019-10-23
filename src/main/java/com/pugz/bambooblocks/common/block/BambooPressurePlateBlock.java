package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.common.config.BambooBlocksConfig;
import com.pugz.bambooblocks.core.util.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.List;

public class BambooPressurePlateBlock extends AbstractPressurePlateBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public BambooPressurePlateBlock() {
        super(BlockProperties.BAMBOO_PRESSURE_PLATE);
        setDefaultState(stateContainer.getBaseState().with(POWERED, Boolean.valueOf(false)));
    }

    protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list;
        switch(getSensitivityFromConfig()) {
            case ALL:
                list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);
                break;
            case MOBS:
                list = worldIn.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
                break;
            case PLAYER:
                list = worldIn.getEntitiesWithinAABB(PlayerEntity.class, axisalignedbb);
                break;
            default:
                return 0;
        }
        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.doesEntityNotTriggerPressurePlate()) {
                    return 15;
                }
            }
        }
        return 0;
    }

    protected void playClickOnSound(IWorld worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
    }

    protected void playClickOffSound(IWorld worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
    }

    protected int getRedstoneStrength(BlockState state) {
        return state.get(POWERED) ? 15 : 0;
    }

    protected BlockState setRedstoneStrength(BlockState state, int strength) {
        return state.with(POWERED, strength > 0);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos downPos = pos.down();
        return func_220064_c(worldIn, downPos) || func_220055_a(worldIn, downPos, Direction.UP) || worldIn.getBlockState(downPos).getBlock() instanceof LeavesBlock;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    public Sensitivity getSensitivityFromConfig() {
        switch(BambooBlocksConfig.bambooPressurePlateSensitivity) {
            case "mobs":
                return Sensitivity.MOBS;
            case "player":
                return Sensitivity.PLAYER;
            case "humanoids":
                return Sensitivity.HUMANOIDS;
            default:
                return Sensitivity.ALL;
        }
    }

    public enum Sensitivity {
        ALL,
        MOBS,
        PLAYER,
        HUMANOIDS
    }
}