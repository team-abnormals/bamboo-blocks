package com.minecraftabnormals.bamboo_blocks.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.bamboo_blocks.core.BambooBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BambooBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBItems {
	public static final ItemSubRegistryHelper HELPER = BambooBlocks.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> BAMBOO_BOAT = HELPER.createBoatItem("bamboo", BBBlocks.BAMBOO_PLANKS);
}