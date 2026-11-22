package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.Item;

public class ItemWolAntler extends LegacyItem {
   public ItemWolAntler() {
      this.maxStackSize = 16;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("wolantler");
      RegistryHelper.setTexture(this, "mam:ItemAntler");
   }
}
