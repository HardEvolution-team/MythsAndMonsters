package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.projectile.EntityDendroidChakram;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDendroidChakram extends LegacyItem {
   public ItemDendroidChakram() {
      this.maxStackSize = 4;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("dendroidchakram");
      RegistryHelper.setTexture(this, "mam:ItemDendroidChakram");
   }

   public ItemStack onItemRightClickLegacy(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      boolean flag = par3EntityPlayer.capabilities.isCreativeMode;
      EntityDendroidChakram den = new EntityDendroidChakram(par2World, par3EntityPlayer);
      MAMEntityUtil.spawnInWorld(par2World, den);
      if (!flag) {
         par1ItemStack.shrink(1);
      }

      LegacyWorld.playSoundAtEntity(par2World, par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      return par1ItemStack;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }
}
