package com.pugz.bambooblocks.core;

import com.pugz.bambooblocks.client.render.BambooBoatRenderer;
import com.pugz.bambooblocks.common.config.BambooBlocksConfig;
import com.pugz.bambooblocks.common.entity.BambooBoatEntity;
import com.pugz.bambooblocks.core.registry.BlockRegistry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("bambooblocks")
public class BambooBlocks {

    public BambooBlocks() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> this::initSetupClient);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BambooBlocksConfig.COMMON_CONFIG);
        BambooBlocksConfig.loadConfig(BambooBlocksConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("bambooblocks-common.toml"));
    }
    
    public void initSetupClient()
    {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        BlockRegistry.registerFlammables();
        BlockRegistry.registerCompostables();
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(BambooBoatEntity.class, BambooBoatRenderer::new);
    }
}