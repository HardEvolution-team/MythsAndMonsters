package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelJackalopeOld extends ModelBase {
   ModelRenderer HEAD;
   ModelRenderer EAR_LEFT;
   ModelRenderer EAR_RIGHT;
   ModelRenderer MOUTH;
   ModelRenderer ANTLER_LEFT;
   ModelRenderer ANTLER_RIGHT;
   ModelRenderer BODY;
   ModelRenderer LEFT_LEG_2;
   ModelRenderer LEFT_LEG_1;
   ModelRenderer RIGHT_LEG_2;
   ModelRenderer RIGHT_LEG_1;
   ModelRenderer TEETH;

   public ModelJackalopeOld() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.HEAD = new ModelRenderer(this, 31, 0);
      this.HEAD.addBox(-2.0F, -4.0F, -7.0F, 6, 6, 7);
      this.HEAD.setRotationPoint(-1.0F, 13.0F, -2.0F);
      this.HEAD.setTextureSize(64, 32);
      this.HEAD.mirror = true;
      this.setRotation(this.HEAD, 0.0F, 0.0F, 0.0F);
      this.EAR_LEFT = new ModelRenderer(this, 0, 20);
      this.EAR_LEFT.addBox(0.5F, -5.0F, 0.0F, 2, 5, 1);
      this.EAR_LEFT.setRotationPoint(1.5F, -4.0F, -1.0F);
      this.EAR_LEFT.setTextureSize(64, 32);
      this.EAR_LEFT.mirror = true;
      this.setRotation(this.EAR_LEFT, 0.0F, 0.0F, 0.0F);
      this.EAR_RIGHT = new ModelRenderer(this, 0, 20);
      this.EAR_RIGHT.addBox(-0.5F, -5.0F, 0.0F, 2, 5, 1);
      this.EAR_RIGHT.setRotationPoint(-1.5F, -4.0F, -1.0F);
      this.EAR_RIGHT.setTextureSize(64, 32);
      this.EAR_RIGHT.mirror = true;
      this.setRotation(this.EAR_RIGHT, 0.0F, 0.0F, 0.0F);
      this.MOUTH = new ModelRenderer(this, 31, 15);
      this.MOUTH.addBox(-1.0F, 0.0F, 0.0F, 4, 3, 1);
      this.MOUTH.setRotationPoint(0.0F, -1.0F, -8.0F);
      this.MOUTH.setTextureSize(64, 32);
      this.MOUTH.mirror = true;
      this.setRotation(this.MOUTH, 0.0F, 0.0F, 0.0F);
      this.ANTLER_LEFT = new ModelRenderer(this, 44, 15);
      this.ANTLER_LEFT.addBox(1.0F, 0.0F, 0.0F, 1, 6, 1);
      this.ANTLER_LEFT.setRotationPoint(1.0F, -10.0F, -4.0F);
      this.ANTLER_LEFT.setTextureSize(64, 32);
      this.ANTLER_LEFT.mirror = true;
      this.setRotation(this.ANTLER_LEFT, 0.0F, 0.0F, 0.0F);
      this.ANTLER_RIGHT = new ModelRenderer(this, 44, 15);
      this.ANTLER_RIGHT.addBox(1.0F, 0.0F, 0.0F, 1, 6, 1);
      this.ANTLER_RIGHT.setRotationPoint(-2.0F, -10.0F, -4.0F);
      this.ANTLER_RIGHT.setTextureSize(64, 32);
      this.ANTLER_RIGHT.mirror = true;
      this.setRotation(this.ANTLER_RIGHT, 0.0F, 0.0F, 0.0F);
      this.BODY = new ModelRenderer(this, 0, 0);
      this.BODY.addBox(-2.0F, 0.0F, 0.0F, 6, 6, 9);
      this.BODY.setRotationPoint(-1.0F, 11.0F, -2.0F);
      this.BODY.setTextureSize(64, 32);
      this.BODY.mirror = true;
      this.setRotation(this.BODY, 0.0F, 0.0F, 0.0F);
      this.LEFT_LEG_2 = new ModelRenderer(this, 26, 20);
      this.LEFT_LEG_2.addBox(1.0F, 0.0F, -1.0F, 3, 8, 3);
      this.LEFT_LEG_2.setRotationPoint(2.0F, 16.0F, 5.0F);
      this.LEFT_LEG_2.setTextureSize(64, 32);
      this.LEFT_LEG_2.mirror = true;
      this.setRotation(this.LEFT_LEG_2, 0.0F, 0.0F, 0.0F);
      this.LEFT_LEG_1 = new ModelRenderer(this, 10, 20);
      this.LEFT_LEG_1.addBox(1.0F, 0.0F, -2.0F, 3, 8, 3);
      this.LEFT_LEG_1.setRotationPoint(1.0F, 16.0F, 0.0F);
      this.LEFT_LEG_1.setTextureSize(64, 32);
      this.LEFT_LEG_1.mirror = true;
      this.setRotation(this.LEFT_LEG_1, 0.0F, 0.0F, 0.0F);
      this.RIGHT_LEG_2 = new ModelRenderer(this, 26, 20);
      this.RIGHT_LEG_2.addBox(-2.0F, 0.0F, -1.0F, 3, 8, 3);
      this.RIGHT_LEG_2.setRotationPoint(-4.0F, 16.0F, 5.0F);
      this.RIGHT_LEG_2.setTextureSize(64, 32);
      this.RIGHT_LEG_2.mirror = true;
      this.setRotation(this.RIGHT_LEG_2, 0.0F, 0.0F, 0.0F);
      this.RIGHT_LEG_1 = new ModelRenderer(this, 10, 20);
      this.RIGHT_LEG_1.addBox(-2.0F, 0.0F, -2.0F, 3, 8, 3);
      this.RIGHT_LEG_1.setRotationPoint(-3.0F, 16.0F, 0.0F);
      this.RIGHT_LEG_1.setTextureSize(64, 32);
      this.RIGHT_LEG_1.mirror = true;
      this.setRotation(this.RIGHT_LEG_1, 0.0F, 0.0F, 0.0F);
      this.TEETH = new ModelRenderer(this, 14, 17);
      this.TEETH.addBox(0.0F, 0.0F, -1.0F, 2, 1, 1);
      this.TEETH.setRotationPoint(0.0F, 2.0F, -7.0F);
      this.TEETH.setTextureSize(64, 32);
      this.TEETH.mirror = true;
      this.setRotation(this.TEETH, 0.0F, 0.0F, 0.0F);
      this.HEAD.addChild(this.ANTLER_LEFT);
      this.HEAD.addChild(this.ANTLER_RIGHT);
      this.HEAD.addChild(this.MOUTH);
      this.HEAD.addChild(this.TEETH);
      this.HEAD.addChild(this.EAR_LEFT);
      this.HEAD.addChild(this.EAR_RIGHT);
   }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      this.HEAD.render(par7);
      this.BODY.render(par7);
      this.LEFT_LEG_2.render(par7);
      this.LEFT_LEG_1.render(par7);
      this.RIGHT_LEG_2.render(par7);
      this.RIGHT_LEG_1.render(par7);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      this.HEAD.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.HEAD.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      if (par7Entity.isInWater()) {
         this.RIGHT_LEG_1.rotateAngleX = MathHelper.cos(par1 * 2.0F * 0.6662F + (float) Math.PI) * 2.0F * par2;
         this.RIGHT_LEG_2.rotateAngleX = MathHelper.cos(par1 * 2.0F * 0.6662F) * 2.0F * par2;
         this.LEFT_LEG_1.rotateAngleX = MathHelper.cos(par1 * 2.0F * 0.6662F) * 2.0F * par2;
         this.LEFT_LEG_2.rotateAngleX = MathHelper.cos(par1 * 2.0F * 0.6662F + (float) Math.PI) * 2.0F * par2;
      } else if (par7Entity.onGround) {
         this.RIGHT_LEG_2.rotateAngleY = -0.15F;
         this.LEFT_LEG_2.rotateAngleY = 0.15F;
         this.RIGHT_LEG_1.rotateAngleX = MathHelper.cos(par1 * 2.0F * 0.6662F + (float) Math.PI) * 2.0F * par2;
         this.RIGHT_LEG_2.rotateAngleX = Math.abs(MathHelper.cos(par1 * 2.0F * 0.6662F * 0.5F) * 2.0F * par2);
         this.LEFT_LEG_1.rotateAngleX = MathHelper.cos(par1 * 2.0F * 0.6662F + (float) Math.PI) * 2.0F * par2;
         this.LEFT_LEG_2.rotateAngleX = Math.abs(MathHelper.cos(par1 * 2.0F * 0.6662F * 0.5F) * 2.0F * par2);
      } else {
         float jumpFloat = MathHelper.clamp(((float)par7Entity.motionY + 0.1F) * 2.0F, -0.523F, 0.523F);
         this.RIGHT_LEG_1.rotateAngleX = jumpFloat;
         this.RIGHT_LEG_2.rotateAngleX = jumpFloat;
         this.LEFT_LEG_1.rotateAngleX = jumpFloat;
         this.LEFT_LEG_2.rotateAngleX = jumpFloat;
         this.RIGHT_LEG_1.rotateAngleY = 0.0F;
         this.LEFT_LEG_1.rotateAngleY = 0.0F;
         if (jumpFloat > 0.0F) {
            this.RIGHT_LEG_2.rotateAngleY = -0.35F;
            this.LEFT_LEG_2.rotateAngleY = 0.35F;
         } else {
            this.RIGHT_LEG_2.rotateAngleY = 0.0F;
            this.LEFT_LEG_2.rotateAngleY = 0.0F;
         }
      }
   }
}
