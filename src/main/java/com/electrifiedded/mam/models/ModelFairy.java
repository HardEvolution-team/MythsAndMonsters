package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFairy extends ModelBase {
   ModelRenderer Body;
   ModelRenderer Wing_TL;
   ModelRenderer Wing_TR;
   ModelRenderer Wing_BL;
   ModelRenderer Wing_BR;

   public ModelFairy() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3);
      this.Body.setRotationPoint(0.0F, 18.0F, -2.0F);
      this.Body.setTextureSize(64, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.Wing_TL = new ModelRenderer(this, 0, 6);
      this.Wing_TL.addBox(-1.0F, -4.0F, 0.0F, 3, 4, 1);
      this.Wing_TL.setRotationPoint(1.5F, 19.0F, 1.0F);
      this.Wing_TL.setTextureSize(64, 32);
      this.Wing_TL.mirror = true;
      this.setRotation(this.Wing_TL, 0.0F, 0.0F, 0.0F);
      this.Wing_TR = new ModelRenderer(this, 0, 6);
      this.Wing_TR.addBox(-2.0F, -4.0F, 0.0F, 3, 4, 1);
      this.Wing_TR.setRotationPoint(-1.5F, 19.0F, 1.0F);
      this.Wing_TR.setTextureSize(64, 32);
      this.Wing_TR.mirror = true;
      this.setRotation(this.Wing_TR, 0.0F, 0.0F, 0.0F);
      this.Wing_BL = new ModelRenderer(this, 0, 11);
      this.Wing_BL.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Wing_BL.setRotationPoint(1.5F, 20.0F, 1.0F);
      this.Wing_BL.setTextureSize(64, 32);
      this.Wing_BL.mirror = true;
      this.setRotation(this.Wing_BL, 0.0F, 0.0F, 0.0F);
      this.Wing_BR = new ModelRenderer(this, 0, 11);
      this.Wing_BR.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Wing_BR.setRotationPoint(-1.5F, 20.0F, 1.0F);
      this.Wing_BR.setTextureSize(64, 32);
      this.Wing_BR.mirror = true;
      this.setRotation(this.Wing_BR, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      GL11.glTranslatef(0.0F, -0.7F, 0.0F);
      this.Body.render(f5);
      this.Wing_TL.render(f5);
      this.Wing_TR.render(f5);
      this.Wing_BL.render(f5);
      this.Wing_BR.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
      this.Wing_TR.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_TL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_TR.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * -0.5F;
      this.Wing_TL.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_BR.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_BL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_BR.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * -0.5F;
      this.Wing_BL.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
   }
}
