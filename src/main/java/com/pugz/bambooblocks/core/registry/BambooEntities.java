package com.pugz.bambooblocks.core.registry;

import com.pugz.bambooblocks.client.render.BambooBoatRenderer;
import com.pugz.bambooblocks.common.entity.BambooBoatEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BambooEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, "bambooblocks");

	public static RegistryObject<EntityType<BambooBoatEntity>>  BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<BambooBoatEntity>create(BambooBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).build("boat"));

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends BambooBoatEntity>)BOAT.get(), BambooBoatRenderer::new);
    }
}