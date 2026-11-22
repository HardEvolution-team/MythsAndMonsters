package com.electrifiedded.mam.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelQuadrupedPlus extends ModelBase {
   public ModelRenderer head;
   public ModelRenderer body;
   public ModelRenderer leg1;
   public ModelRenderer leg2;
   public ModelRenderer leg3;
   public ModelRenderer leg4;
   public ModelRenderer Horn_R;
   public ModelRenderer Horn_RE;
   public ModelRenderer Horn_REE;
   public ModelRenderer Horn_L;
   public ModelRenderer Horn_LE;
   public ModelRenderer Horn_LEE;
   protected float field_78145_g = 8.0F;
   protected float field_78151_h = 4.0F;
   private float hX = 0.0F;
   private float hY = 8.0F;
   private float hZ = -8.0F;

   public ModelQuadrupedPlus(int par1, float par2) {
      this.Horn_R = new ModelRenderer(this, 28, 0);
      this.Horn_R.addBox(-3.0F, -8.0F, -4.2F, 3, 5, 3, 0.6F);
      this.Horn_R.setRotationPoint(this.hX, this.hY, this.hZ);
      this.Horn_R.setTextureSize(64, 32);
      this.Horn_R.mirror = true;
      this.setRotation(this.Horn_R, -0.296706F, 0.0F, 0.6457718F);
      this.Horn_RE = new ModelRenderer(this, 40, 0);
      this.Horn_RE.addBox(1.0F, -11.0F, -6.0F, 2, 5, 3, 0.6F);
      this.Horn_RE.setRotationPoint(this.hX, this.hY, this.hZ);
      this.Horn_RE.setTextureSize(64, 32);
      this.Horn_RE.mirror = true;
      this.setRotation(this.Horn_RE, -0.6283185F, 0.0F, -0.6457718F);
      this.Horn_LE = new ModelRenderer(this, 40, 0);
      this.Horn_LE.addBox(-3.0F, -11.0F, -6.0F, 2, 5, 3, 0.6F);
      this.Horn_LE.setRotationPoint(this.hX, this.hY, this.hZ);
      this.Horn_LE.setTextureSize(64, 32);
      this.Horn_LE.mirror = true;
      this.setRotation(this.Horn_LE, -0.6283185F, 0.0F, 0.6457718F);
      this.Horn_L = new ModelRenderer(this, 28, 0);
      this.Horn_L.addBox(0.0F, -8.0F, -4.2F, 3, 5, 3, 0.6F);
      this.Horn_L.setRotationPoint(this.hX, this.hY, this.hZ);
      this.Horn_L.setTextureSize(64, 32);
      this.Horn_L.mirror = true;
      this.setRotation(this.Horn_L, -0.296706F, 0.0F, -0.6457718F);
      this.Horn_LEE = new ModelRenderer(this, 50, 0);
      this.Horn_LEE.addBox(-1.0F, -10.0F, -10.0F, 1, 4, 2, 0.6F);
      this.Horn_LEE.setRotationPoint(this.hX, this.hY, this.hZ);
      this.Horn_LEE.setTextureSize(64, 32);
      this.Horn_LEE.mirror = true;
      this.setRotation(this.Horn_LEE, -1.134464F, 0.0F, -0.4363323F);
      this.Horn_REE = new ModelRenderer(this, 50, 0);
      this.Horn_REE.addBox(0.0F, -10.0F, -10.0F, 1, 4, 2, 0.6F);
      this.Horn_REE.setRotationPoint(this.hX, this.hY, this.hZ);
      this.Horn_REE.setTextureSize(64, 32);
      this.Horn_REE.mirror = true;
      this.setRotation(this.Horn_REE, -1.134464F, 0.0F, 0.4363323F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.head.setRotationPoint(this.hX, this.hY, this.hZ);
      this.body = new ModelRenderer(this, 28, 8);
      this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, par2);
      this.body.setRotationPoint(0.0F, 17 - par1, 2.0F);
      this.leg1 = new ModelRenderer(this, 0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, par1, 4, par2);
      this.leg1.setRotationPoint(-3.0F, 24 - par1, 7.0F);
      this.leg2 = new ModelRenderer(this, 0, 16);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, par1, 4, par2);
      this.leg2.setRotationPoint(3.0F, 24 - par1, 7.0F);
      this.leg3 = new ModelRenderer(this, 0, 16);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, par1, 4, par2);
      this.leg3.setRotationPoint(-3.0F, 24 - par1, -5.0F);
      this.leg4 = new ModelRenderer(this, 0, 16);
      this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, par1, 4, par2);
      this.leg4.setRotationPoint(3.0F, 24 - par1, -5.0F);
      this.head.addChild(this.Horn_L);
      this.head.addChild(this.Horn_LE);
      this.head.addChild(this.Horn_LEE);
      this.head.addChild(this.Horn_R);
      this.head.addChild(this.Horn_RE);
      this.head.addChild(this.Horn_REE);
   }

   protected void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
      this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      if (this.isChild) {
         float var8 = 2.0F;
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, this.field_78145_g * par7, this.field_78151_h * par7);
         this.head.render(par7);
         this.Horn_R.render(par7);
         this.Horn_RE.render(par7);
         this.Horn_REE.render(par7);
         this.Horn_L.render(par7);
         this.Horn_LE.render(par7);
         this.Horn_LEE.render(par7);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
         GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
         this.body.render(par7);
         this.leg1.render(par7);
         this.leg2.render(par7);
         this.leg3.render(par7);
         this.leg4.render(par7);
         GL11.glPopMatrix();
      } else {
         this.head.render(par7);
         this.body.render(par7);
         this.leg1.render(par7);
         this.leg2.render(par7);
         this.leg3.render(par7);
         this.leg4.render(par7);
         this.Horn_R.render(par7);
         this.Horn_RE.render(par7);
         this.Horn_REE.render(par7);
         this.Horn_L.render(par7);
         this.Horn_LE.render(par7);
         this.Horn_LEE.render(par7);
      }
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      this.head.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.head.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.Horn_L.rotateAngleX = this.head.rotateAngleX - 0.296706F;
      this.Horn_LE.rotateAngleX = this.head.rotateAngleX - 0.6283185F;
      this.Horn_LEE.rotateAngleX = this.head.rotateAngleX - 1.134464F;
      this.Horn_R.rotateAngleX = this.head.rotateAngleX - 0.296706F;
      this.Horn_RE.rotateAngleX = this.head.rotateAngleX - 0.6283185F;
      this.Horn_REE.rotateAngleX = this.head.rotateAngleX - 1.134464F;
      this.body.rotateAngleX = (float) (Math.PI / 2);
      this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
   }
}
