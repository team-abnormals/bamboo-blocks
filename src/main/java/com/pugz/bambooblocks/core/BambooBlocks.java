package com.pugz.bambooblocks.core;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod(BambooBlocks.MODID)
public class BambooBlocks {

	public static final String MODID = "bamboo_blocks";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public BambooBlocks() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> this::initSetupClient);
	}

	public void initSetupClient() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
    	DeferredWorkQueue.runLater(() -> {
    		BambooBlocksRegistry.registerFlammables();
    		BambooBlocksRegistry.registerCompostables();
    	});
    }

	private void setupClient(final FMLClientSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			BambooBlocksRegistry.registerRenderLayers();
		});
	}
}