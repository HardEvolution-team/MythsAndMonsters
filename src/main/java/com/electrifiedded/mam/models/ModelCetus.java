package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class ModelCetus extends ModelBase {
   ModelRenderer HEAD;
   ModelRenderer BODY;
   ModelRenderer TAIL_1;
   ModelRenderer TAIL_2;
   ModelRenderer FLIPPER_TAIL;
   ModelRenderer SHOULDER_LEFT;
   ModelRenderer SHOULD_RIGHT;
   ModelRenderer FOREARM_LEFT;
   ModelRenderer FOREARM_RIGHT;
   ModelRenderer TOOTH_BIG;
   ModelRenderer TOOTH_LEFT;
   ModelRenderer TOOTH_RIGHT;

   public ModelCetus() {
      this.textureWidth = 64;
      this.textureHeight = 128;
      this.HEAD = new ModelRenderer(this, 31, 0);
      this.HEAD.addBox(-3.0F, -4.0F, -7.0F, 6, 6, 7);
      this.HEAD.setRotationPoint(0.0F, 16.0F, -3.0F);
      this.HEAD.setTextureSize(64, 128);
      this.HEAD.mirror = true;
      this.setRotation(this.HEAD, 0.0F, 0.0F, 0.0F);
      this.BODY = new ModelRenderer(this, 0, 0);
      this.BODY.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 9);
      this.BODY.setRotationPoint(0.0F, 16.0F, -3.0F);
      this.BODY.setTextureSize(64, 128);
      this.BODY.mirror = true;
      this.setRotation(this.BODY, 0.0F, 0.0F, 0.0F);
      this.TAIL_1 = new ModelRenderer(this, 32, 15);
      this.TAIL_1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4);
      this.TAIL_1.setRotationPoint(0.0F, 3.0F, 9.0F);
      this.TAIL_1.setTextureSize(64, 128);
      this.TAIL_1.mirror = true;
      this.setRotation(this.TAIL_1, 0.0F, 0.0F, 0.0F);
      this.TAIL_2 = new ModelRenderer(this, 26, 24);
      this.TAIL_2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 6);
      this.TAIL_2.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.TAIL_2.setTextureSize(64, 128);
      this.TAIL_2.mirror = true;
      this.setRotation(this.TAIL_2, 0.0F, 0.0F, 0.0F);
      this.FLIPPER_TAIL = new ModelRenderer(this, 0, 40);
      this.FLIPPER_TAIL.addBox(-4.0F, -1.0F, 0.0F, 8, 1, 5);
      this.FLIPPER_TAIL.setRotationPoint(0.0F, 0.0F, 6.0F);
      this.FLIPPER_TAIL.setTextureSize(64, 128);
      this.FLIPPER_TAIL.mirror = true;
      this.setRotation(this.FLIPPER_TAIL, 0.0F, 0.0F, 0.0F);
      this.SHOULDER_LEFT = new ModelRenderer(this, 13, 24);
      this.SHOULDER_LEFT.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
      this.SHOULDER_LEFT.setRotationPoint(3.0F, 3.0F, 3.0F);
      this.SHOULDER_LEFT.setTextureSize(64, 128);
      this.SHOULDER_LEFT.mirror = true;
      this.setRotation(this.SHOULDER_LEFT, 0.0F, 0.0F, 0.0F);
      this.SHOULD_RIGHT = new ModelRenderer(this, 13, 24);
      this.SHOULD_RIGHT.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2);
      this.SHOULD_RIGHT.setRotationPoint(-3.0F, 3.0F, 3.0F);
      this.SHOULD_RIGHT.setTextureSize(64, 128);
      this.SHOULD_RIGHT.mirror = true;
      this.setRotation(this.SHOULD_RIGHT, 0.0F, 0.0F, 0.0F);
      this.FOREARM_LEFT = new ModelRenderer(this, 49, 24);
      this.FOREARM_LEFT.addBox(-2.0F, 0.0F, -5.0F, 2, 2, 5);
      this.FOREARM_LEFT.setRotationPoint(2.0F, 3.0F, 0.0F);
      this.FOREARM_LEFT.setTextureSize(64, 128);
      this.FOREARM_LEFT.mirror = true;
      this.setRotation(this.FOREARM_LEFT, 0.0F, 0.0F, 0.0F);
      this.FOREARM_RIGHT = new ModelRenderer(this, 49, 24);
      this.FOREARM_RIGHT.addBox(0.0F, 0.0F, -5.0F, 2, 2, 5);
      this.FOREARM_RIGHT.setRotationPoint(-2.0F, 3.0F, 0.0F);
      this.FOREARM_RIGHT.setTextureSize(64, 128);
      this.FOREARM_RIGHT.mirror = true;
      this.setRotation(this.FOREARM_RIGHT, 0.0F, 0.0F, 0.0F);
      this.TOOTH_BIG = new ModelRenderer(this, 12, 17);
      this.TOOTH_BIG.addBox(-0.5F, 0.0F, -4.0F, 1, 1, 4);
      this.TOOTH_BIG.setRotationPoint(0.0F, -4.0F, -7.0F);
      this.TOOTH_BIG.setTextureSize(64, 128);
      this.TOOTH_BIG.mirror = true;
      this.setRotation(this.TOOTH_BIG, 0.446143F, 0.0F, 0.0F);
      this.TOOTH_LEFT = new ModelRenderer(this, 0, 25);
      this.TOOTH_LEFT.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3);
      this.TOOTH_LEFT.setRotationPoint(1.5F, 1.5F, -7.0F);
      this.TOOTH_LEFT.setTextureSize(64, 128);
      this.TOOTH_LEFT.mirror = true;
      this.setRotation(this.TOOTH_LEFT, 0.0F, 0.0F, 0.0F);
      this.TOOTH_RIGHT = new ModelRenderer(this, 0, 25);
      this.TOOTH_RIGHT.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3);
      this.TOOTH_RIGHT.setRotationPoint(-1.5F, 1.5F, -7.0F);
      this.TOOTH_RIGHT.setTextureSize(64, 128);
      this.TOOTH_RIGHT.mirror = true;
      this.setRotation(this.TOOTH_RIGHT, 0.0F, 0.0F, 0.0F);
      this.HEAD.addChild(this.TOOTH_BIG);
      this.HEAD.addChild(this.TOOTH_RIGHT);
      this.HEAD.addChild(this.TOOTH_LEFT);
      this.BODY.addChild(this.TAIL_1);
      this.TAIL_1.addChild(this.TAIL_2);
      this.TAIL_2.addChild(this.FLIPPER_TAIL);
      this.BODY.addChild(this.SHOULD_RIGHT);
      this.BODY.addChild(this.SHOULDER_LEFT);
      this.SHOULD_RIGHT.addChild(this.FOREARM_RIGHT);
      this.SHOULDER_LEFT.addChild(this.FOREARM_LEFT);
   }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      this.HEAD.render(par7);
      this.BODY.render(par7);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      if (par7Entity.isRiding() && par7Entity.getRidingEntity() instanceof EntityPlayer) {
         this.HEAD.rotateAngleY = par7Entity.getRidingEntity().rotationYaw * (float) Math.PI / 180.0F;
         this.BODY.rotateAngleY = par7Entity.getRidingEntity().rotationYaw * (float) Math.PI / 180.0F;
         this.HEAD.setRotationPoint(0.0F, 37.0F, 0.0F);
         this.BODY.setRotationPoint(0.0F, 37.0F, 0.0F);
         this.HEAD.rotateAngleX = 1.57F;
         this.BODY.rotateAngleX = 1.57F;
      } else {
         this.HEAD.setRotationPoint(0.0F, 16.0F, -3.0F);
         this.BODY.setRotationPoint(0.0F, 16.0F, -3.0F);
         this.HEAD.rotateAngleY = par4 / (180.0F / (float)Math.PI);
         this.HEAD.rotateAngleX = par5 / (180.0F / (float)Math.PI);
         this.BODY.rotateAngleY = 0.0F;
         this.BODY.rotateAngleX = 0.0F;
      }

      this.FOREARM_RIGHT.rotateAngleX = 0.5F;
      this.FOREARM_LEFT.rotateAngleX = 0.5F;
      this.SHOULD_RIGHT.rotateAngleX = par2 * 3.0F;
      this.SHOULDER_LEFT.rotateAngleX = par2 * 3.0F;
      this.SHOULD_RIGHT.rotateAngleZ = MathHelper.sin(par1 * 0.6662F) * 0.8F * par2;
      this.SHOULDER_LEFT.rotateAngleZ = -this.SHOULD_RIGHT.rotateAngleZ;
      this.TAIL_1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 0.4F * par2;
      this.TAIL_2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 0.8F * par2;
      this.FLIPPER_TAIL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
   }
}
