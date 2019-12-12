package com.pugz.bambooblocks.core.proxy;

import com.pugz.bambooblocks.client.render.BambooBoatRenderer;
import com.pugz.bambooblocks.common.entity.BambooBoatEntity;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {
    @Override
    public void init() {
        RenderingRegistry.registerEntityRenderingHandler(BambooBoatEntity.class, BambooBoatRenderer::new);
        if (ModList.get().isLoaded("quark")) {
            //ClientRegistry.bindTileEntitySpecialRenderer(BambooChestTileEntity.class, new BambooChestTileEntityRenderer());
            //ClientRegistry.bindTileEntitySpecialRenderer(TrappedBambooChestTileEntity.class, new TrappedBambooChestTileEntityRenderer());
        }
    }
}