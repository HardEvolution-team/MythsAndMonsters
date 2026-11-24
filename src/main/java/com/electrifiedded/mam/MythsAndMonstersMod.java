package com.electrifiedded.mam;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.original.OriginalModAssets;
import com.electrifiedded.mam.entity.*;
import com.electrifiedded.mam.items.*;
import com.electrifiedded.mam.lib.MAMEventHandler;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.mam.projectile.*;
import com.electrifiedded.mam.proxy.CommonProxy;
import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import com.electrifiedded.mam.registry.MAMPotion;
import com.electrifiedded.mam.registry.MAMRecipe;
import com.electrifiedded.mam.structure.MAMWorldGenerator;
import com.electrifiedded.mam.tabs.MAMTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:archaiclib@[1.40.0,)", guiFactory = "com.electrifiedded.mam.gui.GUIConfigFactory")
public class MythsAndMonstersMod {
   public static final String ORIGINAL_NAME = "modid_1.7.10_1.0.0.jar - Myths & Monsters Mod 1.0.0 for 1.7.10 (by HoopAWolf)";
   public static final String ORIGINAL_URL = "https://www.curseforge.com/minecraft/mc-mods/search?search=myths+monsters";
   public static Configuration configFile;
   public static int SpawnRateSandWyrm;
   public static int SpawnRateFairy;
   public static int SpawnRateGoldenRam;
   public static int SpawnRateDendroid;
   public static int SpawnRateWolpertinger;
   public static int SpawnRateJackalope;
   public static int SpawnRateUrsaWhite;
   public static int SpawnRateUrsaBlack;
   public static int SpawnRateKitsune;
   public static int SpawnRateDropBear;
   public static int SpawnRateHillGiant;
   public static int SpawnRateClayGolem;
   public static int BiomeIceSheet;
   public static int BiomeHauntedOcean;
   public static Item hunterhelmet;
   public static Item hunterchest;
   public static Item hunterlegging;
   public static Item hunterboots;
   public static Item scalechest;
   public static Item scalelegging;
   public static Item dendroidmask;
   @Instance("mam")
   public static MythsAndMonstersMod instance;
   @SidedProxy(clientSide = "com.electrifiedded.mam.proxy.ClientProxy", serverSide = "com.electrifiedded.mam.proxy.CommonProxy")
   public static CommonProxy proxy;
   public static CreativeTabs tabMythsAndMonsters = new MAMTab("tabMythsAndMonsters");

   public MythsAndMonstersMod() {
      // all textures/sounds come from the player's own copy of the original jar (archaic_originals/), never bundled
      OriginalModAssets.hide("mam", "lang/en_us.lang");
      OriginalModAssets.hide("mam", "sounds.json");
   }

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      OriginalModAssets.require("mam", "mam", ORIGINAL_NAME, ORIGINAL_URL, "mam");
      LegacyModSounds.autoRegister("mam");
      configFile = new Configuration(event.getSuggestedConfigurationFile());
      syncConfig();
      MAMItems.createItems();
      MAMBlocks.createBlock();
      MAMPotion.createPotions();
      scalechest = new ItemSandChest(MAMItems.EnumArmorMaterialSand, 0, EntityEquipmentSlot.CHEST);
      RegistryHelper.registerItem(scalechest);
      scalelegging = new ItemSandLegging(MAMItems.EnumArmorMaterialSand, 0, EntityEquipmentSlot.LEGS);
      RegistryHelper.registerItem(scalelegging);
      dendroidmask = new ItemDendroidMask(MAMItems.EnumArmorMaterialDendroid, 0, EntityEquipmentSlot.HEAD);
      RegistryHelper.registerItem(dendroidmask);
      hunterhelmet = new ItemHunterHelmet(MAMItems.EnumArmorMaterialHunter, 0, EntityEquipmentSlot.HEAD);
      RegistryHelper.registerItem(hunterhelmet);
      hunterchest = new ItemHunterChestplate(MAMItems.EnumArmorMaterialHunter, 0, EntityEquipmentSlot.CHEST);
      RegistryHelper.registerItem(hunterchest);
      hunterlegging = new ItemHunterLegging(MAMItems.EnumArmorMaterialHunter, 0, EntityEquipmentSlot.LEGS);
      RegistryHelper.registerItem(hunterlegging);
      hunterboots = new ItemHunterBoots(MAMItems.EnumArmorMaterialHunter, 0, EntityEquipmentSlot.FEET);
      RegistryHelper.registerItem(hunterboots);
      MAMBlocks.registerTileEntity();
      LegacyGameRegistry.registerModEntity(EntityStick.class, "stick", 0, this, 64, 10, true);
      LegacyGameRegistry.registerModEntity(EntityMusic.class, "music", 1, this, 64, 10, true);
      LegacyGameRegistry.registerModEntity(EntityDendroidChakram.class, "dendroidchakram", 2, this, 64, 10, true);
      LegacyGameRegistry.registerModEntity(EntityGoldArrow.class, "goldarrow", 3, this, 64, 10, true);
      LegacyGameRegistry.registerModEntity(EntityFairy.class, "fairy", 4, this, 80, 3, true, 10092543, 16777215);
      LegacyGameRegistry.registerModEntity(EntityCetus.class, "cetus", 5, this, 80, 3, true, 17254, 102);
      LegacyGameRegistry.registerModEntity(EntityHillGiant.class, "hillgiant", 6, this, 80, 3, true, 6697779, 10053222);
      LegacyGameRegistry.registerModEntity(EntityJackalopes.class, "jackalopes", 7, this, 80, 3, true, 16751001, 13408614);
      LegacyGameRegistry.registerModEntity(EntityDendroidElder.class, "dendroidelder", 8, this, 80, 3, true, 10053171, 6697728);
      LegacyGameRegistry.registerModEntity(EntityDendroid.class, "dendroid", 9, this, 80, 3, true, 10053171, 10053171);
      LegacyGameRegistry.registerModEntity(EntityGoodDendroid.class, "dendroidgood", 10, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityDendroidRoot.class, "dendroidroot", 11, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityDendroidSeer.class, "dendroidseer", 12, this, 80, 3, true, 10053171, 26163);
      LegacyGameRegistry.registerModEntity(EntityDendroidSentinel.class, "dendroidsentinel", 13, this, 80, 3, true, 10053171, 6710835);
      LegacyGameRegistry.registerModEntity(EntityHunter.class, "hunter", 14, this, 80, 3, true, 153, 153);
      LegacyGameRegistry.registerModEntity(EntityEliteHunter.class, "hunterelite", 15, this, 80, 3, true, 255, 255);
      LegacyGameRegistry.registerModEntity(EntitySandWyrm.class, "sandwyrm", 16, this, 80, 3, true, 13408614, 16777164);
      LegacyGameRegistry.registerModEntity(EntityGoldenRam.class, "goldenram", 17, this, 80, 3, true, 16777011, 16777215);
      LegacyGameRegistry.registerModEntity(EntityUrsaBlack.class, "ursablack", 18, this, 80, 3, true, 0, 6710886);
      LegacyGameRegistry.registerModEntity(EntityCoreRed.class, "corered", 19, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityUrsaWhite.class, "ursawhite", 20, this, 80, 3, true, 0, 16777215);
      LegacyGameRegistry.registerModEntity(EntityCoreBlue.class, "coreblue", 21, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityDust2.class, "stardustblue", 22, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityDust.class, "stardustred", 23, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityWolpertinger.class, "wolpertinger", 24, this, 80, 3, true, 16751001, 16750899);
      LegacyGameRegistry.registerModEntity(EntityFrostJakalopes.class, "frostjackalopes", 25, this, 80, 3, true, 16751001, 13408614);
      LegacyGameRegistry.registerModEntity(EntityFrostJakalopesOld.class, "oldfrostjackalopes", 26, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityWolpertingerOld.class, "oldwolpertinger", 27, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityJackalopesOld.class, "oldjackalopes", 28, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityKitsune.class, "kitsune", 29, this, 80, 3, true, 16744448, 16671790);
      LegacyGameRegistry.registerModEntity(EntityKitsuneMinion.class, "kitsuneminion", 30, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityDropBear.class, "dropbear", 31, this, 80, 3, true, 6371339, 9054472);
      LegacyGameRegistry.registerModEntity(EntityClayGolem.class, "claygolem", 32, this, 80, 3, true, 12434877, 7237230);
      LegacyGameRegistry.registerModEntity(EntityClayMinion.class, "clayminion", 33, this, 80, 3, true);
      proxy.preInit();
   }

   public static void syncConfig() {
      SpawnRateSandWyrm = configFile.getInt("SandWyrm Spawn Rate", "general", 5, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 5");
      SpawnRateFairy = configFile.getInt("Fairy Spawn Rate", "general", 13, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 13");
      SpawnRateGoldenRam = configFile.getInt("Golden Ram Spawn Rate", "general", 5, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 5");
      SpawnRateDendroid = configFile.getInt("Dendroid Spawn Rate", "general", 5, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 5");
      SpawnRateWolpertinger = configFile.getInt("Wolpertinger Spawn Rate", "general", 12, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 12");
      SpawnRateJackalope = configFile.getInt("Jackalope Spawn Rate", "general", 8, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 8");
      SpawnRateUrsaWhite = configFile.getInt("Celestial Ursa Spawn Rate", "general", 2, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 2");
      SpawnRateUrsaBlack = configFile.getInt("Void Ursa Spawn Rate", "general", 5, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 5");
      SpawnRateKitsune = configFile.getInt("Kitsune Spawn Rate", "general", 3, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 2");
      SpawnRateDropBear = configFile.getInt("Drop Bear Spawn Rate", "general", 2, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 2");
      SpawnRateClayGolem = configFile.getInt("Clay Golem Spawn Rate", "general", 2, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 2");
      SpawnRateHillGiant = configFile.getInt("Hill Giant Spawn Rate", "general", 2, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 2");
      BiomeIceSheet = configFile.getInt("Biome Ice Sheet ID", "general", 45, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 45");
      BiomeHauntedOcean = configFile.getInt("Biome Haunted Ocean ID", "general", 46, 0, Integer.MAX_VALUE, "Min: 0, Max: inf Default: 46");
      if (configFile.hasChanged()) {
         configFile.save();
      }
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      proxy.registerRenderThings();
      MAMRecipe.addRecipes();
      GameRegistry.registerWorldGenerator(new MAMWorldGenerator(), 0);
      EntityRegistry.addSpawn(EntityFairy.class, SpawnRateFairy, 5, 13, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
      EntityRegistry.addSpawn(EntityCetus.class, 5, 1, 5, EnumCreatureType.WATER_CREATURE, Biomes.DEEP_OCEAN);
      EntityRegistry.addSpawn(EntityHillGiant.class, SpawnRateHillGiant, 1, 1, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.EXTREME_HILLS);
      EntityRegistry.addSpawn(EntityJackalopes.class, SpawnRateJackalope, 0, 2, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
      EntityRegistry.addSpawn(EntityDendroidElder.class, SpawnRateDendroid, 0, 2, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
      EntityRegistry.addSpawn(EntitySandWyrm.class, SpawnRateSandWyrm, 3, 5, EnumCreatureType.CREATURE, Biomes.DESERT, Biomes.DESERT_HILLS);
      EntityRegistry.addSpawn(EntityGoldenRam.class, SpawnRateGoldenRam, 0, 1, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
      EntityRegistry.addSpawn(EntityUrsaBlack.class, SpawnRateUrsaBlack, 0, 1, EnumCreatureType.CREATURE, Biomes.ICE_PLAINS);
      EntityRegistry.addSpawn(EntityUrsaWhite.class, SpawnRateUrsaWhite, 0, 1, EnumCreatureType.CREATURE, Biomes.ICE_PLAINS);
      EntityRegistry.addSpawn(EntityWolpertinger.class, SpawnRateWolpertinger, 3, 6, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
      EntityRegistry.addSpawn(EntityFrostJakalopes.class, SpawnRateJackalope, 0, 2, EnumCreatureType.CREATURE, Biomes.ICE_PLAINS, Biomes.ICE_MOUNTAINS);
      EntityRegistry.addSpawn(EntityKitsune.class, SpawnRateKitsune, 0, 3, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS);
      EntityRegistry.addSpawn(EntityDropBear.class, SpawnRateDropBear, 0, 3, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.JUNGLE_HILLS);
      EntityRegistry.addSpawn(EntityClayGolem.class, SpawnRateClayGolem, 1, 1, EnumCreatureType.CREATURE, Biomes.MESA);
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      MinecraftForge.EVENT_BUS.register(new MAMEventHandler());
   }
}
