package com.pugz.bambooblocks.core.registry;

import com.pugz.bambooblocks.common.entity.BambooBoatEntity;
import com.pugz.bambooblocks.common.item.BambooBoatItem;
import com.pugz.bambooblocks.core.util.RegistryUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = "bambooblocks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BambooItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, "bambooblocks");

	public static RegistryObject<Item> BAMBOO_BOAT = RegistryUtils.createItem("bamboo_boat", () -> new BambooBoatItem(BambooBoatEntity.Type.BAMBOO, new Item.Properties().group(ItemGroup.TRANSPORTATION).maxStackSize(1)));
}