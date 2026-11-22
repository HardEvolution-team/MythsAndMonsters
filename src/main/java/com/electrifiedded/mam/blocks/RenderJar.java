package com.electrifiedded.mam.blocks;

import com.electrifiedded.mam.models.ModelJar;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderJar extends TileEntitySpecialRenderer<TileEntity> {
   private ModelJar model = new ModelJar();

   public void renderAModelAt(TileEntityJar tile, double d, double d1, double d2, float f) {
      this.bindTexture(new ResourceLocation("mam:textures/blocks/EmptyFairyJar.png"));
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
      GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
      GL11.glScalef(1.0F, -1.0F, -1.0F);
      this.model.renderModel(0.0625F);
      GL11.glPopMatrix();
   }

   @Override
   public void render(TileEntity tileentity, double d, double d1, double d2, float f, int destroyStage, float alpha) {
      this.renderAModelAt((TileEntityJar)tileentity, d, d1, d2, f);
   }
}
