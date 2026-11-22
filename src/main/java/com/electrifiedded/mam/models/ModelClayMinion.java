package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelClayMinion extends ModelBase {
   ModelRenderer Right_Leg;
   ModelRenderer Left_Leg;
   ModelRenderer Body;
   ModelRenderer Right_Arm;
   ModelRenderer Left_Arm;
   ModelRenderer Head;
   ModelRenderer Nose;
   ModelRenderer Chest;
   ModelRenderer Brow;

   public ModelClayMinion() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Right_Leg = new ModelRenderer(this, 40, 30);
      this.Right_Leg.addBox(-3.0F, 0.0F, -3.0F, 6, 18, 6);
      this.Right_Leg.setRotationPoint(-4.0F, 6.0F, 0.0F);
      this.Right_Leg.setTextureSize(128, 64);
      this.Right_Leg.mirror = true;
      this.setRotation(this.Right_Leg, 0.0F, 0.0F, 0.0F);
      this.Left_Leg = new ModelRenderer(this, 40, 30);
      this.Left_Leg.addBox(-3.0F, 0.0F, -3.0F, 6, 18, 6);
      this.Left_Leg.setRotationPoint(4.0F, 6.0F, 0.0F);
      this.Left_Leg.setTextureSize(128, 64);
      this.Left_Leg.mirror = true;
      this.setRotation(this.Left_Leg, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 28);
      this.Body.addBox(-7.0F, -10.0F, -3.0F, 14, 20, 6);
      this.Body.setRotationPoint(0.0F, -4.0F, 0.0F);
      this.Body.setTextureSize(128, 64);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.Right_Arm = new ModelRenderer(this, 0, 0);
      this.Right_Arm.addBox(-6.0F, -1.0F, -3.0F, 6, 21, 6);
      this.Right_Arm.setRotationPoint(-7.0F, -13.0F, 0.0F);
      this.Right_Arm.setTextureSize(128, 64);
      this.Right_Arm.mirror = true;
      this.setRotation(this.Right_Arm, 0.0F, 0.0F, 0.0F);
      this.Left_Arm = new ModelRenderer(this, 0, 0);
      this.Left_Arm.addBox(0.0F, -1.0F, -3.0F, 6, 21, 6);
      this.Left_Arm.setRotationPoint(7.0F, -13.0F, 0.0F);
      this.Left_Arm.setTextureSize(128, 64);
      this.Left_Arm.mirror = true;
      this.setRotation(this.Left_Arm, 0.0F, 0.0F, 0.0F);
      this.Head = new ModelRenderer(this, 29, 0);
      this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.Head.setRotationPoint(0.0F, -14.0F, 0.0F);
      this.Head.setTextureSize(128, 64);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Nose = new ModelRenderer(this, 61, 10);
      this.Nose.addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2);
      this.Nose.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Nose.setTextureSize(128, 64);
      this.Nose.mirror = true;
      this.setRotation(this.Nose, 0.0F, 0.0F, 0.0F);
      this.Chest = new ModelRenderer(this, 0, 55);
      this.Chest.addBox(-6.0F, -3.0F, -1.0F, 12, 5, 2);
      this.Chest.setRotationPoint(0.0F, -10.0F, -3.0F);
      this.Chest.setTextureSize(128, 64);
      this.Chest.mirror = true;
      this.setRotation(this.Chest, -0.1115358F, 0.0F, 0.0F);
      this.Brow = new ModelRenderer(this, 55, 0);
      this.Brow.addBox(-4.0F, -8.0F, -5.0F, 8, 3, 1);
      this.Brow.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Brow.setTextureSize(128, 64);
      this.Brow.mirror = true;
      this.setRotation(this.Brow, 0.0F, 0.0F, 0.0F);
      this.Head.addChild(this.Brow);
      this.Head.addChild(this.Nose);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      GL11.glScalef(0.3F, 0.3F, 0.3F);
      GL11.glTranslatef(0.0F, 3.5F, 0.0F);
      this.Right_Leg.render(f5);
      this.Left_Leg.render(f5);
      this.Body.render(f5);
      this.Right_Arm.render(f5);
      this.Left_Arm.render(f5);
      this.Head.render(f5);
      this.Chest.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Head.rotateAngleY = f3 / (180.0F / (float)Math.PI);
      this.Head.rotateAngleX = f4 / (180.0F / (float)Math.PI);
      this.Right_Arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
      this.Left_Arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.Right_Arm.rotateAngleZ = 0.0F;
      this.Left_Arm.rotateAngleZ = 0.0F;
      this.Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.Left_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
      this.Right_Leg.rotateAngleY = 0.0F;
      this.Left_Leg.rotateAngleY = 0.0F;
   }
}
