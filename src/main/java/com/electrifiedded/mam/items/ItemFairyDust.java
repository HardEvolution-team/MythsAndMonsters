package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFairyDust extends LegacyItem {
   public ItemFairyDust() {
      this.maxStackSize = 64;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("fairydust");
      RegistryHelper.setTexture(this, "mam:ItemFairyDust");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.UNCOMMON;
   }

   public boolean onItemUseLegacy(
      ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10
   ) {
      if (!par3World.isRemote) {
         Random random = new com.electrifiedded.archaiclib.util.FastRandom();

         for (int i = 0; i < 4; i++) {
            int x1 = par4 + random.nextInt(3);
            int y1 = par5 + random.nextInt(3);
            int z1 = par6 + random.nextInt(3);
            grow(par3World, x1, y1, z1);
         }

         par1ItemStack.shrink(1);
      }

      return false;
   }

   public static boolean grow(World world, int x, int y, int z) {
      return grow(world, x, y, z, false);
   }

   public static boolean grow(World world, int x, int y, int z, boolean flag) {
      Block block = LegacyWorld.getBlock(world, x, y, z);
      if (block == Blocks.DIRT) {
         if (LegacyWorld.getBlockMetadata(world, x, y, z) == 1) {
            LegacyWorld.setBlock(world, x, y, z, Blocks.MYCELIUM);
         } else {
            LegacyWorld.setBlock(world, x, y, z, Blocks.GRASS);
         }

         flag = true;
      } else if (block == Blocks.COBBLESTONE) {
         LegacyWorld.setBlock(world, x, y, z, Blocks.MOSSY_COBBLESTONE);
         flag = true;
      } else if (block == Blocks.STONEBRICK) {
         if (LegacyWorld.getBlockMetadata(world, x, y, z) == 0) {
            LegacyWorld.setBlockMetadataWithNotify(world, x, y, z, 2, 3);
            flag = true;
         }
      } else if (block == Blocks.COBBLESTONE_WALL) {
         if (LegacyWorld.getBlockMetadata(world, x, y, z) == 0) {
            LegacyWorld.setBlockMetadataWithNotify(world, x, y, z, 1, 3);
            flag = true;
         }
      } else if (block == Blocks.SAND) {
         LegacyWorld.setBlock(world, x, y, z, Blocks.CLAY);
         flag = true;
      } else if (block == Blocks.CLAY) {
         LegacyWorld.setBlock(world, x, y, z, Blocks.DIRT);
         flag = true;
      } else if (block == Blocks.CACTUS || block == Blocks.REEDS) {
         LegacyWorld.setBlock(world, x, y + 1, z, block);
         LegacyWorld.setBlock(world, x, y + 2, z, block);
         flag = true;
      } else if (block instanceof IGrowable && !(block instanceof BlockDoublePlant)) {
         IGrowable igrowable = (IGrowable)block;
         if (igrowable.canGrow(world, new net.minecraft.util.math.BlockPos(x, y, z), world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)), world.isRemote) && igrowable.canUseBonemeal(world, world.rand, new net.minecraft.util.math.BlockPos(x, y, z), world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)))) {
            igrowable.grow(world, world.rand, new net.minecraft.util.math.BlockPos(x, y, z), world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)));
            flag = true;
         }
      }

      return flag;
   }
}
