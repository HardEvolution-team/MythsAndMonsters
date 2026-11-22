package com.electrifiedded.mam.registry;
import com.electrifiedded.archaiclib.legacy.registry.LegacyRecipes;
import net.minecraft.init.Enchantments;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MAMRecipe {
   public static void addRecipes() {
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.goldenlyre, 1),
         new Object[]{"/=/", "/=/", "*/*", '=', MAMItems.goldenstring, '/', Items.GOLD_INGOT, '*', MAMItems.fairydust}
      );
      LegacyRecipes.addRecipe(new ItemStack(MAMItems.badapple, 1), new Object[]{"DDD", "DGD", "DDD", 'G', Items.GOLDEN_APPLE, 'D', MAMItems.dendroidroot});
      LegacyRecipes.addRecipe(new ItemStack(Items.GOLDEN_APPLE, 1, 1), new Object[]{"DDD", "DGD", "DDD", 'G', MAMItems.badapple, 'D', MAMItems.fairydust});
      LegacyRecipes.addRecipe(new ItemStack(MAMBlocks.fairymushroom, 1), new Object[]{"GDD", "DDD", "DDD", 'G', Blocks.RED_MUSHROOM, 'D', MAMItems.fairydust});
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.bowloffairydust, 1), new Object[]{" D ", " D ", " G ", 'G', Items.BOWL, 'D', MAMBlocks.fairymushroom}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.dendroidblade, 1),
         new Object[]{" D ", " R ", " G ", 'G', Items.STICK, 'D', MAMItems.dendroidroot, 'R', MAMItems.dendroideye}
      );
      LegacyRecipes.addRecipe(new ItemStack(MAMItems.scaleleather, 1), new Object[]{" DD", " DG", "   ", 'G', MAMItems.hardleather, 'D', MAMItems.sandscale});
      LegacyRecipes.addRecipe(
         new ItemStack(MythsAndMonstersMod.dendroidmask, 1), new Object[]{"DDG", "DDG", "DDG", 'G', MAMItems.hardleather, 'D', MAMItems.dendroidroot}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(MythsAndMonstersMod.scalelegging, 1), new Object[]{"DGD", "DDD", "   ", 'G', MAMItems.tusk, 'D', MAMItems.scaleleather}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(MythsAndMonstersMod.scalechest, 1), new Object[]{"G G", "DDD", "DDD", 'G', MAMItems.tusk, 'D', MAMItems.scaleleather}
      );
      LegacyRecipes.addRecipe(new ItemStack(MythsAndMonstersMod.hunterhelmet, 1), new Object[]{"DDD", "D D", "   ", 'D', MAMItems.frostjackalopehide});
      LegacyRecipes.addRecipe(new ItemStack(MythsAndMonstersMod.hunterchest, 1), new Object[]{"D D", "DDD", "DDD", 'D', MAMItems.frostjackalopehide});
      LegacyRecipes.addRecipe(new ItemStack(MythsAndMonstersMod.hunterlegging, 1), new Object[]{"DDD", "D D", "D D", 'D', MAMItems.frostjackalopehide});
      LegacyRecipes.addRecipe(new ItemStack(MythsAndMonstersMod.hunterboots, 1), new Object[]{"   ", "D D", "D D", 'D', MAMItems.frostjackalopehide});
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.tusklance, 1), new Object[]{" G ", " G ", "DAD", 'G', MAMItems.tusk, 'D', MAMItems.scaleleather, 'A', Items.STICK}
      );
      LegacyRecipes.addRecipe(new ItemStack(MAMItems.dendroidchakram, 21), new Object[]{"GGG", "G G", "GGG", 'G', MAMItems.dendroidroot});
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.goldbow, 1),
         new Object[]{"*/-", "* =", "*/-", '=', MAMItems.dendroidroot, '/', Items.GOLD_INGOT, '*', MAMItems.goldenstring, '-', MAMItems.fairydust}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.goldarrow, 8),
         new Object[]{" = ", " / ", " # ", '=', Items.GLOWSTONE_DUST, '/', Items.BLAZE_ROD, '#', MAMItems.fairydust}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.dagger, 1), new Object[]{" = ", "=#=", " # ", '=', new ItemStack(MAMItems.wolantler), '#', Items.BONE}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(MAMItems.marrowsword, 1),
         new Object[]{"=-=", "=-=", " # ", '=', Items.GOLD_INGOT, '-', new ItemStack(MAMItems.redstardust), '#', Items.BONE}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(Items.DYE, 2, 15), new Object[]{MAMItems.jackantler});
      LegacyRecipes.addShapelessRecipe(new ItemStack(Items.DYE, 2, 15), new Object[]{MAMItems.wolantler});
      LegacyRecipes.addShapelessRecipe(new ItemStack(MAMItems.goldenstring, 2), new Object[]{MAMItems.goldenfleece});
      ItemStack EBD = new ItemStack(Items.ENCHANTED_BOOK, 1);
      EBD.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
      LegacyRecipes.addShapelessRecipe(
         EBD, new Object[]{MAMItems.depthcrystal, Items.WATER_BUCKET, Items.ENCHANTED_BOOK}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(MAMItems.dendroidroot, 4), new Object[]{Blocks.LOG, MAMItems.taintedseed});
      LegacyRecipes.addShapelessRecipe(new ItemStack(MAMItems.redstardust, 3), new Object[]{MAMItems.redcore});
      LegacyRecipes.addShapelessRecipe(new ItemStack(MAMItems.bluestardust, 3), new Object[]{MAMItems.bluecore});
      LegacyRecipes.addShapelessRecipe(new ItemStack(Blocks.GRASS, 1), new Object[]{Blocks.DIRT, MAMItems.fairydust});
      LegacyRecipes.addShapelessRecipe(
         new ItemStack(Blocks.MYCELIUM, 1), new Object[]{Blocks.DIRT, MAMItems.bluestardust, MAMItems.redstardust}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(MAMItems.dendroidroot, 4), new Object[]{Blocks.LOG2, MAMItems.taintedseed});
      LegacyRecipes.addShapelessRecipe(
         new ItemStack(Items.EXPERIENCE_BOTTLE, 6),
         new Object[]{
            Items.GLASS_BOTTLE,
            MAMItems.bluestardust,
            Items.DIAMOND,
            Items.GLASS_BOTTLE,
            Items.GLASS_BOTTLE,
            Items.GLASS_BOTTLE,
            Items.GLASS_BOTTLE,
            Items.GLASS_BOTTLE
         }
      );
      GameRegistry.addSmelting(new ItemStack(Items.LEATHER), new ItemStack(MAMItems.hardleather), 0.5F);
      GameRegistry.addSmelting(new ItemStack(MAMItems.dendroidroot), new ItemStack(Items.COAL), 0.5F);
      GameRegistry.addSmelting(new ItemStack(MAMItems.jackalopemeat), new ItemStack(MAMItems.jackalopemeatcooked), 0.1F);
      GameRegistry.addSmelting(new ItemStack(MAMItems.goldenstring), new ItemStack(Items.GOLD_NUGGET), 0.1F);
   }
}
