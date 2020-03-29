package com.pugz.bambooblocks.core.util;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;
import com.pugz.bambooblocks.core.registry.BambooBlocks;
import com.pugz.bambooblocks.core.registry.BambooItems;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;

public class RegistryUtils {
	
	public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = BambooItems.ITEMS.register(name, supplier);
		return item;
	}
	
	public static BlockItem createSimpleItemBlock(Block block, ItemGroup itemGroup) {
        return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
    }
	
	public static <B extends Block> RegistryObject<B> createWallOrFloorBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = BambooBlocks.BLOCKS.register(name, supplier);
		BambooItems.ITEMS.register(name, () -> new WallOrFloorItem(block.get(), wallSupplier.get(), new Item.Properties().group(group)));
		return block;
	}
	
	public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = BambooBlocks.BLOCKS.register(name, supplier);
        BambooItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
        return block;
    }
	
	public static <B extends Block> RegistryObject<B> createBlockCompat(String mod, String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		ItemGroup determinedGroup = ModList.get().isLoaded(mod) || mod == "indev" ? itemGroup : null;
		RegistryObject<B> block = BambooBlocks.BLOCKS.register(name, supplier);
		BambooItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(determinedGroup)));
		return block;		
    }

    public static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BambooBlocks.BLOCKS.register(name, supplier);
        return block;
    }
}
