package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.common.tileentity.BambooChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ChestBlock extends net.minecraft.block.ChestBlock {
    public ChestBlock(Block.Properties properties) {
        super(properties);
    }

    public TileEntity createNewTileEntity(IBlockReader reader) {
        return new BambooChestTileEntity();
    }
}