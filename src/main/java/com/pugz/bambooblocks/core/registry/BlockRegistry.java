package com.pugz.bambooblocks.core.registry;

import com.pugz.bambooblocks.common.block.*;
import com.pugz.bambooblocks.common.block.ChestBlock;
import com.pugz.bambooblocks.common.block.LadderBlock;
import com.pugz.bambooblocks.common.block.TrappedChestBlock;
import com.pugz.bambooblocks.common.config.BambooBlocksConfig;
import com.pugz.bambooblocks.common.item.FuelItem;
import com.pugz.bambooblocks.core.util.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
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

    public static Block REED_THATCH = new Block(BlockProperties.REED_THATCH).setRegistryName("reed_thatch");
	public static Block REED_THATCH_STAIRS = new StairsBlock(REED_THATCH.getDefaultState(), BlockProperties.REED_THATCH).setRegistryName("reed_thatch_stairs");
    public static Block REED_THATCH_SLAB = new SlabBlock(BlockProperties.REED_THATCH).setRegistryName("reed_thatch_slab");

    public static Block POTTED_BAMBOO_TORCH = new FlowerPotBlock(BAMBOO_TORCH, BlockProperties.FLOWER_POT_TORCH).setRegistryName("potted_bamboo_torch");

    //quark
    public static Block BAMBOO_LADDER = new LadderBlock(BlockProperties.LADDER).setRegistryName("bamboo_ladder");
    public static Block HORIZONTAL_BAMBOO_PLANKS = new Block(BlockProperties.BAMBOO_PLANKS).setRegistryName("horizontal_bamboo_planks");
    public static Block BAMBOO_CHEST = new ChestBlock(BlockProperties.CHEST).setRegistryName("bamboo_chest");
    public static Block TRAPPED_BAMBOO_CHEST = new TrappedChestBlock(BlockProperties.CHEST).setRegistryName("trapped_bamboo_chest");
    public static Block VERTICAL_BAMBOO_SLAB = new VerticalSlabBlock(BlockProperties.BAMBOO_PLANKS).setRegistryName("vertical_bamboo_slab");
    public static Block VERTICAL_REED_THATCH_SLAB = new VerticalSlabBlock(BlockProperties.REED_THATCH).setRegistryName("vertical_reed_thatch_slab");

    @SuppressWarnings("serial")
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ArrayList<Block> blocks = new ArrayList<Block>() {};
        if (BambooBlocksConfig.bambooPlanks.get()) {
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
        if (BambooBlocksConfig.bambooTorches.get()) {
            blocks.add(BAMBOO_TORCH);
            blocks.add(BAMBOO_WALL_TORCH);
            blocks.add(POTTED_BAMBOO_TORCH);
        }
        if (BambooBlocksConfig.reedThatch.get()) {
            blocks.add(REED_THATCH);
            blocks.add(REED_THATCH_STAIRS);
            blocks.add(REED_THATCH_SLAB);
        }
        //if (ModList.get().isLoaded("quark")) {
            blocks.add(BAMBOO_LADDER);
            blocks.add(HORIZONTAL_BAMBOO_PLANKS);
            blocks.add(BAMBOO_CHEST);
            blocks.add(TRAPPED_BAMBOO_CHEST);
            blocks.add(VERTICAL_BAMBOO_SLAB);
            blocks.add(VERTICAL_REED_THATCH_SLAB);
        //}
        for (Block block : blocks) event.getRegistry().register(block);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Item.Properties buildingBlocks = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        Item.Properties decorations = new Item.Properties().group(ItemGroup.DECORATIONS);
        Item.Properties redstone = new Item.Properties().group(ItemGroup.REDSTONE);
        event.getRegistry().registerAll(
                new FuelItem.WallOrFloorFuelItem(BAMBOO_TORCH, BAMBOO_WALL_TORCH, decorations, 50).setRegistryName(BAMBOO_TORCH.getRegistryName()),
                new BlockItem(BAMBOO_PLANKS, buildingBlocks).setRegistryName(BAMBOO_PLANKS.getRegistryName()),
                new BlockItem(BAMBOO_STAIRS, buildingBlocks).setRegistryName(BAMBOO_STAIRS.getRegistryName()),
                new BlockItem(BAMBOO_SLAB, buildingBlocks).setRegistryName(BAMBOO_SLAB.getRegistryName()),
                new BlockItem(BAMBOO_FENCE, decorations).setRegistryName(BAMBOO_FENCE.getRegistryName()),
                new BlockItem(BAMBOO_FENCE_GATE, redstone).setRegistryName(BAMBOO_FENCE_GATE.getRegistryName()),
                new BlockItem(BAMBOO_DOOR, redstone).setRegistryName(BAMBOO_DOOR.getRegistryName()),
                new BlockItem(BAMBOO_TRAPDOOR, redstone).setRegistryName(BAMBOO_TRAPDOOR.getRegistryName()),
                new BlockItem(BAMBOO_BUTTON, redstone).setRegistryName(BAMBOO_BUTTON.getRegistryName()),
                new BlockItem(BAMBOO_PRESSURE_PLATE, redstone).setRegistryName(BAMBOO_PRESSURE_PLATE.getRegistryName()),
                new BlockItem(REED_THATCH, buildingBlocks).setRegistryName(REED_THATCH.getRegistryName()),
                new BlockItem(REED_THATCH_STAIRS, buildingBlocks).setRegistryName(REED_THATCH_STAIRS.getRegistryName()),
                new BlockItem(REED_THATCH_SLAB, buildingBlocks).setRegistryName(REED_THATCH_SLAB.getRegistryName())
        );
        if (ModList.get().isLoaded("quark")) {
            event.getRegistry().registerAll(
                    new FuelItem(BAMBOO_LADDER, decorations, 300).setRegistryName(BAMBOO_LADDER.getRegistryName()),
                    new BlockItem(HORIZONTAL_BAMBOO_PLANKS, buildingBlocks).setRegistryName(HORIZONTAL_BAMBOO_PLANKS.getRegistryName()),
                    new FuelItem(VERTICAL_BAMBOO_SLAB, buildingBlocks, 150).setRegistryName(VERTICAL_BAMBOO_SLAB.getRegistryName()),
                    new FuelItem(VERTICAL_REED_THATCH_SLAB, buildingBlocks, 150).setRegistryName(VERTICAL_REED_THATCH_SLAB.getRegistryName())
            );
        }
    }

    public static void registerFlammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(BAMBOO_PLANKS, 5, 20);
        fire.setFireInfo(BAMBOO_STAIRS, 5, 20);
        fire.setFireInfo(BAMBOO_SLAB, 5, 20);
        fire.setFireInfo(BAMBOO_FENCE, 5, 20);
        fire.setFireInfo(BAMBOO_FENCE_GATE, 5, 20);
        fire.setFireInfo(REED_THATCH, 10, 32);
        fire.setFireInfo(REED_THATCH_STAIRS, 10, 32);
        fire.setFireInfo(REED_THATCH_SLAB, 10, 32);
        fire.setFireInfo(HORIZONTAL_BAMBOO_PLANKS, 5, 20);
        fire.setFireInfo(VERTICAL_BAMBOO_SLAB, 5, 20);
        fire.setFireInfo(VERTICAL_REED_THATCH_SLAB, 5, 20);
    }

    public static void registerCompostables() {
        ComposterBlock.CHANCES.put(REED_THATCH.asItem(), 0.75F);
        ComposterBlock.CHANCES.put(REED_THATCH_STAIRS.asItem(), 0.6F);
        ComposterBlock.CHANCES.put(REED_THATCH_SLAB.asItem(), 0.45F);
    }
}