package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import net.minecraft.item.Item;

public class ItemTab extends LegacyItem {
   public ItemTab() {
      this.setTranslationKey("tab");
      RegistryHelper.setTexture(this, "mam:Tab");
   }
}
