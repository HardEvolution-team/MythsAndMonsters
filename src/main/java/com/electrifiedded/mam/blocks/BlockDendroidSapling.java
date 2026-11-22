package com.electrifiedded.mam.blocks;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;
import com.electrifiedded.mam.entity.EntityDendroidRoot;
import com.electrifiedded.mam.entity.EntityDendroidSeer;
import com.electrifiedded.mam.entity.EntityDendroidSentinel;
import com.electrifiedded.mam.lib.MAMCompat;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDendroidSapling extends MAMBlock {
   public BlockDendroidSapling() {
      super(Material.PLANTS, "mam:BlockDendroidSapling");
      this.setTranslationKey("dendroidsapling");
      this.setHardness(0.0F);
      this.setSoundType(SoundType.PLANT);
      this.setTickRandomly(true);
      this.legacyRender(1);
   }

   @Override
   protected float[] getLegacyBounds(int meta) {
      float f = 0.2F;
      return new float[]{0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f};
   }

   @Override
   protected boolean hasLegacyCollision(int meta) {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).getBlock() == Blocks.MYCELIUM;
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      this.checkAndDropBlock(world, pos);
   }

   protected void checkAndDropBlock(World world, BlockPos pos) {
      if (!this.canBlockStay(world, pos)) {
         world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
         Block.spawnAsEntity(world, pos, new ItemStack(MAMItems.taintedseed));
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random random, int fortune) {
      return MAMItems.taintedseed;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         int x = pos.getX(), y = pos.getY(), z = pos.getZ();
         if (MAMCompat.canSeeSky(world, x - 1, y, z - 1)
            && MAMCompat.canSeeSky(world, x - 1, y, z)
            && MAMCompat.canSeeSky(world, x - 1, y, z + 1)
            && MAMCompat.canSeeSky(world, x, y, z - 1)
            && MAMCompat.canSeeSky(world, x, y + 1, z)
            && MAMCompat.canSeeSky(world, x, y, z + 1)
            && MAMCompat.canSeeSky(world, x + 1, y, z - 1)
            && MAMCompat.canSeeSky(world, x + 1, y, z)
            && MAMCompat.canSeeSky(world, x + 1, y, z + 1)) {
            this.updateGrowth(world, x, y, z, rand);
         }
         if (world.getBlockState(pos).getBlock() == this) {
            this.checkAndDropBlock(world, pos);
         }
      }
   }

   public void updateGrowth(World par1World, int par2, int par3, int par4, Random par5Random) {
      int l = LegacyWorld.getBlockMetadata(par1World, par2, par3, par4);
      if (l <= 8) {
         LegacyWorld.setBlockMetadataWithNotify(par1World, par2, par3, par4, l + 1, 4);
         for (int x = -l; x <= l; x++) {
            for (int z = -l; z <= l; z++) {
               for (int y = -5; y <= 5; y++) {
                  Block over = LegacyWorld.getBlock(par1World, par2 + x, par3 + y + 1, par4 + z);
                  if (over == Blocks.AIR) {
                     if (!MAMCompat.canSeeSky(par1World, par2 + x, par3 + y + 1, par4 + z)) {
                        if (LegacyWorld.getBlock(par1World, par2 + x, par3 + y, par4 + z) == Blocks.MYCELIUM) {
                           EntityDendroidRoot root = new EntityDendroidRoot(par1World, par2 + x, par3 + y, par4 + z, par2, par3, par4);
                           if (l > 3 && par5Random.nextInt(10 - l) == 0) {
                              root.grows = true;
                           }
                           root.setPosition(par2 + 0.2 + par5Random.nextInt(6) / 10 + x, par3 + y + 1, par4 + 0.2 + par5Random.nextInt(6) / 10 + z);
                           par1World.spawnEntity(root);
                        } else if (LegacyWorld.getBlock(par1World, par2 + x, par3 + y, par4 + z) == Blocks.DIRT) {
                           LegacyWorld.setBlock(par1World, par2 + x, par3 + y, par4 + z, Blocks.GRASS, 0, 2);
                        }
                     } else if (LegacyWorld.getBlock(par1World, par2 + x, par3 + y, par4 + z) == Blocks.MYCELIUM && par5Random.nextInt(5) == 0) {
                        EntityDendroidRoot root = new EntityDendroidRoot(par1World, par2 + x, par3 + y, par4 + z, par2, par3, par4);
                        if (l > 3 && par5Random.nextInt(2) == 0) {
                           root.grows = true;
                        }
                        root.setPosition(par2 + 0.2 + par5Random.nextInt(6) / 10 + x, par3 + y + 1, par4 + 0.2 + par5Random.nextInt(6) / 10 + z);
                        par1World.spawnEntity(root);
                     }
                  }
                  if (LegacyWorld.getBlock(par1World, par2 + x, par3 + y, par4 + z) == Blocks.GRASS && par5Random.nextInt(2) == 0) {
                     LegacyWorld.setBlock(par1World, par2 + x, par3 + y, par4 + z, Blocks.MYCELIUM, 0, 2);
                     if (!par1World.isRemote && par1World.getGameRules().getBoolean("doMobLoot")) {
                        par1World.spawnEntity(new EntityXPOrb(par1World, par2 + x + par5Random.nextFloat(), par3 + y + 1, par4 + z + par5Random.nextFloat(), 1));
                     }
                  }
               }
            }
         }
      } else {
         AxisAlignedBB box = new AxisAlignedBB(par2, par3, par4, par2, par3, par4).grow(12.0);
         boolean dendyhere = !par1World.getEntitiesWithinAABB(EntityDendroidRoot.class, box).isEmpty();
         LegacyWorld.setBlock(par1World, par2, par3, par4, Blocks.AIR, 0, 2);
         if (dendyhere) {
            EntityDendroidSentinel sentinel = new EntityDendroidSentinel(par1World, par2, par3, par4, 24);
            sentinel.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
            MAMEntityUtil.spawnInWorld(par1World, sentinel);
         } else {
            EntityDendroidSeer seer = new EntityDendroidSeer(par1World, par2, par3, par4, 24);
            seer.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
            MAMEntityUtil.spawnInWorld(par1World, seer);
         }
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!world.isRemote && player.capabilities.isCreativeMode) {
         this.updateGrowth(world, pos.getX(), pos.getY(), pos.getZ(), world.rand);
         return true;
      }
      return false;
   }
}
