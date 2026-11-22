package com.electrifiedded.mam.structure;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureForest extends WorldGenerator {
   public boolean generate(World world, Random rand, int x, int y, int z) {
      int toSpawn = rand.nextInt(5);
      return toSpawn <= 2 ? this.generateGrotto(world, rand, x, y, z) : this.generateFairyRing(world, rand, x, y, z);
   }

   private boolean generateFairyRing(World world, Random rand, int x, int y, int z) {
      int shroomCount = 0;
      boolean fairies = false;
      if (LegacyWorld.getBlock(world, x, y, z) == Blocks.GRASS && LegacyWorld.getBlock(world, x, y + 1, z) == Blocks.AIR) {
         shroomCount += this.placeMushroom(world, rand, x + 0, y, z + 2);
         shroomCount += this.placeMushroom(world, rand, x + 0, y, z + 3);
         shroomCount += this.placeMushroom(world, rand, x + 0, y, z + 4);
         shroomCount += this.placeMushroom(world, rand, x + 0, y, z + 5);
         if (shroomCount > 9) {
            if (shroomCount > 16) {
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private int placeMushroom(World world, Random rand, int x, int y, int z) {
      for (int checky = -2; checky <= 3; checky++) {
         if (LegacyWorld.getBlock(world, x, y + checky, z) == Blocks.GRASS && LegacyWorld.getBlock(world, x, y + checky + 1, z) == Blocks.AIR) {
            LegacyWorld.setBlock(world, x, y + checky + 1, z, MAMBlocks.fairymushroom);
            return 1;
         }
      }

      return 0;
   }

   public boolean generateGrotto(World world, Random rand, int x, int y, int z) {
      if (LegacyWorld.getBlock(world, x, y, z) == Blocks.GRASS && LegacyWorld.getBlock(world, x, y + 1, z) == Blocks.AIR) {
         for (int checkx = 1; checkx <= 8; checkx++) {
            for (int checkz = 1; checkz <= 8; checkz++) {
               if (LegacyWorld.getBlock(world, x + checkx, y, z + checkz) == Blocks.AIR) {
                  return false;
               }
            }
         }

         Block block = Blocks.LOG;

         for (int floorx = 1; floorx <= 8; floorx++) {
            for (int floorz = 1; floorz <= 8; floorz++) {
               for (int floory = 0; floory <= 9; floory++) {
                  if (floory != 0
                     && LegacyWorld.getBlock(world, x + floorx, y + floory, z + floorz) != Blocks.STONE
                     && LegacyWorld.getBlock(world, x + floorx, y + floory, z + floorz) != Blocks.DIRT
                     && LegacyWorld.getBlock(world, x + floorx, y + floory, z + floorz) != Blocks.GRASS) {
                     LegacyWorld.setBlock(world, x + floorx, y + floory, z + floorz, Blocks.AIR, 0, 2);
                  }

                  LegacyWorld.setBlock(world, x + floorx, y, z + floorz, block, 0, 2);
               }
            }
         }

         LegacyWorld.setBlock(world, x + 1, y, z, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y, z, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y, z, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y, z, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y, z, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x, y, z + 1, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y, z + 1, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y, z + 1, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y, z + 1, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x, y, z + 2, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y, z + 2, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x, y, z + 6, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y, z + 6, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x, y, z + 7, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y, z + 7, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x, y, z + 8, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y, z + 8, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y, z + 8, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y, z + 8, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y, z + 9, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y, z + 9, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y, z + 9, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y, z + 9, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y, z + 9, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y, z + 9, Blocks.GRASS, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y, z + 4, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y, z + 4, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y, z + 5, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y, z + 5, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 5, z + 5, MAMBlocks.dendroidelderspawner);
         LegacyWorld.setBlock(world, x + 3, y - 1, z + 4, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y - 1, z + 4, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y - 1, z + 5, Blocks.WATER, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y - 1, z + 5, Blocks.WATER, 0, 2);
                  LegacyWorld.setBlock(world, x + 4, y - 1, z + 6, Blocks.CHEST, 0, 2);
         TileEntityChest chest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + 4, y - 1, z + 6);
         if (chest != null)

         for (int slot = 0; slot < chest.getSizeInventory(); slot++) {
            int chestr = rand.nextInt(29);
            if (chestr == 1) {
               chest.setInventorySlotContents(slot, new ItemStack(Items.EXPERIENCE_BOTTLE));
            }

            if (chestr == 2 || chestr == 3 || chestr == 4) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMItems.fairydust));
            }

            if (chestr == 5) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMItems.jar));
            }

            if (chestr == 6) {
               chest.setInventorySlotContents(slot, new ItemStack(Items.IRON_AXE));
            }

            if (chestr == 7) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMItems.wolantler));
            }

            if (chestr == 8) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMBlocks.fairymushroom));
            }

            if (chestr == 9) {
               chest.setInventorySlotContents(slot, new ItemStack(Items.APPLE));
            }

            if (chestr == 10) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMItems.goldarrow));
            }

            if (chestr == 11 && rand.nextBoolean()) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMItems.dendroidchakram));
            }

            if (chestr == 12) {
               chest.setInventorySlotContents(slot, new ItemStack(MAMItems.angryfairyjar));
            }

            if (chestr == 13) {
               ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
               if (rand.nextBoolean()) {
                  net.minecraft.item.ItemEnchantedBook.addEnchantment(book, new net.minecraft.enchantment.EnchantmentData(net.minecraft.init.Enchantments.BANE_OF_ARTHROPODS, rand.nextInt(4) + 1));
               } else {
                  net.minecraft.item.ItemEnchantedBook.addEnchantment(book, new net.minecraft.enchantment.EnchantmentData(net.minecraft.init.Enchantments.THORNS, rand.nextInt(2) + 1));
               }

               chest.setInventorySlotContents(slot, book);
            }
         }

         LegacyWorld.setBlock(world, x + 4, y + 1, z, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 1, z, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 1, z, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 1, z + 1, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 1, z + 1, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 1, z + 1, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 1, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 1, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x, y + 1, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y + 1, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x, y + 1, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y + 1, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x, y + 1, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y + 1, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 1, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 1, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 1, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 1, z + 8, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 1, z + 8, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 1, z + 8, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 1, z + 9, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 1, z + 9, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 2, z, block, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 2, z + 1, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 2, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 2, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 2, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 2, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 2, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 9, y + 2, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x, y + 2, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 2, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x, y + 2, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 2, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 2, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 2, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 2, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 2, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 2, z + 8, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 2, z + 8, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 3, z + 1, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 3, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 3, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 3, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 3, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 3, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 3, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 3, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 3, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 3, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 3, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 8, y + 3, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 3, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 3, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 3, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 3, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 3, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 3, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 4, z + 1, block, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 4, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 4, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 4, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 4, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 4, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 1, y + 4, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 4, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 4, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 4, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 4, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 4, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 4, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 4, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 4, z + 7, block, 0, 2);
         LegacyWorld.setBlock(world, x + 4, y + 5, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 5, z + 2, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 5, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 5, z + 3, block, 0, 2);
         LegacyWorld.setBlock(world, x + 2, y + 5, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 7, y + 5, z + 4, block, 0, 2);
         LegacyWorld.setBlock(world, x + 3, y + 5, z + 5, block, 0, 2);
         LegacyWorld.setBlock(world, x + 5, y + 5, z + 6, block, 0, 2);
         LegacyWorld.setBlock(world, x + 6, y + 5, z + 6, block, 0, 2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean generate(World world, Random rand, net.minecraft.util.math.BlockPos pos) {
      return this.generate(world, rand, pos.getX(), pos.getY(), pos.getZ());
   }
}
