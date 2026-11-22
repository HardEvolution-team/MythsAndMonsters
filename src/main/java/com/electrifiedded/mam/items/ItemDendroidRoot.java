package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.entity.EntityDendroidRoot;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDendroidRoot extends LegacyItem {
   public ItemDendroidRoot() {
      this.maxStackSize = 64;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("dendroidroot");
      RegistryHelper.setTexture(this, "mam:ItemDendroidRoot");
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (LegacyWorld.getBlock(par3World, par4, par5, par6) != Blocks.GRASS
         && LegacyWorld.getBlock(par3World, par4, par5, par6) != Blocks.DIRT
         && (LegacyWorld.getBlock(par3World, par4, par5, par6) != Blocks.MYCELIUM || LegacyWorld.getBlock(par3World, par4, par5 + 1, par6) != Blocks.AIR)) {
         return false;
      } else {
         EntityDendroidRoot root = new EntityDendroidRoot(par3World, par4, par5, par6);
         root.setPosition(par4 + 0.5, par5 + 1, par6 + 0.5);
         MAMEntityUtil.spawnInWorld(par3World, root);
         par1ItemStack.shrink(1);
         return true;
      }
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }
}
