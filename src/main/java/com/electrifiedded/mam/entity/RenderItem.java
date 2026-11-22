package com.electrifiedded.mam.entity;

import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.electrifiedded.archaiclib.v1710.api.client.IItemRenderer;
import com.electrifiedded.archaiclib.v1710.api.client.IItemRenderer.ItemRenderType;
import com.electrifiedded.archaiclib.v1710.api.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderItem implements IItemRenderer {
   protected ModelBase model;
   protected String texture;

   public RenderItem(ModelBase modelToRender, String texturePath) {
      this.model = modelToRender;
      this.texture = texturePath;
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      switch (type) {
         case EQUIPPED:
            return true;
         default:
            return false;
      }
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return false;
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
      switch (type) {
         case EQUIPPED:
            boolean isFirstPerson = false;
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.texture));
            if (item.getItem() == MAMItems.angryfairyjar || item.getItem() == MAMItems.happyfairyjar || item.getItem() == MAMItems.jar) {
               float scale = 1.0F;
               GL11.glScalef(scale, scale, scale);
               GL11.glRotatef(193.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-13.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.4F, -1.5F, 0.0F);
               this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            } else if (item.getItem() == MAMItems.dendroidroot) {
               float scale = 1.0F;
               GL11.glScalef(scale, scale, scale);
               GL11.glRotatef(193.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-13.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.4F, -1.5F, 0.0F);
               this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            } else if (item.getItem() == MAMItems.bluecore) {
               float scale = 2.0F;
               GL11.glScalef(scale, scale, scale);
               GL11.glRotatef(193.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-13.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.2F, -1.5F, 0.0F);
               this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            } else if (item.getItem() == MAMItems.redcore) {
               float scale = 2.0F;
               GL11.glScalef(scale, scale, scale);
               GL11.glRotatef(193.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-13.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.2F, -1.5F, 0.0F);
               this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            } else if (item.getItem() == MAMItems.depthcrystal) {
               float scale = 2.0F;
               GL11.glScalef(scale, scale, scale);
               GL11.glRotatef(193.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-13.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.2F, -1.5F, 0.0F);
               this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            } else if (item.getItem() == MAMItems.tusklance) {
               if (data[1] != null && data[1] instanceof EntityPlayer) {
                  EntityPlayer player = (EntityPlayer)data[1];
                  if (player != Minecraft.getMinecraft().getRenderViewEntity()
                     || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0
                     || (
                           Minecraft.getMinecraft().currentScreen instanceof GuiInventory
                              || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative
                        )
                        && Minecraft.getMinecraft().getRenderManager().playerViewY == 180.0F) {
                     float scale = 1.15F;
                     GL11.glScalef(scale + 0.25F, scale, -scale);
                     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                     if (player.isHandActive()) {
                        GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
                     } else {
                        GL11.glRotatef(190.0F, 0.0F, 1.0F, 0.0F);
                     }

                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                     if (player.isHandActive()) {
                        GL11.glTranslatef(0.0F, -1.2F, 0.8F);
                     } else {
                        GL11.glTranslatef(0.0F, -0.75F, 0.3F);
                     }
                  } else {
                     isFirstPerson = true;
                     float scalex = 1.15F;
                     GL11.glScalef(scalex, scalex, scalex);
                     GL11.glRotatef(105.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(0.0F, -1.0F, 0.5F);
                  }
               } else {
                  float scalex = 1.15F;
                  GL11.glScalef(scalex + 0.25F, scalex, -scalex);
                  GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(190.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.75F, 0.3F);
               }

               this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            }

            GL11.glPopMatrix();
      }
   }
}
