package com.pugz.bambooblocks.common.block;

import com.pugz.bambooblocks.common.tileentity.TrappedBambooChestTileEntity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TrappedChestBlock extends net.minecraft.block.TrappedChestBlock {

    public TrappedChestBlock(Properties properties) {
        super(properties);
    }

    public TileEntity createNewTileEntity(IBlockReader reader) {
        return new TrappedBambooChestTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    public static void provideItemBlock(Item.Properties properties) {
        ChestBlock.setTEISR(properties, new ResourceLocation("bambooblocks", "textures/model/chest/bamboo_normal.png"), new ResourceLocation("bambooblocks", "textures/model/chest/bamboo_double.png"));
    }
}