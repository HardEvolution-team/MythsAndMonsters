package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelKitsune extends ModelBase {
   ModelRenderer Front_Right_Leg;
   ModelRenderer Front_Left_Leg;
   ModelRenderer Back_Left_leg;
   ModelRenderer Back_Right_Leg;
   ModelRenderer Body;
   ModelRenderer Head;
   ModelRenderer Mouth;
   ModelRenderer Nose;
   ModelRenderer Right_Ear;
   ModelRenderer Left_Ear;
   ModelRenderer Middle_Tail;
   ModelRenderer Left_Tail;
   ModelRenderer Right_Tail;
   ModelRenderer Left_Cheek;
   ModelRenderer Right_Cheek;

   public ModelKitsune() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Front_Right_Leg = new ModelRenderer(this, 0, 0);
      this.Front_Right_Leg.addBox(-2.0F, 0.0F, -1.5F, 3, 6, 3);
      this.Front_Right_Leg.setRotationPoint(-1.0F, 18.0F, -5.0F);
      this.Front_Right_Leg.setTextureSize(64, 32);
      this.Front_Right_Leg.mirror = true;
      this.setRotation(this.Front_Right_Leg, 0.0F, 0.0F, 0.0F);
      this.Front_Left_Leg = new ModelRenderer(this, 0, 0);
      this.Front_Left_Leg.addBox(-1.0F, 0.0F, -1.5F, 3, 6, 3);
      this.Front_Left_Leg.setRotationPoint(1.0F, 18.0F, -5.0F);
      this.Front_Left_Leg.setTextureSize(64, 32);
      this.Front_Left_Leg.mirror = true;
      this.setRotation(this.Front_Left_Leg, 0.0F, 0.0F, 0.0F);
      this.Back_Left_leg = new ModelRenderer(this, 0, 0);
      this.Back_Left_leg.addBox(-1.0F, 0.0F, -1.5F, 3, 6, 3);
      this.Back_Left_leg.setRotationPoint(1.0F, 18.0F, 2.0F);
      this.Back_Left_leg.setTextureSize(64, 32);
      this.Back_Left_leg.mirror = true;
      this.setRotation(this.Back_Left_leg, 0.0F, 0.0F, 0.0F);
      this.Back_Right_Leg = new ModelRenderer(this, 0, 0);
      this.Back_Right_Leg.addBox(-2.0F, 0.0F, -1.5F, 3, 6, 3);
      this.Back_Right_Leg.setRotationPoint(-1.0F, 18.0F, 2.0F);
      this.Back_Right_Leg.setTextureSize(64, 32);
      this.Back_Right_Leg.mirror = true;
      this.setRotation(this.Back_Right_Leg, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 11);
      this.Body.addBox(-3.0F, -2.0F, -5.0F, 6, 5, 10);
      this.Body.setRotationPoint(0.0F, 15.0F, -2.0F);
      this.Body.setTextureSize(64, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.Head = new ModelRenderer(this, 25, 11);
      this.Head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 4);
      this.Head.setRotationPoint(0.0F, 14.0F, -7.0F);
      this.Head.setTextureSize(64, 32);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Mouth = new ModelRenderer(this, 9, 27);
      this.Mouth.addBox(-1.5F, -2.0F, -3.0F, 3, 2, 3);
      this.Mouth.setRotationPoint(0.0F, 15.5F, -10.0F);
      this.Mouth.setTextureSize(64, 32);
      this.Mouth.mirror = true;
      this.setRotation(this.Mouth, 0.0F, 0.0F, 0.0F);
      this.Nose = new ModelRenderer(this, 0, 27);
      this.Nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
      this.Nose.setRotationPoint(0.0F, 13.5F, -13.0F);
      this.Nose.setTextureSize(64, 32);
      this.Nose.mirror = true;
      this.setRotation(this.Nose, 0.0F, 0.0F, 0.0F);
      this.Right_Ear = new ModelRenderer(this, 12, 0);
      this.Right_Ear.addBox(-2.0F, -2.0F, 0.0F, 3, 3, 1);
      this.Right_Ear.setRotationPoint(-2.0F, 10.0F, -8.0F);
      this.Right_Ear.setTextureSize(64, 32);
      this.Right_Ear.mirror = true;
      this.setRotation(this.Right_Ear, 0.2602503F, 0.2230717F, 0.0F);
      this.Left_Ear = new ModelRenderer(this, 12, 5);
      this.Left_Ear.addBox(0.0F, -3.0F, 0.0F, 3, 3, 1);
      this.Left_Ear.setRotationPoint(1.0F, 11.0F, -8.0F);
      this.Left_Ear.setTextureSize(64, 32);
      this.Left_Ear.mirror = true;
      this.setRotation(this.Left_Ear, 0.2602503F, -0.2094395F, 0.0F);
      this.Middle_Tail = new ModelRenderer(this, 25, 0);
      this.Middle_Tail.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
      this.Middle_Tail.setRotationPoint(0.0F, 14.0F, 2.0F);
      this.Middle_Tail.setTextureSize(64, 32);
      this.Middle_Tail.mirror = true;
      this.setRotation(this.Middle_Tail, -0.4833219F, 0.0F, 0.0F);
      this.Left_Tail = new ModelRenderer(this, 25, 0);
      this.Left_Tail.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
      this.Left_Tail.setRotationPoint(1.0F, 14.0F, 2.0F);
      this.Left_Tail.setTextureSize(64, 32);
      this.Left_Tail.mirror = true;
      this.setRotation(this.Left_Tail, -0.5948578F, 0.4461433F, 0.1487144F);
      this.Right_Tail = new ModelRenderer(this, 25, 0);
      this.Right_Tail.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
      this.Right_Tail.setRotationPoint(-1.0F, 14.0F, 2.0F);
      this.Right_Tail.setTextureSize(64, 32);
      this.Right_Tail.mirror = true;
      this.setRotation(this.Right_Tail, -0.5948524F, -0.4461433F, -0.1487144F);
      this.Left_Cheek = new ModelRenderer(this, 34, 0);
      this.Left_Cheek.addBox(-0.5F, -1.0F, -4.9F, 3, 3, 2);
      this.Left_Cheek.setRotationPoint(0.0F, 14.0F, -7.0F);
      this.Left_Cheek.setTextureSize(64, 32);
      this.Left_Cheek.mirror = true;
      this.setRotation(this.Left_Cheek, 0.0F, -0.7435722F, 0.0F);
      this.Right_Cheek = new ModelRenderer(this, 45, 0);
      this.Right_Cheek.addBox(-2.5F, -1.0F, -4.9F, 3, 3, 2);
      this.Right_Cheek.setRotationPoint(0.0F, 14.0F, -7.0F);
      this.Right_Cheek.setTextureSize(64, 32);
      this.Right_Cheek.mirror = true;
      this.setRotation(this.Right_Cheek, 0.0F, 0.7610334F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Front_Right_Leg.render(f5);
      this.Front_Left_Leg.render(f5);
      this.Back_Left_leg.render(f5);
      this.Back_Right_Leg.render(f5);
      this.Body.render(f5);
      this.Head.render(f5);
      this.Mouth.render(f5);
      this.Nose.render(f5);
      this.Right_Ear.render(f5);
      this.Left_Ear.render(f5);
      this.Middle_Tail.render(f5);
      this.Left_Tail.render(f5);
      this.Right_Tail.render(f5);
      this.Left_Cheek.render(f5);
      this.Right_Cheek.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Front_Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.Front_Left_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
      this.Back_Left_leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
      this.Back_Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.Left_Tail.rotateAngleZ = MathHelper.cos(f2 * 1.0F * 0.5F * 0.6662F) * 0.2F;
      this.Middle_Tail.rotateAngleZ = MathHelper.cos(f2 * 1.0F * 0.5F * 0.6662F) * 0.2F;
      this.Right_Tail.rotateAngleZ = MathHelper.cos(f2 * 1.0F * 0.5F * 0.6662F) * 0.2F;
   }
}
