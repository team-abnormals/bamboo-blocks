package com.pugz.bambooblocks.client.render;

import com.pugz.bambooblocks.common.block.ChestBlock;
import com.pugz.bambooblocks.common.tileentity.TrappedBambooChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class TrappedBambooChestTileEntityRenderer extends ChestTileEntityRenderer<TrappedBambooChestTileEntity> {
    private TrappedBambooChestTileEntity tile;
    public static ResourceLocation forceNormal = new ResourceLocation("bambooblocks", "textures/model/chest/bamboo_chest_normal.png");
    public static ResourceLocation forceDouble = new ResourceLocation("bambooblocks", "textures/model/chest/bamboo_chest_double.png");

    @Override
    public void render(TrappedBambooChestTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        tile = tileEntityIn;
        super.render(tileEntityIn, x, y, z, partialTicks, destroyStage);
    }

    @Override
    protected void bindTexture(ResourceLocation location) {
        boolean isDouble = location.getPath().contains("double");
        if(tile != null && tile.hasWorld()) {
            if(location.getPath().contains("normal")) {
                Block block = tile.getBlockState().getBlock();
                if(block instanceof ChestBlock) {
                    location = isDouble ? forceDouble : forceNormal;
                }
            }
        }
        else {
            ResourceLocation forced = isDouble ? forceDouble : forceNormal;
            if(forced != null) {
                location = forced;
            }
        }
        super.bindTexture(location);
    }
}