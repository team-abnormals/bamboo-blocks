package com.pugz.bambooblocks.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockProperties {
    public static Block.Properties BAMBOO_TORCH = Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.BAMBOO);
    public static Block.Properties BAMBOO_PLANKS = Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.25F, 3.5F).sound(SoundType.WOOD);
    public static Block.Properties BAMBOO_DOORS = Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.25F).sound(SoundType.WOOD);
    public static Block.Properties THATCH = Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(1.5F, 2.0F).sound(SoundType.PLANT);
    public static Block.Properties BAMBOO_BUTTON = Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.6F).sound(SoundType.WOOD);
    public static Block.Properties BAMBOO_PRESSURE_PLATE = Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.6F).sound(SoundType.WOOD);
    public static Block.Properties FLOWER_POT = Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F);
    public static Block.Properties FLOWER_POT_TORCH = Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F).lightValue(14);
}