package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelUrsa extends ModelBase {
   ModelRenderer Head;
   ModelRenderer Body_A;
   ModelRenderer Body_B;
   ModelRenderer LegR;
   ModelRenderer LegL;
   ModelRenderer Snout;
   ModelRenderer LegRB;
   ModelRenderer LegLB;
   ModelRenderer Tail;
   ModelRenderer EarL;
   ModelRenderer EarR;
   ModelRenderer Soul;

   public ModelUrsa() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Head = new ModelRenderer(this, 0, 25);
      this.Head.addBox(-4.5F, -5.0F, -6.0F, 9, 9, 8);
      this.Head.setRotationPoint(0.0F, 5.0F, -10.0F);
      this.Head.setTextureSize(128, 64);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body_A = new ModelRenderer(this, 0, 0);
      this.Body_A.addBox(-8.0F, -6.0F, 0.0F, 16, 15, 10);
      this.Body_A.setRotationPoint(0.0F, 4.0F, -10.0F);
      this.Body_A.setTextureSize(128, 64);
      this.Body_A.mirror = true;
      this.setRotation(this.Body_A, 0.1487144F, 0.0F, 0.0F);
      this.Body_B = new ModelRenderer(this, 52, 0);
      this.Body_B.addBox(-7.0F, -5.0F, 0.0F, 14, 14, 17);
      this.Body_B.setRotationPoint(0.0F, 3.0F, -2.0F);
      this.Body_B.setTextureSize(128, 64);
      this.Body_B.mirror = true;
      this.setRotation(this.Body_B, -0.1487144F, 0.0F, 0.0F);
      this.LegR = new ModelRenderer(this, 34, 25);
      this.LegR.addBox(-2.0F, 2.0F, -2.0F, 5, 15, 4);
      this.LegR.setRotationPoint(4.0F, 7.0F, -6.0F);
      this.LegR.setTextureSize(128, 64);
      this.LegR.mirror = true;
      this.setRotation(this.LegR, 0.0F, 0.0F, 0.0F);
      this.LegL = new ModelRenderer(this, 34, 25);
      this.LegL.addBox(-3.0F, 2.0F, -2.0F, 5, 15, 4);
      this.LegL.setRotationPoint(-4.0F, 7.0F, -6.0F);
      this.LegL.setTextureSize(128, 64);
      this.LegL.mirror = true;
      this.setRotation(this.LegL, 0.0F, 0.0F, 0.0F);
      this.Snout = new ModelRenderer(this, 52, 31);
      this.Snout.addBox(-0.5F, 0.0F, -9.0F, 5, 4, 3);
      this.Snout.setRotationPoint(-2.0F, 0.0F, 0.0F);
      this.Snout.setTextureSize(128, 64);
      this.Snout.mirror = true;
      this.setRotation(this.Snout, 0.0F, 0.0F, 0.0F);
      this.LegRB = new ModelRenderer(this, 70, 31);
      this.LegRB.addBox(-2.0F, 0.0F, -2.0F, 5, 17, 6);
      this.LegRB.setRotationPoint(5.0F, 7.0F, 13.0F);
      this.LegRB.setTextureSize(128, 64);
      this.LegRB.mirror = true;
      this.setRotation(this.LegRB, 0.0F, 0.0F, 0.0F);
      this.LegLB = new ModelRenderer(this, 70, 31);
      this.LegLB.addBox(-3.0F, 0.0F, -2.0F, 5, 17, 6);
      this.LegLB.setRotationPoint(-5.0F, 7.0F, 13.0F);
      this.LegLB.setTextureSize(128, 64);
      this.LegLB.mirror = true;
      this.setRotation(this.LegLB, 0.0F, 0.0F, 0.0F);
      this.Tail = new ModelRenderer(this, 52, 38);
      this.Tail.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.Tail.setRotationPoint(0.0F, 4.0F, 14.0F);
      this.Tail.setTextureSize(128, 64);
      this.Tail.mirror = true;
      this.setRotation(this.Tail, 0.0F, 0.0F, 0.0F);
      this.EarL = new ModelRenderer(this, 0, 42);
      this.EarL.addBox(-5.466667F, -7.0F, -3.0F, 4, 3, 0);
      this.EarL.setRotationPoint(0.0F, 5.0F, -10.0F);
      this.EarL.setTextureSize(128, 64);
      this.EarL.mirror = true;
      this.setRotation(this.EarL, 0.0F, 0.0F, 0.0F);
      this.EarR = new ModelRenderer(this, 0, 42);
      this.EarR.addBox(1.5F, -7.0F, -3.0F, 4, 3, 0);
      this.EarR.setRotationPoint(0.0F, 5.0F, -10.0F);
      this.EarR.setTextureSize(128, 64);
      this.EarR.mirror = true;
      this.setRotation(this.EarR, 0.0F, 0.0F, 0.0F);
      this.Soul = new ModelRenderer(this, 0, 45);
      this.Soul.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5);
      this.Soul.setRotationPoint(0.0F, 5.0F, 3.0F);
      this.Soul.setTextureSize(128, 64);
      this.Soul.mirror = true;
      this.setRotation(this.Soul, 0.0F, 0.0F, 0.0F);
      this.Head.addChild(this.Snout);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5);
      this.Head.render(f5);
      this.Body_A.render(f5);
      this.Body_B.render(f5);
      this.LegR.render(f5);
      this.LegL.render(f5);
      this.Snout.render(f5);
      this.LegRB.render(f5);
      this.LegLB.render(f5);
      this.Tail.render(f5);
      this.EarL.render(f5);
      this.EarR.render(f5);
      this.Soul.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, null);
      this.Head.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Head.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.EarR.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.EarR.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.EarL.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.EarL.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.LegR.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.LegL.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.LegRB.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.LegLB.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.Soul.rotateAngleY = par3;
   }
}
