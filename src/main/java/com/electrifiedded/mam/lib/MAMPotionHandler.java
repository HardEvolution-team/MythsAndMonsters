package com.electrifiedded.mam.lib;

import net.minecraft.potion.Potion;

public class MAMPotionHandler extends Potion {
   public MAMPotionHandler(boolean bad, int color) {
      super(bad, color);
      this.setIconIndex(0, 1); // original: vanilla inventory.png icon (0,1)
   }
}
