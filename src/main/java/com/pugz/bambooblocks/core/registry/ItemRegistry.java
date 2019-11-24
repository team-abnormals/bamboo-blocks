package com.pugz.bambooblocks.core.registry;

import com.pugz.bambooblocks.common.entity.BambooBoatEntity;
import com.pugz.bambooblocks.common.item.BambooBoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "bambooblocks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
    public static Item BAMBOO_BOAT = new BambooBoatItem(BambooBoatEntity.Type.BAMBOO, new Item.Properties().group(ItemGroup.TRANSPORTATION).maxStackSize(1)).setRegistryName("bamboo_boat");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                BAMBOO_BOAT
        );
    }
}