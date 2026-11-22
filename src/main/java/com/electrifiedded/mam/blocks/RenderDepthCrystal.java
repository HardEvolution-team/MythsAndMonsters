package com.electrifiedded.mam.blocks;

import com.electrifiedded.mam.models.ModelDepthCrystal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDepthCrystal extends TileEntitySpecialRenderer<TileEntity> {
   private ModelDepthCrystal model = new ModelDepthCrystal();

   public void renderAModelAt(TileEntityDepthCrystal tile, double d, double d1, double d2, float f) {
      this.bindTexture(new ResourceLocation("mam:textures/blocks/DepthCrystal.png"));
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
      GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
      GL11.glScalef(1.0F, -1.0F, -1.0F);
      GL11.glColor3d(500000.0, 500000.0, 500000.0);
      this.model.renderModel(0.0625F);
      GL11.glPopMatrix();
   }

   @Override
   public void render(TileEntity tileentity, double d, double d1, double d2, float f, int destroyStage, float alpha) {
      this.renderAModelAt((TileEntityDepthCrystal)tileentity, d, d1, d2, f);
   }
}
