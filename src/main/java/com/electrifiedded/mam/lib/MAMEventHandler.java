package com.electrifiedded.mam.lib;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import com.electrifiedded.mam.registry.MAMPotion;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MAMEventHandler {
   @SubscribeEvent
   public void onLivingUpdateEvent(LivingUpdateEvent event) {
      if (MAMPotion.sleepingPotion != null && event.getEntityLiving().isPotionActive(MAMPotion.sleepingPotion) && event.getEntityLiving() instanceof EntityMob) {
         ((EntityMob)event.getEntityLiving()).setAttackTarget(null);
      }
   }

   @SubscribeEvent
   public void onConfigChanged(OnConfigChangedEvent eventArgs) {
      if (eventArgs.getModID().equals("mam")) {
         MythsAndMonstersMod.syncConfig();
      }
   }

   @SubscribeEvent
   public void onFuel(FurnaceFuelBurnTimeEvent event) {
      ItemStack fuel = event.getItemStack();
      if (!fuel.isEmpty() && fuel.getItem() == MAMItems.dendroidroot) {
         event.setBurnTime(TileEntityFurnace.getItemBurnTime(new ItemStack(Blocks.LOG)));
      }
   }
}
