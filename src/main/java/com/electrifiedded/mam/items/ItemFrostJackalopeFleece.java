package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFrostJackalopeFleece extends LegacyItem {
   public ItemFrostJackalopeFleece() {
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("frostjackfleece");
      RegistryHelper.setTexture(this, "mam:FrostJackalopeFleece");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.UNCOMMON;
   }
}
