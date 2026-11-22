package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDepthCrystal extends LegacyItem {
   public ItemDepthCrystal() {
      this.maxStackSize = 4;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("depthcrystal");
      RegistryHelper.setTexture(this, "mam:ItemDepthCrystal");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (LegacyWorld.getBlock(par3World, par4, par5 + 1, par6) == Blocks.AIR) {
         LegacyWorld.setBlock(par3World, par4, par5 + 1, par6, MAMBlocks.depthtcrystal);
         par1ItemStack.shrink(1);
         return true;
      } else {
         return false;
      }
   }
}
