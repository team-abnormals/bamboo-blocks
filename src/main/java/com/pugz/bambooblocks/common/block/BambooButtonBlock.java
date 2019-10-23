package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.common.config.BambooBlocksConfig;
import com.pugz.bambooblocks.core.util.BlockProperties;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IWorldReader;

public class BambooButtonBlock extends AbstractButtonBlock {

    public BambooButtonBlock() {
        super(true, BlockProperties.BAMBOO_BUTTON);
    }

    @Override
    public int tickRate(IWorldReader worldIn) {
        return BambooBlocksConfig.bambooButtonLength * 20;
    }

    protected SoundEvent getSoundEvent(boolean powered) {
        return powered ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }
}