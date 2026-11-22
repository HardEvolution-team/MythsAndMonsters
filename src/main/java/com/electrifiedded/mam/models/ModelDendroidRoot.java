package com.electrifiedded.mam.models;

import com.electrifiedded.mam.entity.EntityDendroidRoot;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDendroidRoot extends ModelBase {
   ModelRenderer Root0;
   ModelRenderer Root1;
   ModelRenderer Root2a;
   ModelRenderer Root2b;
   ModelRenderer Root3a;

   public ModelDendroidRoot() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Root0 = new ModelRenderer(this, 38, 0);
      this.Root0.addBox(-2.5F, -6.0F, -2.5F, 5, 7, 5);
      this.Root0.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Root0.setTextureSize(64, 32);
      this.Root0.mirror = true;
      this.setRotation(this.Root0, 0.0F, 0.0F, 0.0F);
      this.Root1 = new ModelRenderer(this, 20, 0);
      this.Root1.addBox(-2.5F, -6.0F, -2.0F, 5, 7, 4);
      this.Root1.setRotationPoint(0.0F, -6.0F, 0.0F);
      this.Root1.setTextureSize(64, 32);
      this.Root1.mirror = true;
      this.setRotation(this.Root1, 0.0F, 0.0F, 0.0F);
      this.Root2a = new ModelRenderer(this, 14, 15);
      this.Root2a.addBox(-1.5F, -5.0F, -1.5F, 3, 6, 3);
      this.Root2a.setRotationPoint(1.0F, -6.0F, 0.0F);
      this.Root2a.setTextureSize(64, 32);
      this.Root2a.mirror = true;
      this.setRotation(this.Root2a, 0.0F, 0.0F, 0.0F);
      this.Root2b = new ModelRenderer(this, 0, 15);
      this.Root2b.addBox(-1.0F, -3.0F, -1.0F, 2, 4, 2);
      this.Root2b.setRotationPoint(-1.5F, -6.0F, -0.6F);
      this.Root2b.setTextureSize(64, 32);
      this.Root2b.mirror = true;
      this.setRotation(this.Root2b, 0.0F, 0.0F, 0.0F);
      this.Root3a = new ModelRenderer(this, 26, 15);
      this.Root3a.addBox(-1.5F, -2.0F, -1.0F, 3, 3, 2);
      this.Root3a.setRotationPoint(0.0F, -5.0F, 0.0F);
      this.Root3a.setTextureSize(64, 32);
      this.Root3a.mirror = true;
      this.setRotation(this.Root3a, 0.0F, 0.0F, 0.0F);
      this.Root0.addChild(this.Root1);
      this.Root1.addChild(this.Root2a);
      this.Root2a.addChild(this.Root3a);
      this.Root1.addChild(this.Root2b);
   }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      this.Root0.render(par7);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      if (par7Entity instanceof EntityDendroidRoot) {
         EntityDendroidRoot Dman = (EntityDendroidRoot)par7Entity;
         float flailSpeed = 1 + Dman.getFlailCounter() / 65;
         this.Root0.rotateAngleY = par4 / (180.0F / (float)Math.PI);
         this.Root0.rotateAngleX = MathHelper.cos(par3 * 1.0F * flailSpeed * 0.6662F) * 0.2F;
         this.Root1.rotateAngleX = MathHelper.cos(par3 * 1.0F * flailSpeed * 0.6662F) * 1.1F;
         this.Root2b.rotateAngleX = MathHelper.sin(par3 * 2.0F * flailSpeed * 0.6662F) * 1.4F;
         this.Root2a.rotateAngleX = MathHelper.cos(par3 * 1.0F * flailSpeed * 0.6662F) * 1.4F;
         this.Root3a.rotateAngleX = MathHelper.cos(par3 * 1.0F * flailSpeed * 0.6662F) * 1.4F;
      } else {
         this.Root1.rotateAngleX = MathHelper.cos(0.6662F) * -0.3F;
         this.Root2b.rotateAngleX = MathHelper.sin(0.3331F) * -0.5F;
         this.Root2a.rotateAngleX = MathHelper.cos(0.6662F) * -0.5F;
         this.Root3a.rotateAngleX = MathHelper.cos(0.6662F) * -0.5F;
      }
   }
}
