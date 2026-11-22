package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelJackalopes extends ModelBase {
   ModelRenderer Right_Front_Leg;
   ModelRenderer Head;
   ModelRenderer Body;
   ModelRenderer Left_Front_Leg;
   ModelRenderer Right_Back_Leg;
   ModelRenderer Left_Back_Leg;
   ModelRenderer Tail;
   ModelRenderer Left_Ear;
   ModelRenderer Right_Ear;
   ModelRenderer Nose;
   ModelRenderer Left_Antler;
   ModelRenderer Right_Antler;
   ModelRenderer Left_Antler_Part;
   ModelRenderer Right_Antler_Part;
   ModelRenderer Tooth;
   protected float field_78145_g = 8.0F;
   protected float field_78151_h = 4.0F;

   public ModelJackalopes() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Right_Front_Leg = new ModelRenderer(this, 0, 31);
      this.Right_Front_Leg.addBox(-4.0F, -1.0F, -2.0F, 4, 14, 4);
      this.Right_Front_Leg.setRotationPoint(-5.0F, 11.0F, -3.0F);
      this.Right_Front_Leg.setTextureSize(64, 32);
      this.Right_Front_Leg.mirror = true;
      this.setRotation(this.Right_Front_Leg, 0.0F, 0.0F, 0.0F);
      this.Head = new ModelRenderer(this, 45, 0);
      this.Head.addBox(-5.0F, -4.0F, -10.0F, 10, 8, 10);
      this.Head.setRotationPoint(0.0F, 4.0F, -4.0F);
      this.Head.setTextureSize(64, 32);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.addBox(-5.5F, -5.0F, 0.0F, 11, 10, 20);
      this.Body.setRotationPoint(0.0F, 8.0F, -5.0F);
      this.Body.setTextureSize(64, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, -0.2602503F, 0.0F, 0.0F);
      this.Left_Front_Leg = new ModelRenderer(this, 0, 31);
      this.Left_Front_Leg.addBox(0.0F, -1.0F, -2.0F, 4, 14, 4);
      this.Left_Front_Leg.setRotationPoint(5.0F, 11.0F, -4.0F);
      this.Left_Front_Leg.setTextureSize(64, 32);
      this.Left_Front_Leg.mirror = true;
      this.setRotation(this.Left_Front_Leg, 0.0F, 0.0F, 0.0F);
      this.Right_Back_Leg = new ModelRenderer(this, 18, 31);
      this.Right_Back_Leg.addBox(-4.0F, -1.0F, -2.0F, 4, 12, 4);
      this.Right_Back_Leg.setRotationPoint(-5.0F, 13.0F, 11.0F);
      this.Right_Back_Leg.setTextureSize(64, 32);
      this.Right_Back_Leg.mirror = true;
      this.setRotation(this.Right_Back_Leg, 0.0F, 0.0F, 0.0F);
      this.Left_Back_Leg = new ModelRenderer(this, 18, 31);
      this.Left_Back_Leg.addBox(0.0F, -1.0F, -2.0F, 4, 12, 4);
      this.Left_Back_Leg.setRotationPoint(5.0F, 13.0F, 11.0F);
      this.Left_Back_Leg.setTextureSize(64, 32);
      this.Left_Back_Leg.mirror = true;
      this.setRotation(this.Left_Back_Leg, 0.0F, 0.0F, 0.0F);
      this.Tail = new ModelRenderer(this, 0, 50);
      this.Tail.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 5);
      this.Tail.setRotationPoint(0.0F, 9.0F, 13.0F);
      this.Tail.setTextureSize(64, 32);
      this.Tail.mirror = true;
      this.setRotation(this.Tail, 0.0F, 0.0F, 0.0F);
      this.Left_Ear = new ModelRenderer(this, 36, 31);
      this.Left_Ear.addBox(-2.0F, -10.0F, 0.0F, 4, 10, 3);
      this.Left_Ear.setRotationPoint(3.0F, 0.0F, -7.0F);
      this.Left_Ear.setTextureSize(64, 32);
      this.Left_Ear.mirror = true;
      this.setRotation(this.Left_Ear, 0.0F, 0.0F, 0.0F);
      this.Right_Ear = new ModelRenderer(this, 51, 31);
      this.Right_Ear.addBox(-2.0F, -10.0F, -1.0F, 4, 10, 3);
      this.Right_Ear.setRotationPoint(-3.0F, 0.0F, -6.0F);
      this.Right_Ear.setTextureSize(64, 32);
      this.Right_Ear.mirror = true;
      this.setRotation(this.Right_Ear, 0.0F, 0.0F, 0.0F);
      this.Nose = new ModelRenderer(this, 24, 56);
      this.Nose.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1);
      this.Nose.setRotationPoint(0.0F, 3.5F, -14.0F);
      this.Nose.setTextureSize(64, 32);
      this.Nose.mirror = true;
      this.setRotation(this.Nose, 0.0F, 0.0F, 0.0F);
      this.Left_Antler = new ModelRenderer(this, 66, 34);
      this.Left_Antler.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
      this.Left_Antler.setRotationPoint(3.0F, 0.0F, -10.0F);
      this.Left_Antler.setTextureSize(64, 32);
      this.Left_Antler.mirror = true;
      this.setRotation(this.Left_Antler, 0.1115358F, 0.0F, 0.0F);
      this.Right_Antler = new ModelRenderer(this, 66, 34);
      this.Right_Antler.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
      this.Right_Antler.setRotationPoint(-3.0F, 0.0F, -10.0F);
      this.Right_Antler.setTextureSize(64, 32);
      this.Right_Antler.mirror = true;
      this.setRotation(this.Right_Antler, 0.1115358F, 0.0F, 0.0F);
      this.Left_Antler_Part = new ModelRenderer(this, 22, 50);
      this.Left_Antler_Part.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
      this.Left_Antler_Part.setRotationPoint(3.0F, -3.0F, -11.0F);
      this.Left_Antler_Part.setTextureSize(64, 32);
      this.Left_Antler_Part.mirror = true;
      this.setRotation(this.Left_Antler_Part, 0.0F, 0.0F, 0.0F);
      this.Right_Antler_Part = new ModelRenderer(this, 22, 50);
      this.Right_Antler_Part.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
      this.Right_Antler_Part.setRotationPoint(-3.0F, -3.0F, -11.0F);
      this.Right_Antler_Part.setTextureSize(64, 32);
      this.Right_Antler_Part.mirror = true;
      this.setRotation(this.Right_Antler_Part, 0.0F, 0.0F, 0.0F);
      this.Tooth = new ModelRenderer(this, 86, 0);
      this.Tooth.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 1);
      this.Tooth.setRotationPoint(0.0F, 8.0F, -13.0F);
      this.Tooth.setTextureSize(64, 32);
      this.Tooth.mirror = true;
      this.setRotation(this.Tooth, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      GL11.glScalef(0.7F, 0.7F, 0.7F);
      GL11.glTranslatef(0.0F, 0.7F, 0.0F);
      if (this.isChild) {
         float f6 = 2.0F;
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, this.field_78145_g * f5, this.field_78151_h * f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
         GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
         this.Right_Front_Leg.render(f5);
         this.Head.render(f5);
         this.Body.render(f5);
         this.Left_Front_Leg.render(f5);
         this.Right_Back_Leg.render(f5);
         this.Left_Back_Leg.render(f5);
         this.Left_Antler_Part.render(f5);
         this.Right_Antler_Part.render(f5);
         this.Left_Antler.render(f5);
         this.Right_Antler.render(f5);
         this.Nose.render(f5);
         this.Left_Ear.render(f5);
         this.Right_Ear.render(f5);
         this.Tail.render(f5);
         GL11.glPopMatrix();
      } else {
         this.Right_Front_Leg.render(f5);
         this.Head.render(f5);
         this.Body.render(f5);
         this.Left_Front_Leg.render(f5);
         this.Right_Back_Leg.render(f5);
         this.Left_Back_Leg.render(f5);
         this.Left_Antler_Part.render(f5);
         this.Right_Antler_Part.render(f5);
         this.Left_Antler.render(f5);
         this.Right_Antler.render(f5);
         this.Nose.render(f5);
         this.Left_Ear.render(f5);
         this.Right_Ear.render(f5);
         this.Tail.render(f5);
      }
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      if (entity.isAirBorne) {
         this.Right_Front_Leg.rotateAngleX = MathHelper.sin(f * 2.0F * 0.6662F + 3.141593F) * 2.0F * f1;
         this.Right_Back_Leg.rotateAngleX = MathHelper.sin(f * 2.0F * 0.6662F) * 2.0F * f1;
         this.Left_Front_Leg.rotateAngleX = MathHelper.sin(f * 2.0F * 0.6662F) * 2.0F * f1;
         this.Left_Back_Leg.rotateAngleX = MathHelper.sin(f * 2.0F * 0.6662F + 3.141593F) * 2.0F * f1;
      } else if (!entity.isAirBorne) {
         this.Right_Back_Leg.rotateAngleY = -0.15F;
         this.Left_Back_Leg.rotateAngleY = 0.15F;
         this.Right_Front_Leg.rotateAngleX = MathHelper.sin(f * 2.0F * 0.6662F + 3.141593F) * 2.0F * f1;
         this.Right_Back_Leg.rotateAngleX = Math.abs(MathHelper.sin(f * 2.0F * 0.6662F * 0.5F) * 2.0F * f1);
         this.Left_Front_Leg.rotateAngleX = MathHelper.sin(f * 2.0F * 0.6662F + 3.141593F) * 2.0F * f1;
         this.Left_Back_Leg.rotateAngleX = Math.abs(MathHelper.sin(f * 2.0F * 0.6662F * 0.5F) * 2.0F * f1);
      } else {
         float jumpFloat = MathHelper.clamp(((float)entity.posY + 0.1F) * 2.0F, -0.523F, 0.523F);
         this.Right_Front_Leg.rotateAngleX = jumpFloat;
         this.Right_Back_Leg.rotateAngleX = jumpFloat;
         this.Left_Front_Leg.rotateAngleX = jumpFloat;
         this.Left_Back_Leg.rotateAngleX = jumpFloat;
         this.Right_Front_Leg.rotateAngleY = 0.0F;
         this.Left_Front_Leg.rotateAngleY = 0.0F;
         if (jumpFloat > 0.0F) {
            this.Right_Back_Leg.rotateAngleY = -0.35F;
            this.Left_Back_Leg.rotateAngleY = 0.35F;
         } else {
            this.Right_Back_Leg.rotateAngleY = 0.0F;
            this.Left_Back_Leg.rotateAngleY = 0.0F;
         }
      }
   }
}
