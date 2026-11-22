package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTuskLance extends ModelBase {
   ModelRenderer Main;
   ModelRenderer Guard;
   ModelRenderer Main2;
   ModelRenderer Main3;
   ModelRenderer Sub2;
   ModelRenderer Sub1;
   ModelRenderer Handle;
   ModelRenderer Over;
   ModelRenderer Guardtop;
   ModelRenderer Guardleft;

   public ModelTuskLance() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Main = new ModelRenderer(this, 44, 0);
      this.Main.addBox(-1.0F, -1.5F, 0.0F, 2, 3, 8);
      this.Main.setRotationPoint(0.0F, 21.0F, 0.0F);
      this.Main.setTextureSize(64, 32);
      this.Main.mirror = true;
      this.setRotation(this.Main, 0.0F, 0.0F, 0.0F);
      this.Guard = new ModelRenderer(this, 0, 0);
      this.Guard.addBox(-2.0F, -2.0F, 1.0F, 4, 4, 1);
      this.Guard.setRotationPoint(0.0F, 21.0F, -1.0F);
      this.Guard.setTextureSize(64, 32);
      this.Guard.mirror = true;
      this.setRotation(this.Guard, 0.0F, 0.0F, 0.0F);
      this.Main2 = new ModelRenderer(this, 29, 0);
      this.Main2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 5);
      this.Main2.setRotationPoint(0.0F, 20.5F, 8.0F);
      this.Main2.setTextureSize(64, 32);
      this.Main2.mirror = true;
      this.setRotation(this.Main2, 0.0F, 0.0F, 0.0F);
      this.Main3 = new ModelRenderer(this, 12, 0);
      this.Main3.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 7);
      this.Main3.setRotationPoint(0.0F, 20.5F, 13.0F);
      this.Main3.setTextureSize(64, 32);
      this.Main3.mirror = true;
      this.setRotation(this.Main3, 0.0F, 0.0F, 0.0F);
      this.Sub2 = new ModelRenderer(this, 29, 8);
      this.Sub2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 6);
      this.Sub2.setRotationPoint(0.0F, 22.0F, 8.0F);
      this.Sub2.setTextureSize(64, 32);
      this.Sub2.mirror = true;
      this.setRotation(this.Sub2, 0.0F, 0.0F, 0.0F);
      this.Sub1 = new ModelRenderer(this, 46, 12);
      this.Sub1.addBox(-0.5F, -0.2F, 0.0F, 1, 1, 8);
      this.Sub1.setRotationPoint(0.0F, 22.0F, 0.0F);
      this.Sub1.setTextureSize(64, 32);
      this.Sub1.mirror = true;
      this.setRotation(this.Sub1, 0.0F, 0.0F, 0.0F);
      this.Handle = new ModelRenderer(this, 0, 24);
      this.Handle.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 6);
      this.Handle.setRotationPoint(0.0F, 21.0F, -1.0F);
      this.Handle.setTextureSize(64, 32);
      this.Handle.mirror = true;
      this.setRotation(this.Handle, 0.2602503F, 0.0F, 0.0F);
      this.Over = new ModelRenderer(this, 0, 10);
      this.Over.addBox(-0.5F, -1.0F, -7.0F, 1, 1, 11);
      this.Over.setRotationPoint(0.0F, 20.0F, 6.0F);
      this.Over.setTextureSize(64, 32);
      this.Over.mirror = true;
      this.setRotation(this.Over, -0.0349066F, 0.0F, 0.0F);
      this.Guardtop = new ModelRenderer(this, 17, 24);
      this.Guardtop.addBox(-2.0F, -3.0F, 1.0F, 4, 2, 1);
      this.Guardtop.setRotationPoint(0.0F, 21.0F, 0.0F);
      this.Guardtop.setTextureSize(64, 32);
      this.Guardtop.mirror = true;
      this.setRotation(this.Guardtop, 0.8552113F, 0.0F, 0.0F);
      this.Guardleft = new ModelRenderer(this, 29, 23);
      this.Guardleft.addBox(1.0F, -2.0F, 1.0F, 2, 4, 1);
      this.Guardleft.setRotationPoint(0.0F, 21.0F, 0.0F);
      this.Guardleft.setTextureSize(64, 32);
      this.Guardleft.mirror = true;
      this.setRotation(this.Guardleft, 0.0F, 0.8552113F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5);
      this.Main.render(f5);
      this.Guard.render(f5);
      this.Main2.render(f5);
      this.Main3.render(f5);
      this.Sub2.render(f5);
      this.Sub1.render(f5);
      this.Handle.render(f5);
      this.Over.render(f5);
      this.Guardtop.render(f5);
      this.Guardleft.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
   }
}
