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

public class ItemTaintedSeed extends LegacyItem {
   public ItemTaintedSeed() {
      this.maxStackSize = 64;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("taintedseed");
      RegistryHelper.setTexture(this, "mam:ItemTaintedSeed");
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (!par3World.isRemote && LegacyWorld.getBlock(par3World, par4, par5, par6) == Blocks.MYCELIUM) {
         LegacyWorld.setBlock(par3World, par4, par5 + 1, par6, MAMBlocks.dendroidsapling);
         par1ItemStack.shrink(1);
      }

      return false;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
