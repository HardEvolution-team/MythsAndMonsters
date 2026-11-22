package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyJar extends ModelBase {
   public ModelRenderer Body;
   public ModelRenderer Wing_TL;
   public ModelRenderer Wing_TR;
   public ModelRenderer Wing_BL;
   public ModelRenderer Wing_BR;
   public ModelRenderer Outer_Jar;
   public ModelRenderer Inner_Jar;
   public ModelRenderer GoldNugget;

   public ModelFairyJar() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3);
      this.Body.setRotationPoint(0.0F, 18.0F, -2.0F);
      this.Body.setTextureSize(64, 64);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.Wing_TL = new ModelRenderer(this, 0, 6);
      this.Wing_TL.addBox(-1.0F, -4.0F, 0.0F, 3, 4, 1);
      this.Wing_TL.setRotationPoint(1.5F, 19.0F, 1.0F);
      this.Wing_TL.setTextureSize(64, 64);
      this.Wing_TL.mirror = true;
      this.setRotation(this.Wing_TL, 0.0F, 0.0F, 0.0F);
      this.Wing_TR = new ModelRenderer(this, 0, 6);
      this.Wing_TR.addBox(-2.0F, -4.0F, 0.0F, 3, 4, 1);
      this.Wing_TR.setRotationPoint(-1.5F, 19.0F, 1.0F);
      this.Wing_TR.setTextureSize(64, 64);
      this.Wing_TR.mirror = true;
      this.setRotation(this.Wing_TR, 0.0F, 0.0F, 0.0F);
      this.Wing_BL = new ModelRenderer(this, 0, 11);
      this.Wing_BL.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Wing_BL.setRotationPoint(1.5F, 20.0F, 1.0F);
      this.Wing_BL.setTextureSize(64, 64);
      this.Wing_BL.mirror = true;
      this.setRotation(this.Wing_BL, 0.0F, 0.0F, 0.0F);
      this.Wing_BR = new ModelRenderer(this, 0, 11);
      this.Wing_BR.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Wing_BR.setRotationPoint(-1.5F, 20.0F, 1.0F);
      this.Wing_BR.setTextureSize(64, 64);
      this.Wing_BR.mirror = true;
      this.setRotation(this.Wing_BR, 0.0F, 0.0F, 0.0F);
      this.Outer_Jar = new ModelRenderer(this, 24, 9);
      this.Outer_Jar.addBox(-5.0F, -12.0F, -5.0F, 10, 13, 10);
      this.Outer_Jar.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.Outer_Jar.setTextureSize(64, 64);
      this.Outer_Jar.mirror = true;
      this.setRotation(this.Outer_Jar, 0.0F, 0.0F, 0.0F);
      this.Inner_Jar = new ModelRenderer(this, 26, 32);
      this.Inner_Jar.addBox(-4.5F, 0.0F, -4.5F, 9, 11, 9);
      this.Inner_Jar.setRotationPoint(0.0F, 12.0F, 0.0F);
      this.Inner_Jar.setTextureSize(64, 64);
      this.Inner_Jar.mirror = true;
      this.setRotation(this.Inner_Jar, 0.0F, 0.0F, 0.0F);
      this.GoldNugget = new ModelRenderer(this, 4, 9);
      this.GoldNugget.addBox(-3.5F, 0.0F, -3.5F, 7, 1, 7);
      this.GoldNugget.setRotationPoint(0.0F, 10.0F, 0.0F);
      this.GoldNugget.setTextureSize(64, 64);
      this.GoldNugget.mirror = true;
      this.setRotation(this.GoldNugget, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Body.render(f5);
      this.Wing_TL.render(f5);
      this.Wing_TR.render(f5);
      this.Wing_BL.render(f5);
      this.Wing_BR.render(f5);
      this.Outer_Jar.render(f5);
      this.Inner_Jar.render(f5);
      this.GoldNugget.render(f5);
   }

   public void renderModel(float f1) {
      this.Outer_Jar.render(f1);
      this.Inner_Jar.render(f1);
      this.GoldNugget.render(f1);
      this.Body.render(f1);
      this.Wing_TL.render(f1);
      this.Wing_TR.render(f1);
      this.Wing_BL.render(f1);
      this.Wing_BR.render(f1);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = 0.0F;
      model.rotateAngleY = 0.0F;
      model.rotateAngleZ = 0.0F;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      this.Wing_TL.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Wing_TR.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Wing_BR.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Wing_BL.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Body.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Wing_TR.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_TL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_TR.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * -0.5F;
      this.Wing_TL.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_BR.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_BL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
      this.Wing_BR.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * -0.5F;
      this.Wing_BL.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 3.0F * par2 * 0.5F;
   }
}
