package com.pugz.bambooblocks.core.registry;

import com.pugz.bambooblocks.common.block.BambooTorchBlock;
import com.pugz.bambooblocks.common.block.BambooWallTorchBlock;
import com.pugz.bambooblocks.common.block.BookshelfBlock;
import com.pugz.bambooblocks.common.block.VerticalSlabBlock;
import com.pugz.bambooblocks.core.util.BlockProperties;
import com.pugz.bambooblocks.core.util.RegistryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = "bambooblocks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BambooBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, "bambooblocks");
	
    public static RegistryObject<Block> BAMBOO_WALL_TORCH 		= RegistryUtils.createBlockNoItem("bamboo_wall_torch", () -> new BambooWallTorchBlock());
    public static RegistryObject<Block> BAMBOO_TORCH 			= RegistryUtils.createWallOrFloorBlock("bamboo_torch", () -> new BambooTorchBlock(), () -> BAMBOO_WALL_TORCH.get(), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> POTTED_BAMBOO_TORCH 	= RegistryUtils.createBlockNoItem("potted_bamboo_torch", () -> new FlowerPotBlock(BAMBOO_TORCH.get(), BlockProperties.FLOWER_POT_TORCH));

    public static RegistryObject<Block> BAMBOO_PLANKS 			= RegistryUtils.createBlock("bamboo_planks", () -> new Block(BlockProperties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> BAMBOO_STAIRS 			= RegistryUtils.createBlock("bamboo_stairs", () -> new StairsBlock(BAMBOO_PLANKS.get().getDefaultState(), BlockProperties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> BAMBOO_SLAB 			= RegistryUtils.createBlock("bamboo_slab", () -> new SlabBlock(BlockProperties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> BAMBOO_FENCE 			= RegistryUtils.createBlock("bamboo_fence", () -> new FenceBlock(BlockProperties.BAMBOO_PLANKS), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> BAMBOO_FENCE_GATE 		= RegistryUtils.createBlock("bamboo_fence_gate", () -> new FenceGateBlock(BlockProperties.BAMBOO_PLANKS), ItemGroup.REDSTONE);
    public static RegistryObject<Block> BAMBOO_DOOR 			= RegistryUtils.createBlock("bamboo_door", () -> new DoorBlock(BlockProperties.BAMBOO_DOORS), ItemGroup.REDSTONE);
    public static RegistryObject<Block> BAMBOO_TRAPDOOR 		= RegistryUtils.createBlock("bamboo_trapdoor", () -> new TrapDoorBlock(BlockProperties.BAMBOO_DOORS), ItemGroup.REDSTONE);
    public static RegistryObject<Block> BAMBOO_BUTTON 			= RegistryUtils.createBlock("bamboo_button", () -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
    public static RegistryObject<Block> BAMBOO_PRESSURE_PLATE 	= RegistryUtils.createBlock("bamboo_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);

    public static RegistryObject<Block> REED_THATCH 			= RegistryUtils.createBlock("reed_thatch", () -> new Block(BlockProperties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> REED_THATCH_STAIRS 		= RegistryUtils.createBlock("reed_thatch_stairs", () -> new StairsBlock(REED_THATCH.get().getDefaultState(), BlockProperties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> REED_THATCH_SLAB 		= RegistryUtils.createBlock("reed_thatch_slab", () -> new SlabBlock(BlockProperties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);

    //quark
    public static RegistryObject<Block> BAMBOO_LADDER 				= RegistryUtils.createBlockCompat("quark", "bamboo_ladder", () -> new LadderBlock(BlockProperties.LADDER), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> HORIZONTAL_BAMBOO_PLANKS 	= RegistryUtils.createBlockCompat("quark", "horizontal_bamboo_planks", () -> new Block(BlockProperties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> VERTICAL_BAMBOO_SLAB 		= RegistryUtils.createBlockCompat("quark", "bamboo_vertical_slab", () -> new VerticalSlabBlock(BlockProperties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> VERTICAL_REED_THATCH_SLAB 	= RegistryUtils.createBlockCompat("quark", "reed_thatch_vertical_slab", () -> new VerticalSlabBlock(BlockProperties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> BAMBOO_BOOKSHELF 			= RegistryUtils.createBlockCompat("quark", "bamboo_bookshelf", () -> new BookshelfBlock(BlockProperties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);

    public static void registerFlammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(BAMBOO_PLANKS.get(), 5, 20);
        fire.setFireInfo(BAMBOO_STAIRS.get(), 5, 20);
        fire.setFireInfo(BAMBOO_SLAB.get(), 5, 20);
        fire.setFireInfo(BAMBOO_FENCE.get(), 5, 20);
        fire.setFireInfo(BAMBOO_FENCE_GATE.get(), 5, 20);
        fire.setFireInfo(REED_THATCH.get(), 10, 32);
        fire.setFireInfo(REED_THATCH_STAIRS.get(), 10, 32);
        fire.setFireInfo(REED_THATCH_SLAB.get(), 10, 32);
        fire.setFireInfo(HORIZONTAL_BAMBOO_PLANKS.get(), 5, 20);
        fire.setFireInfo(VERTICAL_BAMBOO_SLAB.get(), 5, 20);
        fire.setFireInfo(VERTICAL_REED_THATCH_SLAB.get(), 5, 20);
        fire.setFireInfo(BAMBOO_BOOKSHELF.get(), 5, 20);
    }

    public static void registerCompostables() {
        ComposterBlock.CHANCES.put(REED_THATCH.get(), 0.75F);
        ComposterBlock.CHANCES.put(REED_THATCH_STAIRS.get(), 0.6F);
        ComposterBlock.CHANCES.put(REED_THATCH_SLAB.get(), 0.45F);
    }
    
    public static void registerRenderLayers() {
    	RenderTypeLookup.setRenderLayer(BAMBOO_WALL_TORCH.get(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BAMBOO_TORCH.get(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(POTTED_BAMBOO_TORCH.get(), RenderType.getCutout());
    	
    	RenderTypeLookup.setRenderLayer(BAMBOO_DOOR.get(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BAMBOO_TRAPDOOR.get(), RenderType.getCutout());
    	RenderTypeLookup.setRenderLayer(BAMBOO_LADDER.get(), RenderType.getCutout());
    }
}