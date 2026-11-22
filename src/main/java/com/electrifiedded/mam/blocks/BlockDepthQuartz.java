package com.electrifiedded.mam.blocks;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/** Depth quartz family (plain / chiseled / pillar): only the textures differ. */
public class BlockDepthQuartz extends MAMBlock {
   public BlockDepthQuartz(String name, String side, String top, String bottom) {
      super(Material.ROCK, MapColor.QUARTZ, side, top, bottom);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setSoundType(SoundType.STONE);
      this.setHardness(0.8F);
      this.setTranslationKey(name);
   }
}
