package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBowlOfFairyDust extends ItemFood {
   public ItemBowlOfFairyDust(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
      super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
      this.setAlwaysEdible();
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("bowloffairydust");
      RegistryHelper.setTexture(this, "mam:ItemFairyDustBowl");
   }

   protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (!par2World.isRemote) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 120, 1));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 120, 1));
         par3EntityPlayer.heal(6.0F);
         par3EntityPlayer.dropItem(Items.BOWL, 1);
      }
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.UNCOMMON;
   }
}
