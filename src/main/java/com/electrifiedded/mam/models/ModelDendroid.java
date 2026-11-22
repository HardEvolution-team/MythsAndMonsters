package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDendroid extends ModelBase {
   ModelRenderer Trunk_Tilted;
   ModelRenderer Root_BR;
   ModelRenderer Root_F;
   ModelRenderer Root_BL;
   ModelRenderer Trunk_Main;
   ModelRenderer Trunk_F;
   ModelRenderer Eye;

   public ModelDendroid() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Trunk_Tilted = new ModelRenderer(this, 0, 0);
      this.Trunk_Tilted.addBox(0.0F, 0.0F, 0.0F, 5, 19, 6);
      this.Trunk_Tilted.setRotationPoint(0.0F, 3.0F, -3.0F);
      this.Trunk_Tilted.setTextureSize(64, 32);
      this.Trunk_Tilted.mirror = true;
      this.setRotation(this.Trunk_Tilted, 0.111536F, 0.0F, 0.0F);
      this.Root_BR = new ModelRenderer(this, 22, 25);
      this.Root_BR.addBox(-1.0F, 0.0F, -2.0F, 2, 5, 2);
      this.Root_BR.setRotationPoint(5.0F, 19.0F, 5.0F);
      this.Root_BR.setTextureSize(64, 32);
      this.Root_BR.mirror = true;
      this.setRotation(this.Root_BR, 0.0F, 0.0F, 0.0F);
      this.Root_F = new ModelRenderer(this, 10, 25);
      this.Root_F.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
      this.Root_F.setRotationPoint(0.0F, 20.0F, -6.0F);
      this.Root_F.setTextureSize(64, 32);
      this.Root_F.mirror = true;
      this.setRotation(this.Root_F, 0.0F, 0.0F, 0.0F);
      this.Root_BL = new ModelRenderer(this, 22, 25);
      this.Root_BL.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
      this.Root_BL.setRotationPoint(-4.0F, 19.0F, 4.0F);
      this.Root_BL.setTextureSize(64, 32);
      this.Root_BL.mirror = true;
      this.setRotation(this.Root_BL, 0.0F, 0.0F, 0.0F);
      this.Trunk_Main = new ModelRenderer(this, 0, 0);
      this.Trunk_Main.addBox(0.0F, 0.0F, 0.0F, 5, 19, 6);
      this.Trunk_Main.setRotationPoint(-4.0F, 1.0F, -1.0F);
      this.Trunk_Main.setTextureSize(64, 32);
      this.Trunk_Main.mirror = true;
      this.setRotation(this.Trunk_Main, 0.0F, 0.0F, 0.0F);
      this.Trunk_F = new ModelRenderer(this, 22, 0);
      this.Trunk_F.addBox(0.0F, 0.0F, 0.0F, 6, 10, 4);
      this.Trunk_F.setRotationPoint(-3.0F, 11.66667F, -1.0F);
      this.Trunk_F.setTextureSize(64, 32);
      this.Trunk_F.mirror = true;
      this.setRotation(this.Trunk_F, -0.669215F, 0.111536F, 0.0F);
      this.Eye = new ModelRenderer(this, 0, 25);
      this.Eye.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2);
      this.Eye.setRotationPoint(-1.0F, 9.0F, -3.0F);
      this.Eye.setTextureSize(64, 32);
      this.Eye.mirror = true;
      this.setRotation(this.Eye, 0.0F, 0.0F, 0.520501F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5);
      this.Trunk_Tilted.render(f5);
      this.Root_BR.render(f5);
      this.Root_F.render(f5);
      this.Root_BL.render(f5);
      this.Trunk_Main.render(f5);
      this.Trunk_F.render(f5);
      this.Eye.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
      this.Root_F.rotateAngleX = MathHelper.cos(par1 * 1.2662F + (float) Math.PI) * 1.4F * par2;
      this.Root_BR.rotateAngleX = MathHelper.cos(par1 * 1.2662F) * 1.4F * par2;
      this.Root_BL.rotateAngleX = MathHelper.cos(par1 * 1.2662F) * 1.4F * par2;
   }
}
