package com.electrifiedded.mam.models;

import com.electrifiedded.mam.entity.EntityGoldenRam;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;

public class ModelGoldenRam1 extends ModelQuadrupedPlus {
   private float field_44016_o;

   public ModelGoldenRam1() {
      super(12, 0.0F);
      this.Horn_R = new ModelRenderer(this, 28, 0);
      this.Horn_R.addBox(-3.0F, -8.0F, -4.2F, 3, 5, 3, 0.6F);
      this.Horn_R.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.Horn_R.setTextureSize(64, 32);
      this.Horn_R.mirror = true;
      this.setRotation(this.Horn_R, -0.296706F, 0.0F, 0.6457718F);
      this.Horn_RE = new ModelRenderer(this, 40, 0);
      this.Horn_RE.addBox(1.0F, -11.0F, -6.0F, 2, 5, 3, 0.6F);
      this.Horn_RE.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.Horn_RE.setTextureSize(64, 32);
      this.Horn_RE.mirror = true;
      this.setRotation(this.Horn_RE, -0.6283185F, 0.0F, -0.6457718F);
      this.Horn_LE = new ModelRenderer(this, 40, 0);
      this.Horn_LE.addBox(-3.0F, -11.0F, -6.0F, 2, 5, 3, 0.6F);
      this.Horn_LE.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.Horn_LE.setTextureSize(64, 32);
      this.Horn_LE.mirror = true;
      this.setRotation(this.Horn_LE, -0.6283185F, 0.0F, 0.6457718F);
      this.Horn_L = new ModelRenderer(this, 28, 0);
      this.Horn_L.addBox(0.0F, -8.0F, -4.2F, 3, 5, 3, 0.6F);
      this.Horn_L.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.Horn_L.setTextureSize(64, 32);
      this.Horn_L.mirror = true;
      this.setRotation(this.Horn_L, -0.296706F, 0.0F, -0.6457718F);
      this.Horn_LEE = new ModelRenderer(this, 50, 0);
      this.Horn_LEE.addBox(-1.0F, -10.0F, -10.0F, 1, 4, 2, 0.6F);
      this.Horn_LEE.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.Horn_LEE.setTextureSize(64, 32);
      this.Horn_LEE.mirror = true;
      this.setRotation(this.Horn_LEE, -1.134464F, 0.0F, -0.4363323F);
      this.Horn_REE = new ModelRenderer(this, 50, 0);
      this.Horn_REE.addBox(0.0F, -10.0F, -10.0F, 1, 4, 2, 0.6F);
      this.Horn_REE.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.Horn_REE.setTextureSize(64, 32);
      this.Horn_REE.mirror = true;
      this.setRotation(this.Horn_REE, -1.134464F, 0.0F, 0.4363323F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.head.setRotationPoint(0.0F, 8.0F, -8.0F);
      this.body = new ModelRenderer(this, 28, 8);
      this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
      float f = 0.5F;
      this.leg1 = new ModelRenderer(this, 0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
      this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
      this.leg2 = new ModelRenderer(this, 0, 16);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
      this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
      this.leg3 = new ModelRenderer(this, 0, 16);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
      this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
      this.leg4 = new ModelRenderer(this, 0, 16);
      this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
      this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
   }

   public void setLivingAnimations(net.minecraft.entity.EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
      super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
      this.head.rotationPointY = 6.0F + ((EntityGoldenRam)par1EntityLiving).func_44003_c(par4) * 9.0F;
      this.Horn_REE.rotationPointY = this.head.rotationPointY;
      this.Horn_RE.rotationPointY = this.head.rotationPointY;
      this.Horn_R.rotationPointY = this.head.rotationPointY;
      this.Horn_LEE.rotationPointY = this.head.rotationPointY;
      this.Horn_LE.rotationPointY = this.head.rotationPointY;
      this.Horn_L.rotationPointY = this.head.rotationPointY;
      this.field_44016_o = ((EntityGoldenRam)par1EntityLiving).func_44002_d(par4);
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, null);
      this.head.rotateAngleX = this.field_44016_o;
      this.Horn_L.rotateAngleX = this.head.rotateAngleX - 0.296706F;
      this.Horn_LE.rotateAngleX = this.head.rotateAngleX - 0.6283185F;
      this.Horn_LEE.rotateAngleX = this.head.rotateAngleX - 1.134464F;
      this.Horn_R.rotateAngleX = this.head.rotateAngleX - 0.296706F;
      this.Horn_RE.rotateAngleX = this.head.rotateAngleX - 0.6283185F;
      this.Horn_REE.rotateAngleX = this.head.rotateAngleX - 1.134464F;
   }
}
