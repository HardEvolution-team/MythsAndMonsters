package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGoldenFleece extends LegacyItem {
   public ItemGoldenFleece() {
      this.maxStackSize = 16;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("goldenfleece");
      RegistryHelper.setTexture(this, "mam:ItemGoldFleece");
   }

   public boolean hasEffect(ItemStack par1ItemStack) {
      return true;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
