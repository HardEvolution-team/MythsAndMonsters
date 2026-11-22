package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.entity.EntityCoreBlue;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoreBlue extends LegacyItem {
   public ItemCoreBlue() {
      this.setMaxDamage(0);
      this.setMaxStackSize(1);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("starcoreblue");
      RegistryHelper.setTexture(this, "mam:ItemBrightStarcore");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (!par3World.isRemote) {
         EntityCoreBlue ursa = new EntityCoreBlue(par3World);
         ursa.setPosition(par4 + 0.5, par5 + 1, par6 + 0.5);
         MAMEntityUtil.spawnInWorld(par3World, ursa);
         par1ItemStack.shrink(1);
         return true;
      } else {
         return false;
      }
   }
}
