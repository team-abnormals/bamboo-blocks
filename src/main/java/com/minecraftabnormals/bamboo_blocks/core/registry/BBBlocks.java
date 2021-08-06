package com.minecraftabnormals.bamboo_blocks.core.registry;

import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.minecraftabnormals.bamboo_blocks.common.block.BambooTorchBlock;
import com.minecraftabnormals.bamboo_blocks.common.block.BambooWallTorchBlock;
import com.minecraftabnormals.bamboo_blocks.core.BambooBlocks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BambooBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBBlocks {
	public static final BlockSubRegistryHelper HELPER = BambooBlocks.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> BAMBOO_WALL_TORCH = HELPER.createBlockNoItem("bamboo_wall_torch", () -> new BambooWallTorchBlock(Properties.BAMBOO_TORCH, ParticleTypes.FLAME));
	public static final RegistryObject<Block> BAMBOO_TORCH = HELPER.createWallOrFloorBlock("bamboo_torch", () -> new BambooTorchBlock(Properties.BAMBOO_TORCH, ParticleTypes.FLAME), BAMBOO_WALL_TORCH, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_BAMBOO_TORCH = HELPER.createBlockNoItem("potted_bamboo_torch", () -> new FlowerPotBlock(BAMBOO_TORCH.get(), Properties.FLOWER_POT_BAMBOO_TORCH));

	public static final RegistryObject<Block> SOUL_BAMBOO_WALL_TORCH = HELPER.createBlockNoItem("soul_bamboo_wall_torch", () -> new BambooWallTorchBlock(Properties.SOUL_BAMBOO_TORCH, ParticleTypes.SOUL_FIRE_FLAME));
	public static final RegistryObject<Block> SOUL_BAMBOO_TORCH = HELPER.createWallOrFloorBlock("soul_bamboo_torch", () -> new BambooTorchBlock(Properties.SOUL_BAMBOO_TORCH, ParticleTypes.SOUL_FIRE_FLAME), SOUL_BAMBOO_WALL_TORCH, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_SOUL_BAMBOO_TORCH = HELPER.createBlockNoItem("potted_soul_bamboo_torch", () -> new FlowerPotBlock(SOUL_BAMBOO_TORCH.get(), Properties.FLOWER_POT_SOUL_BAMBOO_TORCH));

	public static final RegistryObject<Block> ENDER_BAMBOO_WALL_TORCH = HELPER.createBlockNoItem("ender_bamboo_wall_torch", () -> new BambooWallTorchBlock(Properties.BAMBOO_TORCH, null));
	public static final RegistryObject<Block> ENDER_BAMBOO_TORCH = HELPER.createWallOrFloorBlock("ender_bamboo_torch", () -> new BambooTorchBlock(Properties.BAMBOO_TORCH, null), ENDER_BAMBOO_WALL_TORCH, ModList.get().isLoaded("endergetic") ? ItemGroup.TAB_DECORATIONS : null);
	public static final RegistryObject<Block> POTTED_ENDER_BAMBOO_TORCH = HELPER.createBlockNoItem("potted_ender_bamboo_torch", () -> new FlowerPotBlock(ENDER_BAMBOO_TORCH.get(), Properties.FLOWER_POT_BAMBOO_TORCH));

	public static final RegistryObject<Block> BAMBOO_PLANKS = HELPER.createBlock("bamboo_planks", () -> new PlanksBlock(Properties.BAMBOO_PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_SLAB = HELPER.createBlock("bamboo_slab", () -> new WoodSlabBlock(Properties.BAMBOO_PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_STAIRS = HELPER.createBlock("bamboo_stairs", () -> new WoodStairsBlock(BAMBOO_PLANKS.get().defaultBlockState(), Properties.BAMBOO_PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_FENCE = HELPER.createFuelBlock("bamboo_fence", () -> new WoodFenceBlock(Properties.BAMBOO_PLANKS), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> BAMBOO_FENCE_GATE = HELPER.createFuelBlock("bamboo_fence_gate", () -> new WoodFenceGateBlock(Properties.BAMBOO_PLANKS), 300, ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> BAMBOO_DOOR = HELPER.createBlock("bamboo_door", () -> new WoodDoorBlock(Properties.BAMBOO_DOOR), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> BAMBOO_TRAPDOOR = HELPER.createBlock("bamboo_trapdoor", () -> new WoodTrapDoorBlock(Properties.BAMBOO_DOOR), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> BAMBOO_BUTTON = HELPER.createBlock("bamboo_button", () -> new AbnormalsWoodButtonBlock(Properties.BAMBOO_BUTTON), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> BAMBOO_PRESSURE_PLATE = HELPER.createBlock("bamboo_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.BAMBOO_PRESSURE_PLATE), ItemGroup.TAB_REDSTONE);
	public static final RegistryObject<Block> BAMBOO_LADDER = HELPER.createCompatFuelBlock("quark", "bamboo_ladder", () -> new AbnormalsLadderBlock(Properties.BAMBOO_LADDER), 300, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> VERTICAL_BAMBOO_PLANKS = HELPER.createCompatBlock("quark", "vertical_bamboo_planks", () -> new Block(Properties.BAMBOO_PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "bamboo_vertical_slab", () -> new VerticalSlabBlock(Properties.BAMBOO_PLANKS), 150, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "bamboo_bookshelf", () -> new BookshelfBlock(Properties.BAMBOO_PLANKS), 300, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BAMBOO_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "bamboo_beehive", () -> new AbnormalsBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);

	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> BAMBOO_SIGNS = HELPER.createSignBlock("bamboo", MaterialColor.WOOD);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> BAMBOO_CHESTS = HELPER.createCompatChestBlocks("quark", "bamboo", MaterialColor.WOOD);

	public static final RegistryObject<Block> REED_THATCH = HELPER.createBlock("reed_thatch", () -> new Block(Properties.REED_THATCH), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> REED_THATCH_STAIRS = HELPER.createBlock("reed_thatch_stairs", () -> new AbnormalsStairsBlock(REED_THATCH.get().defaultBlockState(), Properties.REED_THATCH), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> REED_THATCH_SLAB = HELPER.createBlock("reed_thatch_slab", () -> new SlabBlock(Properties.REED_THATCH), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> REED_THATCH_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "reed_thatch_vertical_slab", () -> new VerticalSlabBlock(Properties.REED_THATCH), ItemGroup.TAB_BUILDING_BLOCKS);

	public static class Properties {
		public static final AbstractBlock.Properties BAMBOO_TORCH = AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.0F).sound(SoundType.BAMBOO).lightLevel((state) -> 14);
		public static final AbstractBlock.Properties SOUL_BAMBOO_TORCH = AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.0F).sound(SoundType.BAMBOO).lightLevel((state) -> 10);
		public static final AbstractBlock.Properties FLOWER_POT_BAMBOO_TORCH = AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).lightLevel((state) -> 14);
		public static final AbstractBlock.Properties FLOWER_POT_SOUL_BAMBOO_TORCH = AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).lightLevel((state) -> 10);

		public static final AbstractBlock.Properties BAMBOO_PLANKS = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.25F, 3.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties BAMBOO_DOOR = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion().strength(3.25F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties BAMBOO_BUTTON = AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.6F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties BAMBOO_PRESSURE_PLATE = AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(0.6F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties BAMBOO_LADDER = AbstractBlock.Properties.of(Material.DECORATION).noOcclusion().strength(0.4F).sound(SoundType.LADDER);

		public static final AbstractBlock.Properties REED_THATCH = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.5F, 2.0F).sound(SoundType.GRASS).harvestTool(ToolType.HOE);
	}

}