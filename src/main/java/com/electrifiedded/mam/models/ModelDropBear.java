package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDropBear extends ModelBase {
   ModelRenderer Forearm_left;
   ModelRenderer Forearm_Right;
   ModelRenderer Ear_1;
   ModelRenderer Ear_2;
   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer leg1;
   ModelRenderer leg2;
   ModelRenderer Right_Arm;
   ModelRenderer Left_Arm;

   public ModelDropBear() {
      this.textureWidth = 128;
      this.textureHeight = 32;
      this.Forearm_left = new ModelRenderer(this, 0, 0);
      this.Forearm_left.addBox(-2.0F, 4.0F, 1.0F, 4, 9, 4);
      this.Forearm_left.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Forearm_left.setTextureSize(128, 32);
      this.Forearm_left.mirror = true;
      this.setRotation(this.Forearm_left, -0.669215F, 0.0F, 0.0F);
      this.Forearm_Right = new ModelRenderer(this, 0, 0);
      this.Forearm_Right.addBox(-2.0F, 4.0F, 1.0F, 4, 9, 4);
      this.Forearm_Right.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Forearm_Right.setTextureSize(128, 32);
      this.Forearm_Right.mirror = true;
      this.setRotation(this.Forearm_Right, -0.669215F, 0.0F, 0.0F);
      this.Ear_1 = new ModelRenderer(this, 35, 0);
      this.Ear_1.addBox(2.0F, -8.0F, -3.0F, 5, 5, 1);
      this.Ear_1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Ear_1.setTextureSize(128, 32);
      this.Ear_1.mirror = true;
      this.setRotation(this.Ear_1, 0.0F, 0.0F, 0.0F);
      this.Ear_2 = new ModelRenderer(this, 52, 0);
      this.Ear_2.addBox(-7.0F, -8.0F, -3.0F, 5, 5, 1);
      this.Ear_2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Ear_2.setTextureSize(128, 32);
      this.Ear_2.mirror = true;
      this.setRotation(this.Ear_2, 0.0F, 0.0F, 0.0F);
      this.head = new ModelRenderer(this, 68, 0);
      this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 9);
      this.head.setRotationPoint(0.0F, 6.0F, 2.0F);
      this.head.setTextureSize(128, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 28, 8);
      this.body.addBox(-5.0F, -10.0F, -7.0F, 8, 12, 8);
      this.body.setRotationPoint(1.0F, 13.0F, 10.0F);
      this.body.setTextureSize(128, 32);
      this.body.mirror = true;
      this.setRotation(this.body, 0.6785097F, 0.0F, 0.0F);
      this.leg1 = new ModelRenderer(this, 0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.leg1.setRotationPoint(-3.0F, 18.0F, 7.0F);
      this.leg1.setTextureSize(128, 32);
      this.leg1.mirror = true;
      this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
      this.leg2 = new ModelRenderer(this, 0, 16);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.leg2.setRotationPoint(3.0F, 18.0F, 7.0F);
      this.leg2.setTextureSize(128, 32);
      this.leg2.mirror = true;
      this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
      this.Right_Arm = new ModelRenderer(this, 0, 16);
      this.Right_Arm.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.Right_Arm.setRotationPoint(-6.0F, 9.0F, 2.0F);
      this.Right_Arm.setTextureSize(128, 32);
      this.Right_Arm.mirror = true;
      this.setRotation(this.Right_Arm, 0.0F, 0.0F, 0.0F);
      this.Left_Arm = new ModelRenderer(this, 0, 16);
      this.Left_Arm.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.Left_Arm.setRotationPoint(6.0F, 9.0F, 2.0F);
      this.Left_Arm.setTextureSize(128, 32);
      this.Left_Arm.mirror = true;
      this.setRotation(this.Left_Arm, 0.0F, 0.0F, 0.0F);
      this.Right_Arm.addChild(this.Forearm_Right);
      this.Left_Arm.addChild(this.Forearm_left);
      this.head.addChild(this.Ear_1);
      this.head.addChild(this.Ear_2);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.body.render(f5);
      this.leg1.render(f5);
      this.leg2.render(f5);
      this.Right_Arm.render(f5);
      this.Left_Arm.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.rotateAngleY = f3 / (180.0F / (float)Math.PI);
      this.head.rotateAngleX = f4 / (180.0F / (float)Math.PI);
      this.Right_Arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
      this.Left_Arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.Right_Arm.rotateAngleZ = 0.0F;
      this.Left_Arm.rotateAngleZ = 0.0F;
      this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
      this.leg1.rotateAngleY = 0.0F;
      this.leg2.rotateAngleY = 0.0F;
   }
}
