package com.electrifiedded.mam.structure;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MAMWorldGenerator implements IWorldGenerator {
   @Override
   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
      if (world.provider.getDimension() == 0) {
         // +8 offset: keep the 1.7 structures inside the already generated chunks (no cascading worldgen)
         this.generateSurface(world, random, chunkX * 16 + 8, chunkZ * 16 + 8);
      }
   }

   private void generateSurface(World world, Random random, int blockX, int blockZ) {
      Biome biome = world.getBiome(new BlockPos(blockX, 64, blockZ));
      if (BiomeDictionary.hasType(biome, Type.FOREST) && !BiomeDictionary.hasType(biome, Type.SNOWY)) {
         for (int a = 0; a < 6; a++) {
            int x = blockX + random.nextInt(16);
            int y = random.nextInt(150);
            int z = blockZ + random.nextInt(16);
            new StructureForest().generate(world, random, x, y, z);
         }
      } else if (biome instanceof BiomeOcean) {
         new StructureUnderWater().generate(world, random, blockX, 45, blockZ);
      }
   }
}
