package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBadApple extends ItemFood {
   public ItemBadApple(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
      super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
      this.setAlwaysEdible();
      this.maxStackSize = 64;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("badapple");
      RegistryHelper.setTexture(this, "mam:ItemBadApple");
   }

   protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (!par2World.isRemote) {
         ItemStack head = par3EntityPlayer.inventory.armorInventory.get(3);
         if (head != null && head.getItem() == MythsAndMonstersMod.dendroidmask) {
            par3EntityPlayer.experienceLevel += 5;
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 90, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 180, 4));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 300, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 300, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 180, 6));
         } else {
            par3EntityPlayer.experienceLevel += 5;
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 90, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WITHER, 180, 4));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 300, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 180, 6));
            par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 6));
         }

         par3EntityPlayer.dropItem(MAMItems.taintedseed, 1);
      }
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
