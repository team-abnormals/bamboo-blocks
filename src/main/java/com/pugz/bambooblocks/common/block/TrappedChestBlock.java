package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.common.tileentity.TrappedBambooChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class TrappedChestBlock extends net.minecraft.block.TrappedChestBlock {
    public TrappedChestBlock(Properties properties) {
        super(properties);
    }

    public TileEntity createNewTileEntity(IBlockReader reader) {
        return new TrappedBambooChestTileEntity();
    }
}