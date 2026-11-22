package com.electrifiedded.mam.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDendroidElderSpawner extends MAMBlock {
   public BlockDendroidElderSpawner() {
      super(Material.WOOD, "planks_oak");
      this.setTranslationKey("mamdendroidspawner");
      this.setHardness(0.6F);
      this.setSoundType(SoundType.WOOD);
   }

   @Override
   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   @Override
   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileEntityDendroidElderSpawner();
   }
}
