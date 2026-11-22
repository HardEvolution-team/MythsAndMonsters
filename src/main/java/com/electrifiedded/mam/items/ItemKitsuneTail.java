package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKitsuneTail extends ItemFood {
   public ItemKitsuneTail(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
      super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
      this.setAlwaysEdible();
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("kitsunetail");
      RegistryHelper.setTexture(this, "mam:KitsuneTailtexture");
   }

   protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      par3EntityPlayer.experienceLevel = par3EntityPlayer.experienceLevel + itemRand.nextInt(15);
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
