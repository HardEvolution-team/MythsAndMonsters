package com.electrifiedded.mam.blocks;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFairyMushroom extends MAMBlock {
   public BlockFairyMushroom() {
      super(Material.PLANTS, "mam:BlockBlueMushroom");
      this.setTranslationKey("fairymushroom");
      this.setHardness(0.0F);
      this.setSoundType(SoundType.PLANT);
      this.setLightLevel(0.5F);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTickRandomly(true);
      this.legacyRender(1);
      this.setLegacyTranslucent(true);
   }

   @Override
   protected float[] getLegacyBounds(int meta) {
      float f = 0.2F;
      return new float[]{0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f};
   }

   @Override
   protected boolean hasLegacyCollision(int meta) {
      return false;
   }

   private static boolean canStay(World world, BlockPos pos) {
      Block below = world.getBlockState(pos.down()).getBlock();
      return below == Blocks.GRASS || below == Blocks.DIRT;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return canStay(world, pos) && super.canPlaceBlockAt(world, pos);
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      if (!canStay(world, pos)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
      }
   }
}
