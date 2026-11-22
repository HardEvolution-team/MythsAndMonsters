package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.entity.EntityFairy;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAngryFairyJar extends LegacyItem {
   public ItemAngryFairyJar() {
      this.setTranslationKey("itemangryfairyjar");
      RegistryHelper.setTexture(this, "mam:ItemFairyJarAngry");
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (!par2EntityPlayer.isSneaking()) {
         if (LegacyWorld.getBlock(par3World, par4, par5 + 1, par6) == Blocks.AIR) {
            LegacyWorld.setBlock(par3World, par4, par5 + 1, par6, MAMBlocks.angryfairyjar);
            par1ItemStack.shrink(1);
            return true;
         }
      } else if (!par3World.isRemote) {
         EntityFairy mob = new EntityFairy(par3World);
         mob.setLocationAndAngles(par4, par5 + 1, par6, 0.0F, 0.0F);
         mob.bottleRelease();
         MAMEntityUtil.spawnInWorld(par3World, mob);
         par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(MAMItems.jar));
         par1ItemStack.shrink(1);
         return true;
      }

      return false;
   }
}
