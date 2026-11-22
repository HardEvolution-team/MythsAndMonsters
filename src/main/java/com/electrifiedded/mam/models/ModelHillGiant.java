package com.electrifiedded.mam.models;

import com.electrifiedded.mam.entity.EntityHillGiant;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHillGiant extends ModelBase {
   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;
   public int heldItemLeft = 0;
   public int heldItemRight = 0;

   public ModelHillGiant() {
      this.textureWidth = 128;
      this.textureHeight = 128;
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-7.0F, -14.0F, -7.0F, 14, 14, 14);
      this.head.setRotationPoint(0.0F, -30.0F, 0.0F);
      this.head.setTextureSize(128, 128);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 74, 0);
      this.body.addBox(-9.0F, 0.0F, -4.5F, 18, 27, 9);
      this.body.setRotationPoint(0.0F, -30.0F, 0.0F);
      this.body.setTextureSize(128, 128);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.rightarm = new ModelRenderer(this, 0, 64);
      this.rightarm.addBox(-11.0F, -4.0F, -4.0F, 8, 28, 8);
      this.rightarm.setRotationPoint(-9.0F, -26.0F, 0.0F);
      this.rightarm.setTextureSize(128, 128);
      this.rightarm.mirror = true;
      this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
      this.leftarm = new ModelRenderer(this, 36, 64);
      this.leftarm.addBox(3.0F, -4.0F, -4.0F, 8, 28, 8);
      this.leftarm.setRotationPoint(9.0F, -26.0F, 0.0F);
      this.leftarm.setTextureSize(128, 128);
      this.leftarm.mirror = true;
      this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
      this.rightleg = new ModelRenderer(this, 0, 28);
      this.rightleg.addBox(-4.5F, 0.0F, -4.5F, 9, 27, 9);
      this.rightleg.setRotationPoint(-4.5F, -3.0F, 0.0F);
      this.rightleg.setTextureSize(128, 128);
      this.rightleg.mirror = true;
      this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
      this.leftleg = new ModelRenderer(this, 36, 28);
      this.leftleg.addBox(-4.5F, 0.0F, -4.5F, 9, 27, 9);
      this.leftleg.setRotationPoint(4.5F, -3.0F, 0.0F);
      this.leftleg.setTextureSize(128, 128);
      this.leftleg.mirror = true;
      this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5);
      this.head.render(f5);
      this.body.render(f5);
      this.rightarm.render(f5);
      this.leftarm.render(f5);
      this.rightleg.render(f5);
      this.leftleg.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setLivingAnimations(net.minecraft.entity.EntityLivingBase living, float par2, float par3, float par4) {
      super.setLivingAnimations(living, par2, par3, par4);
      if (!(living instanceof EntityHillGiant)) return;
      EntityHillGiant par1EntityLiving = (EntityHillGiant)living;
      int attack = par1EntityLiving.smashCounter / 10;
      this.rightarm.rotateAngleX = attack;
      this.leftarm.rotateAngleX = attack;
   }

   public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6) {
      this.head.rotateAngleY = f4 / (180.0F / (float)Math.PI);
      this.head.rotateAngleX = f5 / (180.0F / (float)Math.PI);
      this.rightarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float) Math.PI) * 2.0F * f2 * 0.5F;
      this.leftarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 2.0F * f2 * 0.5F;
      this.rightarm.rotateAngleZ = 0.0F;
      this.leftarm.rotateAngleZ = 0.0F;
      this.rightleg.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f2;
      this.leftleg.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float) Math.PI) * 1.4F * f2;
      this.rightleg.rotateAngleY = 0.0F;
      this.leftleg.rotateAngleY = 0.0F;
      this.rightarm.rotateAngleY = 0.0F;
      this.leftarm.rotateAngleY = 0.0F;
      if (this.swingProgress > -9990.0F) {
         float var8 = this.swingProgress;
         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(var8) * (float) Math.PI * 2.0F) * 0.2F;
         this.rightarm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
         this.rightarm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
         this.leftarm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
         this.leftarm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
         this.rightarm.rotateAngleY = this.rightarm.rotateAngleY + this.body.rotateAngleY;
         this.leftarm.rotateAngleY = this.leftarm.rotateAngleY + this.body.rotateAngleY;
         this.leftarm.rotateAngleX = this.leftarm.rotateAngleX + this.body.rotateAngleY;
         var8 = 1.0F - this.swingProgress;
         var8 *= var8;
         var8 *= var8;
         var8 = 1.0F - var8;
         float var9 = MathHelper.sin(var8 * (float) Math.PI);
         float var10 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
         this.rightarm.rotateAngleX = (float)(this.rightarm.rotateAngleX - (var9 * 1.2 + var10));
         this.rightarm.rotateAngleY = this.rightarm.rotateAngleY + this.body.rotateAngleY * 2.0F;
         this.rightarm.rotateAngleZ = MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
      }

      this.rightarm.rotateAngleZ = this.rightarm.rotateAngleZ + (MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F);
      this.leftarm.rotateAngleZ = this.leftarm.rotateAngleZ - (MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F);
      this.rightarm.rotateAngleX = this.rightarm.rotateAngleX + MathHelper.sin(f3 * 0.067F) * 0.05F;
      this.leftarm.rotateAngleX = this.leftarm.rotateAngleX - MathHelper.sin(f3 * 0.067F) * 0.05F;
   }
}
