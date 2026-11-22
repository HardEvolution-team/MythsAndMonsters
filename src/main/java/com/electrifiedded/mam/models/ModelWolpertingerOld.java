package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWolpertingerOld extends ModelBase {
   ModelRenderer HEAD;
   ModelRenderer TEETH;
   ModelRenderer EAR_RIGHT;
   ModelRenderer EAR_LEFT;
   ModelRenderer HORN_LEFT;
   ModelRenderer HORN_RIGHT;
   ModelRenderer BODY;
   ModelRenderer WING_LEFT;
   ModelRenderer WING_RIGHT;
   ModelRenderer LEG_LEFT_1;
   ModelRenderer LEG_RIGHT_1;
   ModelRenderer LEG_LEFT_2;
   ModelRenderer LEG_RIGHT_2;
   ModelRenderer FOOT_LEFT_;
   ModelRenderer FOOT_RIGHT;
   ModelRenderer TAIL;

   public ModelWolpertingerOld() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.HEAD = new ModelRenderer(this, 24, 21);
      this.HEAD.addBox(-2.5F, -5.0F, -4.0F, 5, 5, 5);
      this.HEAD.setRotationPoint(0.0F, 17.0F, -4.0F);
      this.HEAD.setTextureSize(64, 32);
      this.HEAD.mirror = true;
      this.setRotation(this.HEAD, 0.0F, 0.0F, 0.0F);
      this.TEETH = new ModelRenderer(this, 10, 11);
      this.TEETH.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 1);
      this.TEETH.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.TEETH.setTextureSize(64, 32);
      this.TEETH.mirror = true;
      this.setRotation(this.TEETH, 0.0F, 0.0F, 0.0F);
      this.EAR_RIGHT = new ModelRenderer(this, 15, 0);
      this.EAR_RIGHT.addBox(0.0F, 0.0F, -1.0F, 1, 6, 2);
      this.EAR_RIGHT.setRotationPoint(2.0F, -5.0F, 0.0F);
      this.EAR_RIGHT.setTextureSize(64, 32);
      this.EAR_RIGHT.mirror = true;
      this.setRotation(this.EAR_RIGHT, 0.0F, 0.0F, 0.0F);
      this.EAR_LEFT = new ModelRenderer(this, 15, 0);
      this.EAR_LEFT.addBox(-1.0F, 0.0F, -1.0F, 1, 6, 2);
      this.EAR_LEFT.setRotationPoint(-2.0F, -5.0F, 0.0F);
      this.EAR_LEFT.setTextureSize(64, 32);
      this.EAR_LEFT.mirror = true;
      this.setRotation(this.EAR_LEFT, 0.0F, 0.0F, 0.0F);
      this.EAR_LEFT.mirror = false;
      this.HORN_LEFT = new ModelRenderer(this, 0, 9);
      this.HORN_LEFT.addBox(1.0F, -9.0F, -2.0F, 1, 4, 1);
      this.HORN_LEFT.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.HORN_LEFT.setTextureSize(64, 32);
      this.HORN_LEFT.mirror = true;
      this.setRotation(this.HORN_LEFT, 0.0F, 0.0F, 0.0F);
      this.HORN_LEFT.mirror = false;
      this.HORN_RIGHT = new ModelRenderer(this, 0, 9);
      this.HORN_RIGHT.addBox(-2.0F, -9.0F, -2.0F, 1, 4, 1);
      this.HORN_RIGHT.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.HORN_RIGHT.setTextureSize(64, 32);
      this.HORN_RIGHT.mirror = true;
      this.setRotation(this.HORN_RIGHT, 0.0F, 0.0F, 0.0F);
      this.BODY = new ModelRenderer(this, 0, 15);
      this.BODY.addBox(0.0F, 0.0F, 0.0F, 4, 5, 7);
      this.BODY.setRotationPoint(-2.0F, 14.0F, -3.0F);
      this.BODY.setTextureSize(64, 32);
      this.BODY.mirror = true;
      this.setRotation(this.BODY, 0.0F, 0.0F, 0.0F);
      this.WING_LEFT = new ModelRenderer(this, 24, 11);
      this.WING_LEFT.addBox(0.0F, -1.0F, 0.0F, 4, 1, 5);
      this.WING_LEFT.setRotationPoint(2.0F, 14.0F, -1.0F);
      this.WING_LEFT.setTextureSize(64, 32);
      this.WING_LEFT.mirror = true;
      this.setRotation(this.WING_LEFT, 0.0F, 0.0F, 0.0F);
      this.WING_LEFT.mirror = false;
      this.WING_RIGHT = new ModelRenderer(this, 24, 11);
      this.WING_RIGHT.addBox(-4.0F, -1.0F, 0.0F, 4, 1, 5);
      this.WING_RIGHT.setRotationPoint(-2.0F, 14.0F, -1.0F);
      this.WING_RIGHT.setTextureSize(64, 32);
      this.WING_RIGHT.mirror = true;
      this.setRotation(this.WING_RIGHT, 0.0F, 0.0F, 0.0F);
      this.LEG_LEFT_1 = new ModelRenderer(this, 42, 0);
      this.LEG_LEFT_1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.LEG_LEFT_1.setRotationPoint(1.0F, 19.0F, -3.0F);
      this.LEG_LEFT_1.setTextureSize(64, 32);
      this.LEG_LEFT_1.mirror = true;
      this.setRotation(this.LEG_LEFT_1, 0.0F, 0.0F, 0.0F);
      this.LEG_LEFT_1.mirror = false;
      this.LEG_RIGHT_1 = new ModelRenderer(this, 42, 0);
      this.LEG_RIGHT_1.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 1);
      this.LEG_RIGHT_1.setRotationPoint(-1.0F, 19.0F, -3.0F);
      this.LEG_RIGHT_1.setTextureSize(64, 32);
      this.LEG_RIGHT_1.mirror = true;
      this.setRotation(this.LEG_RIGHT_1, 0.0F, 0.0F, 0.0F);
      this.LEG_LEFT_2 = new ModelRenderer(this, 48, 0);
      this.LEG_LEFT_2.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
      this.LEG_LEFT_2.setRotationPoint(1.0F, 19.0F, 4.0F);
      this.LEG_LEFT_2.setTextureSize(64, 32);
      this.LEG_LEFT_2.mirror = true;
      this.setRotation(this.LEG_LEFT_2, 0.0F, 0.0F, 0.0F);
      this.LEG_LEFT_2.mirror = false;
      this.LEG_RIGHT_2 = new ModelRenderer(this, 48, 0);
      this.LEG_RIGHT_2.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
      this.LEG_RIGHT_2.setRotationPoint(-1.0F, 19.0F, 4.0F);
      this.LEG_RIGHT_2.setTextureSize(64, 32);
      this.LEG_RIGHT_2.mirror = true;
      this.setRotation(this.LEG_RIGHT_2, 0.0F, 0.0F, 0.0F);
      this.FOOT_LEFT_ = new ModelRenderer(this, 0, 0);
      this.FOOT_LEFT_.addBox(-1.0F, 0.0F, -2.0F, 3, 0, 3);
      this.FOOT_LEFT_.setRotationPoint(0.0F, 5.0F, 0.0F);
      this.FOOT_LEFT_.setTextureSize(64, 32);
      this.FOOT_LEFT_.mirror = true;
      this.setRotation(this.FOOT_LEFT_, 0.0F, 0.0F, 0.0F);
      this.FOOT_LEFT_.mirror = false;
      this.FOOT_RIGHT = new ModelRenderer(this, 0, 0);
      this.FOOT_RIGHT.addBox(-2.0F, 0.0F, -2.0F, 3, 0, 3);
      this.FOOT_RIGHT.setRotationPoint(0.0F, 5.0F, 0.0F);
      this.FOOT_RIGHT.setTextureSize(64, 32);
      this.FOOT_RIGHT.mirror = true;
      this.setRotation(this.FOOT_RIGHT, 0.0F, 0.0F, 0.0F);
      this.TAIL = new ModelRenderer(this, 24, 0);
      this.TAIL.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 6);
      this.TAIL.setRotationPoint(0.0F, 14.5F, 4.0F);
      this.TAIL.setTextureSize(64, 32);
      this.TAIL.mirror = true;
      this.setRotation(this.TAIL, 0.0F, 0.0F, 0.0F);
      this.HEAD.addChild(this.EAR_LEFT);
      this.HEAD.addChild(this.EAR_RIGHT);
      this.HEAD.addChild(this.TEETH);
      this.HEAD.addChild(this.HORN_RIGHT);
      this.HEAD.addChild(this.HORN_LEFT);
      this.LEG_LEFT_1.addChild(this.FOOT_LEFT_);
      this.LEG_RIGHT_1.addChild(this.FOOT_RIGHT);
   }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      this.HEAD.render(par7);
      this.BODY.render(par7);
      this.WING_LEFT.render(par7);
      this.WING_RIGHT.render(par7);
      this.LEG_LEFT_1.render(par7);
      this.LEG_RIGHT_1.render(par7);
      this.LEG_LEFT_2.render(par7);
      this.LEG_RIGHT_2.render(par7);
      this.TAIL.render(par7);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      this.HEAD.rotateAngleX = par5 / (180.0F / (float)Math.PI) + 0.26F;
      this.HEAD.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.TAIL.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      if (!par7Entity.onGround && !par7Entity.isInWater()) {
         this.WING_RIGHT.rotateAngleX = MathHelper.cos(par3 * 2.0F * 0.6662F) * 1.4F;
         this.WING_LEFT.rotateAngleX = this.WING_RIGHT.rotateAngleX;
         this.WING_RIGHT.rotateAngleZ = MathHelper.cos(par1 * 3.0F * 0.6662F) * 1.4F * par2;
         this.WING_LEFT.rotateAngleZ = -this.WING_RIGHT.rotateAngleX;
         this.EAR_RIGHT.rotateAngleZ = MathHelper.cos(par3 * 0.6662F) * 1.4F * par2 - 1.8F;
         this.EAR_LEFT.rotateAngleZ = -this.EAR_RIGHT.rotateAngleZ;
         this.LEG_RIGHT_1.rotateAngleX = this.LEG_LEFT_1.rotateAngleX = 0.6F;
         this.FOOT_RIGHT.rotateAngleX = this.FOOT_LEFT_.rotateAngleX = 1.2F;
         this.LEG_RIGHT_2.rotateAngleX = this.LEG_LEFT_2.rotateAngleX = 1.0F;
         this.TAIL.rotateAngleX = Math.abs(MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
      } else {
         this.WING_RIGHT.rotateAngleX = this.EAR_RIGHT.rotateAngleZ = this.WING_LEFT.rotateAngleX = this.EAR_LEFT.rotateAngleZ = 0.0F;
         this.WING_RIGHT.rotateAngleZ = -1.52F;
         this.WING_LEFT.rotateAngleZ = 1.52F;
         this.LEG_RIGHT_1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
         this.LEG_LEFT_1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
         this.FOOT_RIGHT.rotateAngleX = Math.abs(MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
         this.FOOT_LEFT_.rotateAngleX = Math.abs(MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2);
         this.LEG_LEFT_2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
         this.LEG_RIGHT_2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
         this.TAIL.rotateAngleX = -1.0F;
      }
   }
}
