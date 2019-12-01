package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.client.render.BambooChestTileEntityRenderer;
import com.pugz.bambooblocks.common.tileentity.BambooChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChestBlock extends net.minecraft.block.ChestBlock {

    public ChestBlock(Block.Properties properties) {
        super(properties);
    }

    public TileEntity createNewTileEntity(IBlockReader reader) {
        return new BambooChestTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    public static void setTEISR(Item.Properties properties, ResourceLocation modelNormal, ResourceLocation modelDouble) {
        properties.setTEISR(() -> () -> new ItemStackTileEntityRenderer() {
            private final TileEntity tile = new BambooChestTileEntity();

            @Override
            public void renderByItem(ItemStack itemStackIn) {
                BambooChestTileEntityRenderer.forceNormal = modelNormal;
                BambooChestTileEntityRenderer.forceDouble = modelDouble;
                TileEntityRendererDispatcher.instance.renderAsItem(tile);
            }
        });
    }
}