package com.electrifiedded.mam.registry;

import com.electrifiedded.mam.lib.MAMPotionHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public abstract class MAMPotion {
   public static Potion sleepingPotion;

   public static void createPotions() {
      sleepingPotion = new MAMPotionHandler(false, 0)
         .setPotionName("Sleeping")
         .registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -50.0, 2);
      sleepingPotion.setRegistryName(new ResourceLocation("mam", "sleeping"));
      ForgeRegistries.POTIONS.register(sleepingPotion);
   }
}
