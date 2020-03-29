package com.pugz.bambooblocks.core;

import com.pugz.bambooblocks.client.render.BambooBoatRenderer;
import com.pugz.bambooblocks.core.registry.BambooBlocks;
import com.pugz.bambooblocks.core.registry.BambooEntities;
import com.pugz.bambooblocks.core.registry.BambooItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("bambooblocks")
public class BambooBlocksMain {

    public BambooBlocksMain() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        BambooItems.ITEMS.register(modEventBus);
    	BambooBlocks.BLOCKS.register(modEventBus);
    	BambooEntities.ENTITIES.register(modEventBus);
    	
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> this::initSetupClient);
    }
    
    public void initSetupClient()
    {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        BambooBlocks.registerFlammables();
        BambooBlocks.registerCompostables();
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
    	BambooBlocks.registerRenderLayers();
        RenderingRegistry.registerEntityRenderingHandler(BambooEntities.BOAT.get(), BambooBoatRenderer::new);
    }
}