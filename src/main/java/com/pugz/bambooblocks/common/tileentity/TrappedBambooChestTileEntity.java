package com.pugz.bambooblocks.common.tileentity;

import com.pugz.bambooblocks.core.registry.BlockRegistry;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

public class TrappedBambooChestTileEntity extends BambooChestTileEntity {
    public static TileEntityType<TrappedBambooChestTileEntity> TRAPPED_BAMBOO_CHEST = TileEntityType.Builder.create(TrappedBambooChestTileEntity::new, BlockRegistry.TRAPPED_BAMBOO_CHEST).build(null);

    public TrappedBambooChestTileEntity() {
        super(TRAPPED_BAMBOO_CHEST);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
    }
}