package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.projectile.EntityDust2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlueStarDust extends LegacyItem {
   public ItemBlueStarDust() {
      this.maxStackSize = 16;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("bluestardust");
      RegistryHelper.setTexture(this, "mam:ItemBrightStardust");
   }

   public ItemStack onItemRightClickLegacy(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      boolean flag = par3EntityPlayer.capabilities.isCreativeMode;
      EntityThrowable one = new EntityDust2(par2World, par3EntityPlayer);
      EntityThrowable two = new EntityDust2(par2World, par3EntityPlayer);
      EntityThrowable three = new EntityDust2(par2World, par3EntityPlayer);
      EntityThrowable four = new EntityDust2(par2World, par3EntityPlayer);
      EntityThrowable five = new EntityDust2(par2World, par3EntityPlayer);
      EntityThrowable six = new EntityDust2(par2World, par3EntityPlayer);
      one.motionX += 0.2F;
      one.motionZ += 0.2F;
      one.motionX *= 0.8F;
      one.motionZ *= 0.8F;
      two.motionX += 0.2F;
      two.motionZ += 0.2F;
      six.motionX *= 0.8F;
      six.motionZ *= 0.8F;
      four.motionX -= 0.2F;
      four.motionZ -= 0.2F;
      five.motionX -= 0.2F;
      five.motionZ -= 0.2F;
      five.motionX *= 0.8F;
      five.motionZ *= 0.8F;
      MAMEntityUtil.spawnInWorld(par2World, one);
      MAMEntityUtil.spawnInWorld(par2World, two);
      MAMEntityUtil.spawnInWorld(par2World, three);
      MAMEntityUtil.spawnInWorld(par2World, four);
      MAMEntityUtil.spawnInWorld(par2World, five);
      MAMEntityUtil.spawnInWorld(par2World, six);
      if (!flag) {
         par1ItemStack.shrink(1);
      }

      LegacyWorld.playSoundAtEntity(par2World, par3EntityPlayer, "random.fizz", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      return par1ItemStack;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
