package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGoldenArrow extends LegacyItem {
   public ItemGoldenArrow() {
      this.maxStackSize = 32;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("goldarrow");
      RegistryHelper.setTexture(this, "mam:ItemGoldArrow");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
