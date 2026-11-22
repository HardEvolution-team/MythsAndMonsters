package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.entity.EntityFairy;
import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJar extends LegacyItem {
   public ItemJar() {
      this.setTranslationKey("itemjar");
      RegistryHelper.setTexture(this, "mam:ItemFairyJar");
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (LegacyWorld.getBlock(par3World, par4, par5 + 1, par6) == Blocks.AIR) {
         LegacyWorld.setBlock(par3World, par4, par5 + 1, par6, MAMBlocks.jar);
         par1ItemStack.shrink(1);
         return true;
      } else {
         return false;
      }
   }

   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, net.minecraft.util.EnumHand hand) {
      if (target instanceof EntityFairy && !player.world.isRemote) {
         player.inventory.addItemStackToInventory(new ItemStack(MAMItems.angryfairyjar));
         stack.shrink(1);
         target.setDead();
         return true;
      } else {
         return false;
      }
   }
}
