package com.electrifiedded.mam.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGoldenRam extends RenderLiving<EntityGoldenRam> {
   private static final ResourceLocation shearedSheepTextures = new ResourceLocation("mam:textures/entity/goldenram.png");
   private static final ResourceLocation sheepTextures = new ResourceLocation("mam:textures/entity/goldenram_fur.png");

   public RenderGoldenRam(RenderManager rm, ModelBase body, final ModelBase fur, float shadow) {
      super(rm, body, shadow);
      this.addLayer(new LayerRenderer<EntityGoldenRam>() {
         @Override
         public void doRenderLayer(EntityGoldenRam e, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (!e.getSheared() && !e.isInvisible()) {
               RenderGoldenRam.this.bindTexture(sheepTextures);
               GlStateManager.color(0.811F, 0.701F, 0.101F);
               fur.setModelAttributes(RenderGoldenRam.this.getMainModel());
               fur.setLivingAnimations(e, limbSwing, limbSwingAmount, partialTicks);
               fur.render(e, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
               GlStateManager.color(1.0F, 1.0F, 1.0F);
            }
         }

         @Override
         public boolean shouldCombineTextures() {
            return true;
         }
      });
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityGoldenRam e) {
      return shearedSheepTextures;
   }
}
