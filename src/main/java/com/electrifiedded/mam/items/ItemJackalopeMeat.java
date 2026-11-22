package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.Item;

public class ItemJackalopeMeat extends LegacyItem {
   public ItemJackalopeMeat() {
      this.maxStackSize = 64;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("jackalopemeat");
      RegistryHelper.setTexture(this, "mam:ItemJackalopeMeat");
   }
}
