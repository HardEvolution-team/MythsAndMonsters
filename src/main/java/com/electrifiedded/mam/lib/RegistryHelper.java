package com.electrifiedded.mam.lib;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.v1710.api.item.ArchaicItem;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class RegistryHelper {
   /** 1.7 setTextureName values, applied as generated item models on registration. */
   private static final Map<Item, String> TEXTURES = new HashMap<>();

   public static void setTexture(Item item, String texture) {
      TEXTURES.put(item, texture);
   }

   private static String regName(String unloc) {
      return "mam_" + unloc.substring(5).toLowerCase();
   }

   public static void registerBlock(Block block) {
      registerBlock(block, new ItemBlock(block));
   }

   public static void registerBlock(Block block, Item itemBlock) {
      LegacyGameRegistry.registerBlock(block, itemBlock, regName(block.getTranslationKey()));
   }

   public static void registerItem(Item item) {
      LegacyGameRegistry.registerItem(item, regName(item.getTranslationKey()));
      String tex = TEXTURES.get(item);
      if (tex != null) {
         ArchaicItem.registerMetaTexture(item, 0, tex.toLowerCase());
      }
   }
}
