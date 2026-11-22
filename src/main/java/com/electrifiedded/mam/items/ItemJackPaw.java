package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemJackPaw extends LegacyItem {
   public ItemJackPaw() {
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("jackpaw");
      RegistryHelper.setTexture(this, "mam:ItemJackalopePaw");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.UNCOMMON;
   }
}
