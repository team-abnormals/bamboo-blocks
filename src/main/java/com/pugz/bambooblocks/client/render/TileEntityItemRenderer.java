package com.pugz.bambooblocks.client.render;

import com.pugz.bambooblocks.common.block.ChestBlock;
import com.pugz.bambooblocks.common.block.TrappedChestBlock;
import com.pugz.bambooblocks.common.tileentity.BambooChestTileEntity;
import com.pugz.bambooblocks.common.tileentity.TrappedBambooChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileEntityItemRenderer extends ItemStackTileEntityRenderer {
    private static final BambooChestTileEntity CHEST = new BambooChestTileEntity();
    private static final TrappedBambooChestTileEntity TRAPPED_CHEST = new TrappedBambooChestTileEntity();

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        Item item = itemStackIn.getItem();
        if (Block.getBlockFromItem(item) instanceof ChestBlock) {
            TileEntityRendererDispatcher.instance.renderAsItem(CHEST);
        }
        else if(Block.getBlockFromItem(item) instanceof TrappedChestBlock) {
            TileEntityRendererDispatcher.instance.renderAsItem(TRAPPED_CHEST);
        }
    }
}