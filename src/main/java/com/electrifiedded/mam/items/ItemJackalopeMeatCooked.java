package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.ItemFood;

public class ItemJackalopeMeatCooked extends ItemFood {
   public ItemJackalopeMeatCooked(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
      super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
      this.maxStackSize = 64;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("jackalopemeatcooked");
      RegistryHelper.setTexture(this, "mam:ItemJackalopeMeatCooked");
   }
}
