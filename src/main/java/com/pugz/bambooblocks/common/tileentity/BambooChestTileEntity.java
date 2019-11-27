package com.pugz.bambooblocks.common.tileentity;

import com.pugz.bambooblocks.core.registry.BlockRegistry;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

public class BambooChestTileEntity extends ChestTileEntity {
    public static TileEntityType<BambooChestTileEntity> WISTERIA_CHEST = TileEntityType.Builder.create(BambooChestTileEntity::new, BlockRegistry.BAMBOO_CHEST).build(null);

    public BambooChestTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public BambooChestTileEntity() {
        super(WISTERIA_CHEST);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
    }
}