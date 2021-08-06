package com.minecraftabnormals.bamboo_blocks.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.bamboo_blocks.core.registry.BBBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BBCompat {

	public static void registerFlammables() {
		DataUtil.registerFlammable(BBBlocks.BAMBOO_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.VERTICAL_BAMBOO_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_VERTICAL_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(BBBlocks.BAMBOO_BEEHIVE.get(), 5, 20);

		DataUtil.registerFlammable(BBBlocks.REED_THATCH.get(), 60, 20);
		DataUtil.registerFlammable(BBBlocks.REED_THATCH_STAIRS.get(), 60, 20);
		DataUtil.registerFlammable(BBBlocks.REED_THATCH_SLAB.get(), 60, 20);
		DataUtil.registerFlammable(BBBlocks.REED_THATCH_VERTICAL_SLAB.get(), 60, 20);

	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(BBBlocks.REED_THATCH.get(), 0.65F);
		DataUtil.registerCompostable(BBBlocks.REED_THATCH_STAIRS.get(), 0.65F);
		DataUtil.registerCompostable(BBBlocks.REED_THATCH_SLAB.get(), 0.65F);
		DataUtil.registerCompostable(BBBlocks.REED_THATCH_VERTICAL_SLAB.get(), 0.65F);
	}

	public static void registerRenderLayers() {
		RenderTypeLookup.setRenderLayer(BBBlocks.BAMBOO_WALL_TORCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BAMBOO_TORCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BAMBOO_TORCH.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.SOUL_BAMBOO_WALL_TORCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.SOUL_BAMBOO_TORCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_SOUL_BAMBOO_TORCH.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.ENDER_BAMBOO_WALL_TORCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.ENDER_BAMBOO_TORCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_ENDER_BAMBOO_TORCH.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.BAMBOO_DOOR.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BAMBOO_TRAPDOOR.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BAMBOO_LADDER.get(), RenderType.cutout());
	}
}