package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCore extends ModelBase {
   ModelRenderer Soul;

   public ModelCore() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Soul = new ModelRenderer(this, 0, 45);
      this.Soul.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5);
      this.Soul.setRotationPoint(0.0F, 21.5F, 0.0F);
      this.Soul.setTextureSize(128, 64);
      this.Soul.mirror = true;
      this.setRotation(this.Soul, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Soul.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Soul.rotateAngleY = f2 / 5.0F;
      this.Soul.rotateAngleX = f1;
   }
}
