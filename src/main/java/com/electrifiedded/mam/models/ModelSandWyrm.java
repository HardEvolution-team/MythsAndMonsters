package com.electrifiedded.mam.models;

import com.electrifiedded.mam.entity.EntitySandWyrm;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;

public class ModelSandWyrm extends ModelBase {
   ModelRenderer TuskLC;
   ModelRenderer Head;
   ModelRenderer TuskL;
   ModelRenderer Main_Body;
   ModelRenderer FinL;
   ModelRenderer FinR;
   ModelRenderer Body_1;
   ModelRenderer Main_Body_2;
   ModelRenderer FinL_2;
   ModelRenderer FinR_2;
   ModelRenderer Body_2;
   ModelRenderer TailR;
   ModelRenderer TailL;
   ModelRenderer TuskRC;
   ModelRenderer TuskR;
   ModelRenderer Jaw;

   public ModelSandWyrm() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.TuskLC = new ModelRenderer(this, 40, 19);
      this.TuskLC.addBox(-2.0F, 0.0F, -1.0F, 2, 3, 1);
      this.TuskLC.setRotationPoint(-4.0F, 2.0F, 0.0F);
      this.TuskLC.setTextureSize(128, 64);
      this.TuskLC.mirror = true;
      this.setRotation(this.TuskLC, 0.0F, 0.0F, 0.0F);
      this.Head = new ModelRenderer(this, 0, 0);
      this.Head.addBox(-5.0F, -3.0F, -14.0F, 10, 5, 14);
      this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Head.setTextureSize(128, 64);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0373999F, 0.0F);
      this.TuskL = new ModelRenderer(this, 48, 0);
      this.TuskL.addBox(-3.0F, 1.0F, -13.0F, 2, 2, 12);
      this.TuskL.setRotationPoint(-4.0F, 2.0F, 0.0F);
      this.TuskL.setTextureSize(128, 64);
      this.TuskL.mirror = true;
      this.setRotation(this.TuskL, 0.0F, 0.0F, 0.0F);
      this.Main_Body = new ModelRenderer(this, 76, 0);
      this.Main_Body.addBox(-6.0F, -5.0F, 0.0F, 12, 10, 9);
      this.Main_Body.setRotationPoint(0.0F, 18.0F, 0.0F);
      this.Main_Body.setTextureSize(128, 64);
      this.Main_Body.mirror = true;
      this.setRotation(this.Main_Body, 0.0F, 0.0F, 0.0F);
      this.FinL = new ModelRenderer(this, 48, 14);
      this.FinL.addBox(0.0F, 0.0F, 0.0F, 4, 6, 10);
      this.FinL.setRotationPoint(6.0F, 0.0F, 4.0F);
      this.FinL.setTextureSize(128, 64);
      this.FinL.mirror = true;
      this.setRotation(this.FinL, 0.0F, 0.0F, 0.0F);
      this.FinR = new ModelRenderer(this, 48, 14);
      this.FinR.addBox(0.0F, 0.0F, 0.0F, 4, 6, 10);
      this.FinR.setRotationPoint(-10.0F, 0.0F, 4.0F);
      this.FinR.setTextureSize(128, 64);
      this.FinR.mirror = true;
      this.setRotation(this.FinR, 0.0F, 0.0F, 0.0F);
      this.Body_1 = new ModelRenderer(this, 0, 33);
      this.Body_1.addBox(-5.0F, -4.0F, 0.0F, 10, 8, 14);
      this.Body_1.setRotationPoint(0.0F, 0.0F, 9.0F);
      this.Body_1.setTextureSize(128, 64);
      this.Body_1.mirror = true;
      this.setRotation(this.Body_1, 0.0F, 0.0F, 0.0F);
      this.Main_Body_2 = new ModelRenderer(this, 76, 0);
      this.Main_Body_2.addBox(-6.0F, -5.0F, 0.0F, 12, 10, 9);
      this.Main_Body_2.setRotationPoint(0.0F, 0.0F, 14.0F);
      this.Main_Body_2.setTextureSize(128, 64);
      this.Main_Body_2.mirror = true;
      this.setRotation(this.Main_Body_2, 0.0F, 0.0F, -0.0349066F);
      this.FinL_2 = new ModelRenderer(this, 48, 14);
      this.FinL_2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 10);
      this.FinL_2.setRotationPoint(6.0F, 0.0F, 0.0F);
      this.FinL_2.setTextureSize(128, 64);
      this.FinL_2.mirror = true;
      this.setRotation(this.FinL_2, 0.0F, 0.0F, -0.0349066F);
      this.FinR_2 = new ModelRenderer(this, 48, 14);
      this.FinR_2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 10);
      this.FinR_2.setRotationPoint(-10.0F, 0.0F, 0.0F);
      this.FinR_2.setTextureSize(128, 64);
      this.FinR_2.mirror = true;
      this.setRotation(this.FinR_2, 0.0F, 0.0F, -0.0349066F);
      this.Body_2 = new ModelRenderer(this, 48, 33);
      this.Body_2.addBox(-5.0F, -4.0F, 0.0F, 10, 8, 11);
      this.Body_2.setRotationPoint(0.0F, 0.0F, 9.0F);
      this.Body_2.setTextureSize(128, 64);
      this.Body_2.mirror = true;
      this.setRotation(this.Body_2, 0.0F, 0.0F, 0.0F);
      this.TailR = new ModelRenderer(this, 76, 19);
      this.TailR.addBox(1.0F, -1.5F, 0.0F, 6, 3, 11);
      this.TailR.setRotationPoint(0.0F, 0.0F, 11.0F);
      this.TailR.setTextureSize(128, 64);
      this.TailR.mirror = true;
      this.setRotation(this.TailR, 0.0F, 0.0F, 0.0F);
      this.TailL = new ModelRenderer(this, 76, 19);
      this.TailL.addBox(-7.0F, -1.5F, 0.0F, 6, 3, 11);
      this.TailL.setRotationPoint(0.0F, 0.0F, 11.0F);
      this.TailL.setTextureSize(128, 64);
      this.TailL.mirror = true;
      this.setRotation(this.TailL, 0.0F, 0.0F, 0.0F);
      this.TuskRC = new ModelRenderer(this, 40, 19);
      this.TuskRC.addBox(0.0F, 0.0F, -1.0F, 2, 3, 1);
      this.TuskRC.setRotationPoint(4.0F, 2.0F, 0.0F);
      this.TuskRC.setTextureSize(128, 64);
      this.TuskRC.mirror = true;
      this.setRotation(this.TuskRC, 0.0F, 0.0F, 0.0F);
      this.TuskR = new ModelRenderer(this, 48, 0);
      this.TuskR.addBox(1.0F, 1.0F, -13.0F, 2, 2, 12);
      this.TuskR.setRotationPoint(4.0F, 2.0F, 0.0F);
      this.TuskR.setTextureSize(128, 64);
      this.TuskR.mirror = true;
      this.setRotation(this.TuskR, 0.0F, 0.0F, 0.0F);
      this.Jaw = new ModelRenderer(this, 0, 19);
      this.Jaw.addBox(-4.0F, 0.0F, -12.0F, 8, 2, 12);
      this.Jaw.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.Jaw.setTextureSize(128, 64);
      this.Jaw.mirror = true;
      this.setRotation(this.Jaw, 0.0F, 0.0F, 0.0F);
      this.Head.addChild(this.Jaw);
      this.Head.addChild(this.TuskR);
      this.Head.addChild(this.TuskL);
      this.Head.addChild(this.TuskRC);
      this.Head.addChild(this.TuskLC);
      this.Main_Body.addChild(this.Head);
      this.Main_Body.addChild(this.FinR);
      this.Main_Body.addChild(this.FinL);
      this.Main_Body.addChild(this.Body_1);
      this.Body_1.addChild(this.Main_Body_2);
      this.Main_Body_2.addChild(this.FinR_2);
      this.Main_Body_2.addChild(this.FinL_2);
      this.Main_Body_2.addChild(this.Body_2);
      this.Body_2.addChild(this.TailR);
      this.Body_2.addChild(this.TailL);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5);
      this.Main_Body.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
      this.Head.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Head.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.TailR.rotateAngleX = MathHelper.cos(par1 * 1.2F + (float) Math.PI);
      this.TailL.rotateAngleX = MathHelper.cos(par1 * 1.2F + (float) Math.PI);
      this.FinR.rotateAngleX = -MathHelper.cos(par1 * 0.8F + (float) Math.PI);
      this.FinL.rotateAngleX = -MathHelper.cos(par1 * 0.8F + (float) Math.PI);
      this.FinR_2.rotateAngleX = MathHelper.sin(par1 * 0.8F + (float) Math.PI);
      this.FinL_2.rotateAngleX = MathHelper.sin(par1 * 0.8F + (float) Math.PI);
      this.Body_2.rotateAngleX = MathHelper.cos(par1 * 1.2F + (float) Math.PI);
   }

   public void setLivingAnimations(net.minecraft.entity.EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
      super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
      if (((EntitySandWyrm)par1EntityLiving).motionY <= -1.2) {
         this.Main_Body.rotateAngleX = 1.8F;
      } else {
         this.Main_Body.rotateAngleX = ((EntitySandWyrm)par1EntityLiving).getAnimation(par4);
      }
   }
}
