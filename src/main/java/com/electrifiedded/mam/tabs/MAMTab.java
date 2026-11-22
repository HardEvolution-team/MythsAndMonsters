package com.electrifiedded.mam.tabs;

import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class MAMTab extends CreativeTabs {
   public MAMTab(String label) {
      super(label);
   }

   @Override
   public ItemStack createIcon() {
      return new ItemStack(MAMItems.tab);
   }

   @Override
   public String getTranslationKey() {
      return "Myths & Monsters";
   }
}
