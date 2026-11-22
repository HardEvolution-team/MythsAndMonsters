package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJar extends ModelBase {
   ModelRenderer Outer_Jar;
   ModelRenderer Inner_Jar;
   ModelRenderer GoldNugget;

   public ModelJar() {
      this.textureWidth = 64;
      this.textureHeight = 64;
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
      this.Outer_Jar.render(f5);
      this.Inner_Jar.render(f5);
      this.GoldNugget.render(f5);
   }

   public void renderModel(float f1) {
      this.Outer_Jar.render(f1);
      this.Inner_Jar.render(f1);
      this.GoldNugget.render(f1);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = 0.0F;
      model.rotateAngleY = 0.0F;
      model.rotateAngleZ = 0.0F;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
   }
}
