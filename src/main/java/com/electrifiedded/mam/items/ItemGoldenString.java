package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGoldenString extends LegacyItem {
   public ItemGoldenString() {
      this.maxStackSize = 32;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("goldenstring");
      RegistryHelper.setTexture(this, "mam:ItemGoldString");
   }

   public boolean hasEffect(ItemStack par1ItemStack) {
      return true;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
