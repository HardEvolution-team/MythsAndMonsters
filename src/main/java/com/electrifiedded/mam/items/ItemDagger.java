package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemDagger extends ItemSword {
   public ItemDagger() {
      super(MAMItems.EnumToolMaterialBone);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("bonedagger");
      RegistryHelper.setTexture(this, "mam:ItemBoneDagger");
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
      double xx = par3EntityLiving.posX - par2EntityLiving.posX;
      double yy = par3EntityLiving.posY - par2EntityLiving.posY;
      double zz = par3EntityLiving.posZ - par2EntityLiving.posZ;
      double distance = Math.sqrt(xx * xx + yy * yy + zz * zz);
      if (distance < 2.0) {
         if (distance < 0.4) {
            distance = 0.4;
         }

         par2EntityLiving.addPotionEffect(new PotionEffect(MobEffects.WITHER, (int)(4.5 / distance * 20.0), 0));
      }

      super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
      return true;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.UNCOMMON;
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.getItem() == MAMItems.wolantler;
   }
}
