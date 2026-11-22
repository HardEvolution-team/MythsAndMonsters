package com.electrifiedded.mam.structure;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureUnderWater extends WorldGenerator {
   public boolean generate(World world, Random rand, int x, int y, int z) {
      Block Water = Blocks.FLOWING_WATER;
      Block Water2 = Blocks.WATER;
      int yHeight = 0;

      for (int var11 = 0; var11 > 24; var11++) {
         if (LegacyWorld.getBlock(world, x, y + var11, z) != Water && LegacyWorld.getBlock(world, x, y + var11, z) != Water2) {
            return false;
         }
      }

      int floorBelow = 0;

      for (int yLowerHeight = 0; yLowerHeight > -20; yLowerHeight--) {
         if (LegacyWorld.getBlock(world, x, y + yLowerHeight, z) != Water && LegacyWorld.getBlock(world, x, y + yLowerHeight, z) != Water2) {
            return this.generateStructure(world, rand, x, yLowerHeight + y, z);
         }
      }

      return false;
   }

   public boolean generateStructure(World world, Random rand, int x, int y, int z) {
      if (y > 38) {
         return false;
      } else if (y > 32) {
         return false;
      } else if (rand.nextInt(70) == 0) {
         System.out.println("x: " + x);
         System.out.println("y: " + y);
         System.out.println("z: " + z);
         return new AtlantisEntrance().generate(world, rand, x, y, z);
      } else {
         return false;
      }
   }

   @Override
   public boolean generate(World world, Random rand, net.minecraft.util.math.BlockPos pos) {
      return this.generate(world, rand, pos.getX(), pos.getY(), pos.getZ());
   }
}
