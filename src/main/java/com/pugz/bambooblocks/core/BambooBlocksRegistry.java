package com.pugz.bambooblocks.core;

import com.mojang.datafixers.util.Pair;
import com.pugz.bambooblocks.common.block.BambooTorchBlock;
import com.pugz.bambooblocks.common.block.BambooWallTorchBlock;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsLadderBlock;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsStairsBlock;
import com.teamabnormals.abnormals_core.common.blocks.BookshelfBlock;
import com.teamabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.teamabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.teamabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.AbnormalsWoodButtonBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.PlanksBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodDoorBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodFenceBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodFenceGateBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodPressurePlateBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodSlabBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodStairsBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.WoodTrapDoorBlock;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = BambooBlocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BambooBlocksRegistry {
	public static final RegistryHelper HELPER = BambooBlocks.REGISTRY_HELPER;

	public static final RegistryObject<Block> BAMBOO_WALL_TORCH = HELPER.createBlockNoItem("bamboo_wall_torch", () -> new BambooWallTorchBlock(Properties.BAMBOO_TORCH, ParticleTypes.FLAME));
	public static final RegistryObject<Block> BAMBOO_TORCH = HELPER.createWallOrFloorBlock("bamboo_torch", () -> new BambooTorchBlock(Properties.BAMBOO_TORCH, ParticleTypes.FLAME), () -> BAMBOO_WALL_TORCH.get(), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POTTED_BAMBOO_TORCH = HELPER.createBlockNoItem("potted_bamboo_torch", () -> new FlowerPotBlock(BAMBOO_TORCH.get(), Properties.FLOWER_POT_TORCH));

	public static final RegistryObject<Block> BAMBOO_PLANKS = HELPER.createBlock("bamboo_planks", () -> new PlanksBlock(Properties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_STAIRS = HELPER.createBlock("bamboo_stairs", () -> new WoodStairsBlock(BAMBOO_PLANKS.get().getDefaultState(), Properties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_SLAB = HELPER.createBlock("bamboo_slab", () -> new WoodSlabBlock(Properties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_FENCE = HELPER.createBlock("bamboo_fence", () -> new WoodFenceBlock(Properties.BAMBOO_PLANKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BAMBOO_FENCE_GATE = HELPER.createBlock("bamboo_fence_gate", () -> new WoodFenceGateBlock(Properties.BAMBOO_PLANKS), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> BAMBOO_DOOR = HELPER.createBlock("bamboo_door", () -> new WoodDoorBlock(Properties.BAMBOO_DOORS), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> BAMBOO_TRAPDOOR = HELPER.createBlock("bamboo_trapdoor", () -> new WoodTrapDoorBlock(Properties.BAMBOO_DOORS), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> BAMBOO_BUTTON = HELPER.createBlock("bamboo_button", () -> new AbnormalsWoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> BAMBOO_PRESSURE_PLATE = HELPER.createBlock("bamboo_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Item> BAMBOO_BOAT = HELPER.createBoatItem("bamboo", BAMBOO_PLANKS);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> SIGNS = HELPER.createSignBlock("bamboo", MaterialColor.WOOD);

	public static final RegistryObject<Block> REED_THATCH = HELPER.createBlock("reed_thatch", () -> new Block(Properties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> REED_THATCH_STAIRS = HELPER.createBlock("reed_thatch_stairs", () -> new AbnormalsStairsBlock(REED_THATCH.get().getDefaultState(), Properties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> REED_THATCH_SLAB = HELPER.createBlock("reed_thatch_slab", () -> new SlabBlock(Properties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);

	// quark
	public static final RegistryObject<Block> BAMBOO_LADDER = HELPER.createCompatBlock("quark", "bamboo_ladder", () -> new AbnormalsLadderBlock(Properties.LADDER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HORIZONTAL_BAMBOO_PLANKS = HELPER.createCompatBlock("quark", "horizontal_bamboo_planks", () -> new Block(Properties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> VERTICAL_BAMBOO_SLAB = HELPER.createCompatBlock("quark", "bamboo_vertical_slab", () -> new VerticalSlabBlock(Properties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> VERTICAL_REED_THATCH_SLAB = HELPER.createCompatBlock("quark", "reed_thatch_vertical_slab", () -> new VerticalSlabBlock(Properties.REED_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_BOOKSHELF = HELPER.createCompatBlock("quark", "bamboo_bookshelf", () -> new BookshelfBlock(Properties.BAMBOO_PLANKS), ItemGroup.BUILDING_BLOCKS);

	public static void registerFlammables() {
		DataUtils.registerFlammable(BAMBOO_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(BAMBOO_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(BAMBOO_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(BAMBOO_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(BAMBOO_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(REED_THATCH.get(), 10, 32);
		DataUtils.registerFlammable(REED_THATCH_STAIRS.get(), 10, 32);
		DataUtils.registerFlammable(REED_THATCH_SLAB.get(), 10, 32);
		DataUtils.registerFlammable(HORIZONTAL_BAMBOO_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(VERTICAL_BAMBOO_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(VERTICAL_REED_THATCH_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(BAMBOO_BOOKSHELF.get(), 5, 20);
	}

	public static void registerCompostables() {
		DataUtils.registerCompostable(0.75F, REED_THATCH.get());
		DataUtils.registerCompostable(0.6F, REED_THATCH_STAIRS.get());
		DataUtils.registerCompostable(0.45F, REED_THATCH_SLAB.get());
	}

	public static void registerRenderLayers() {
		RenderTypeLookup.setRenderLayer(BAMBOO_WALL_TORCH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BAMBOO_TORCH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(POTTED_BAMBOO_TORCH.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BAMBOO_DOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BAMBOO_TRAPDOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BAMBOO_LADDER.get(), RenderType.getCutout());
	}

	public static class Properties {
		public static final Block.Properties BAMBOO_TORCH = Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.BAMBOO).func_235838_a_((p_235470_0_) -> {return 14;});
		public static final Block.Properties BAMBOO_PLANKS = Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.25F, 3.5F).sound(SoundType.WOOD);
		public static final Block.Properties BAMBOO_DOORS = Block.Properties.create(Material.WOOD, MaterialColor.WOOD).notSolid().hardnessAndResistance(3.25F).sound(SoundType.WOOD);
		public static final Block.Properties REED_THATCH = Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(1.5F, 2.0F).sound(SoundType.PLANT);
		public static final Block.Properties BAMBOO_BUTTON = Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.6F).sound(SoundType.WOOD);
		public static final Block.Properties BAMBOO_PRESSURE_PLATE = Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.6F).sound(SoundType.WOOD);
		public static final Block.Properties FLOWER_POT_TORCH = Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F).func_235838_a_((p_235470_0_) -> {return 14;});
		public static final Block.Properties LADDER = Block.Properties.create(Material.MISCELLANEOUS).notSolid().hardnessAndResistance(0.4F).sound(SoundType.LADDER);
	}

}