package com.electrifiedded.mam.lib;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Small 1.7.10 call-shape shims used by the ported code. */
public final class MAMCompat {
   private MAMCompat() {
   }

   public static boolean canSeeSky(World world, int x, int y, int z) {
      return world.canSeeSky(new BlockPos(x, y, z));
   }

   /** 1.7 setCurrentItemOrArmor: 0 = held, 1 = boots, 2 = legs, 3 = chest, 4 = helmet. */
   public static EntityEquipmentSlot slot(int legacy) {
      switch (legacy) {
         case 1: return EntityEquipmentSlot.FEET;
         case 2: return EntityEquipmentSlot.LEGS;
         case 3: return EntityEquipmentSlot.CHEST;
         case 4: return EntityEquipmentSlot.HEAD;
         default: return EntityEquipmentSlot.MAINHAND;
      }
   }

   public static void setEquip(EntityLivingBase e, int legacySlot, ItemStack stack) {
      e.setItemStackToSlot(slot(legacySlot), stack == null ? ItemStack.EMPTY : stack);
   }

   public static ItemStack getEquip(EntityLivingBase e, int legacySlot) {
      return e.getItemStackFromSlot(slot(legacySlot));
   }

   /** 1.7 InventoryPlayer.consumeInventoryItem. */
   public static boolean consumeItem(EntityPlayer player, Item item) {
      for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
         ItemStack s = player.inventory.getStackInSlot(i);
         if (!s.isEmpty() && s.getItem() == item) {
            s.shrink(1);
            if (s.isEmpty()) player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
            return true;
         }
      }
      return false;
   }

   public static boolean hasItem(EntityPlayer player, Item item) {
      for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
         ItemStack s = player.inventory.getStackInSlot(i);
         if (!s.isEmpty() && s.getItem() == item) return true;
      }
      return false;
   }
}
