package com.electrifiedded.mam.registry;

import com.electrifiedded.mam.blocks.*;
import com.electrifiedded.mam.lib.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class MAMBlocks {
   public static Block dendroidsapling;
   public static Block fairymushroom;
   public static Block gravel;
   public static Block angryfairyjar;
   public static Block fairyjar;
   public static Block jar;
   public static Block dendroidelderspawner;
   public static Block depthquartz;
   public static Block depthquartzchiseled;
   public static Block depthquartzlines;
   public static Block happyfairyjar;
   public static Block depthtcrystal;

   public static void createBlock() {
      dendroidsapling = new BlockDendroidSapling();
      RegistryHelper.registerBlock(dendroidsapling);
      fairymushroom = new BlockFairyMushroom();
      RegistryHelper.registerBlock(fairymushroom);
      gravel = new BlockGravelMAM();
      RegistryHelper.registerBlock(gravel);
      dendroidelderspawner = new BlockDendroidElderSpawner();
      RegistryHelper.registerBlock(dendroidelderspawner);
      depthquartz = new BlockDepthQuartz("depthquartz", "mam:BlockDepthQuartzSide", "mam:BlockDepthQuartzTop", "mam:BlockDepthQuartzBottom");
      RegistryHelper.registerBlock(depthquartz);
      depthquartzchiseled = new BlockDepthQuartz("depthquartzchiseled", "mam:BlockDepthQuartzChiseled", "mam:BlockDepthQuartzChiseledTop", null);
      RegistryHelper.registerBlock(depthquartzchiseled);
      depthquartzlines = new BlockDepthQuartz("depthquartzlines", "mam:BlockDepthQuartzLines", "mam:BlockDepthQuartzLinesTop", null);
      RegistryHelper.registerBlock(depthquartzlines);
      jar = new BlockJars.Empty();
      RegistryHelper.registerBlock(jar);
      angryfairyjar = new BlockJars.Angry();
      RegistryHelper.registerBlock(angryfairyjar);
      happyfairyjar = new BlockJars.Happy();
      RegistryHelper.registerBlock(happyfairyjar);
      depthtcrystal = new BlockJars.DepthCrystal();
      RegistryHelper.registerBlock(depthtcrystal);
   }

   public static void registerTileEntity() {
      GameRegistry.registerTileEntity(TileEntityGravelMAM.class, new ResourceLocation("mam", "gravel"));
      GameRegistry.registerTileEntity(TileEntityDendroidElderSpawner.class, new ResourceLocation("mam", "dendroid_elder_spawner"));
      GameRegistry.registerTileEntity(TileEntityJar.class, new ResourceLocation("mam", "jar"));
      GameRegistry.registerTileEntity(TileEntityAngryFairyJar.class, new ResourceLocation("mam", "angry_jar"));
      GameRegistry.registerTileEntity(TileEntityHappyFairyJar.class, new ResourceLocation("mam", "happy_jar"));
      GameRegistry.registerTileEntity(TileEntityDepthCrystal.class, new ResourceLocation("mam", "depth_crystal"));
   }
}
