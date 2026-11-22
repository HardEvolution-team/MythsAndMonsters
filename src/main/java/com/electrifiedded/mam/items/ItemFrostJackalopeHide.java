package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFrostJackalopeHide extends LegacyItem {
   public ItemFrostJackalopeHide() {
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("frostjackhide");
      RegistryHelper.setTexture(this, "mam:FrostJackalopeHide");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.UNCOMMON;
   }
}
