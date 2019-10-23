package com.pugz.bambooblocks.core.registry;

import com.pugz.bambooblocks.common.block.*;
import com.pugz.bambooblocks.common.block.DirectionalBlock;
import com.pugz.bambooblocks.common.config.BambooBlocksConfig;
import com.pugz.bambooblocks.core.util.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = "bambooblocks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {
    public static Block BAMBOO_TORCH = new BambooTorchBlock().setRegistryName("bamboo_torch");
    public static Block BAMBOO_WALL_TORCH = new BambooWallTorchBlock().setRegistryName("bamboo_wall_torch");

    public static Block BAMBOO_PLANKS = new Block(BlockProperties.BAMBOO_PLANKS).setRegistryName("bamboo_planks");
    public static Block BAMBOO_STAIRS = new StairsBlock(BAMBOO_PLANKS.getDefaultState(), BlockProperties.BAMBOO_PLANKS).setRegistryName("bamboo_stairs");
    public static Block BAMBOO_SLAB = new SlabBlock(BlockProperties.BAMBOO_PLANKS).setRegistryName("bamboo_slab");
    public static Block BAMBOO_FENCE = new FenceBlock(BlockProperties.BAMBOO_PLANKS).setRegistryName("bamboo_fence");
    public static Block BAMBOO_FENCE_GATE = new FenceGateBlock(BlockProperties.BAMBOO_PLANKS).setRegistryName("bamboo_fence_gate");
    public static Block BAMBOO_DOOR = new DoorBlock(BlockProperties.BAMBOO_DOORS).setRegistryName("bamboo_door");
    public static Block BAMBOO_TRAPDOOR = new TrapDoorBlock(BlockProperties.BAMBOO_DOORS).setRegistryName("bamboo_trapdoor");
    public static Block BAMBOO_BUTTON = new BambooButtonBlock().setRegistryName("bamboo_button");
    public static Block BAMBOO_PRESSURE_PLATE = new BambooPressurePlateBlock().setRegistryName("bamboo_pressure_plate");

    public static Block THATCH = new DirectionalBlock(BlockProperties.THATCH).setRegistryName("thatch");
    public static Block THATCH_STAIRS = new StairsBlock(THATCH.getDefaultState(), BlockProperties.THATCH).setRegistryName("thatch_stairs");
    public static Block THATCH_SLAB = new DirectionalSlabBlock(BlockProperties.THATCH).setRegistryName("thatch_slab");

    public static Block POTTED_BAMBOO_TORCH = new FlowerPotBlock(BAMBOO_TORCH, BlockProperties.FLOWER_POT_TORCH).setRegistryName("potted_bamboo_torch");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ArrayList<Block> blocks = new ArrayList<Block>() {};
        if (BambooBlocksConfig.bambooPlanks) {
            blocks.add(BAMBOO_PLANKS);
            blocks.add(BAMBOO_STAIRS);
            blocks.add(BAMBOO_SLAB);
            blocks.add(BAMBOO_FENCE);
            blocks.add(BAMBOO_FENCE_GATE);
            blocks.add(BAMBOO_DOOR);
            blocks.add(BAMBOO_TRAPDOOR);
            blocks.add(BAMBOO_BUTTON);
            blocks.add(BAMBOO_PRESSURE_PLATE);
        }
        if (BambooBlocksConfig.bambooTorches) {
            blocks.add(BAMBOO_TORCH);
            blocks.add(BAMBOO_WALL_TORCH);
        }
        if (BambooBlocksConfig.thatch) {
            blocks.add(THATCH);
            blocks.add(THATCH_STAIRS);
            blocks.add(THATCH_SLAB);
        }
        for (Block block : blocks) event.getRegistry().register(block);
        event.getRegistry().registerAll(POTTED_BAMBOO_TORCH);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Item.Properties buildingBlocks = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        Item.Properties decorations = new Item.Properties().group(ItemGroup.DECORATIONS);
        Item.Properties redstone = new Item.Properties().group(ItemGroup.REDSTONE);
        event.getRegistry().registerAll(
                new WallOrFloorItem(BAMBOO_TORCH, BAMBOO_WALL_TORCH, decorations).setRegistryName(BAMBOO_TORCH.getRegistryName()),
                new BlockItem(BAMBOO_PLANKS, buildingBlocks).setRegistryName(BAMBOO_PLANKS.getRegistryName()),
                new BlockItem(BAMBOO_STAIRS, buildingBlocks).setRegistryName(BAMBOO_STAIRS.getRegistryName()),
                new BlockItem(BAMBOO_SLAB, buildingBlocks).setRegistryName(BAMBOO_SLAB.getRegistryName()),
                new BlockItem(BAMBOO_FENCE, decorations).setRegistryName(BAMBOO_FENCE.getRegistryName()),
                new BlockItem(BAMBOO_FENCE_GATE, redstone).setRegistryName(BAMBOO_FENCE_GATE.getRegistryName()),
                new BlockItem(BAMBOO_DOOR, redstone).setRegistryName(BAMBOO_DOOR.getRegistryName()),
                new BlockItem(BAMBOO_TRAPDOOR, redstone).setRegistryName(BAMBOO_TRAPDOOR.getRegistryName()),
                new BlockItem(BAMBOO_BUTTON, redstone).setRegistryName(BAMBOO_BUTTON.getRegistryName()),
                new BlockItem(BAMBOO_PRESSURE_PLATE, redstone).setRegistryName(BAMBOO_PRESSURE_PLATE.getRegistryName()),
                new BlockItem(THATCH, buildingBlocks).setRegistryName(THATCH.getRegistryName()),
                new BlockItem(THATCH_STAIRS, buildingBlocks).setRegistryName(THATCH_STAIRS.getRegistryName()),
                new BlockItem(THATCH_SLAB, buildingBlocks).setRegistryName(THATCH_SLAB.getRegistryName())
        );
    }

    public static void registerFlammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(BAMBOO_PLANKS, 5, 20);
        fire.setFireInfo(BAMBOO_STAIRS, 5, 20);
        fire.setFireInfo(BAMBOO_SLAB, 5, 20);
        fire.setFireInfo(BAMBOO_FENCE, 5, 20);
        fire.setFireInfo(BAMBOO_FENCE_GATE, 5, 20);
        fire.setFireInfo(THATCH, 10, 32);
        fire.setFireInfo(THATCH_STAIRS, 10, 32);
        fire.setFireInfo(THATCH_SLAB, 10, 32);
    }

    public static void registerCompostables() {
        ComposterBlock.CHANCES.put(THATCH.asItem(), 0.75F);
        ComposterBlock.CHANCES.put(THATCH_STAIRS.asItem(), 0.6F);
        ComposterBlock.CHANCES.put(THATCH_SLAB.asItem(), 0.45F);
    }
}