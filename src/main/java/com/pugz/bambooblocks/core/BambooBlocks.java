package com.pugz.bambooblocks.core;

import com.pugz.bambooblocks.common.config.BambooBlocksConfig;
import com.pugz.bambooblocks.core.registry.BlockRegistry;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("bambooblocks")
public class BambooBlocks
{
    public BambooBlocks() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BambooBlocksConfig.COMMON_CONFIG);
        BambooBlocksConfig.loadConfig(BambooBlocksConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("bambooblocks-common.toml"));
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        BlockRegistry.registerFlammables();
        BlockRegistry.registerCompostables();
    }
}