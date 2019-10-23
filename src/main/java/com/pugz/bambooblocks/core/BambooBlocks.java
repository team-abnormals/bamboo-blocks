package com.pugz.bambooblocks.core;

import com.pugz.bambooblocks.core.registry.BlockRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("bambooblocks")
@Mod.EventBusSubscriber(modid = "bambooblocks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BambooBlocks
{
    public BambooBlocks() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        BlockRegistry.registerFlammables();
        BlockRegistry.registerCompostables();
    }
}