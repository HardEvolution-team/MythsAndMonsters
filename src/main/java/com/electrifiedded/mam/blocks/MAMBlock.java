package com.electrifiedded.mam.blocks;

import com.electrifiedded.archaiclib.legacy.block.LegacyMetaBlock;
import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Base for the ported blocks: 1.7 meta + icons (side / top / bottom) + render type. */
public class MAMBlock extends LegacyMetaBlock implements IArchaicRenderBlock {
   private final String sideTex;
   private final String topTex;
   private final String bottomTex;
   private int archaicRenderType = RENDER_STANDARD;
   @SideOnly(Side.CLIENT)
   private IIcon side;
   @SideOnly(Side.CLIENT)
   private IIcon top;
   @SideOnly(Side.CLIENT)
   private IIcon bottom;

   public MAMBlock(Material material, MapColor color, String sideTex, String topTex, String bottomTex) {
      super(material, color);
      this.sideTex = sideTex;
      this.topTex = topTex == null ? sideTex : topTex;
      this.bottomTex = bottomTex == null ? this.topTex : bottomTex;
   }

   public MAMBlock(Material material, String tex) {
      this(material, material.getMaterialMapColor(), tex, null, null);
   }

   public MAMBlock sound(SoundType type) {
      this.setSoundType(type);
      return this;
   }

   /** 1.7 getRenderType(): -1 TESR, 1 cross. */
   public MAMBlock legacyRender(int type) {
      if (type == -1) {
         this.setLegacyRenderType(-1);
      } else {
         this.archaicRenderType = type;
      }
      if (type != 0) {
         this.setLegacyFullCube(false);
      }
      return this;
   }

   @Override
   public int getArchaicRenderType(int key) {
      return this.archaicRenderType;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.side = reg.registerIcon(this.sideTex);
      this.top = reg.registerIcon(this.topTex);
      this.bottom = reg.registerIcon(this.bottomTex);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int s, int meta) {
      return s == 0 ? this.bottom : (s == 1 ? this.top : this.side);
   }
}
