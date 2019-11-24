package com.pugz.bambooblocks.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.pugz.bambooblocks.client.model.BambooBoatModel;
import com.pugz.bambooblocks.common.entity.BambooBoatEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BambooBoatRenderer extends EntityRenderer<BambooBoatEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("bambooblocks", "textures/entity/boat/bamboo_boat.png");
    protected final BambooBoatModel model = new BambooBoatModel();

    public BambooBoatRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        shadowSize = 0.8F;
    }

    @Override
    public void doRender(BambooBoatEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        setupTranslation(x, y, z);
        setupRotation(entity, entityYaw, partialTicks);
        bindEntityTexture(entity);
        if (renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }
        model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        if (renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(BambooBoatEntity entityIn, float entityYaw, float partialTicks) {
        GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        float f = (float)entityIn.getTimeSinceHit() - partialTicks;
        float f1 = entityIn.getDamageTaken() - partialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }
        if (f > 0.0F) {
            GlStateManager.rotatef(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }
        float f2 = entityIn.getRockingAngle(partialTicks);
        if (!MathHelper.epsilonEquals(f2, 0.0F)) {
            GlStateManager.rotatef(entityIn.getRockingAngle(partialTicks), 1.0F, 0.0F, 1.0F);
        }
        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double x, double y, double z) {
        GlStateManager.translatef((float)x, (float)y + 0.375F, (float)z);
    }

    @Override
    protected ResourceLocation getEntityTexture(BambooBoatEntity entity) {
        return TEXTURE;
    }

    @Override
    public boolean isMultipass() {
        return true;
    }

    @Override
    public void renderMultipass(BambooBoatEntity entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        setupTranslation(x, y, z);
        setupRotation(entityIn, entityYaw, partialTicks);
        bindEntityTexture(entityIn);
        model.renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}