package com.electrifiedded.mam.blocks;

import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.mam.entity.EntityFairy;
import com.electrifiedded.mam.lib.MAMCompat;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Glass jar blocks (empty / angry fairy / happy fairy) and the depth crystal: TESR-rendered, small box. */
public class BlockJars extends MAMBlock {
   private final Supplier<TileEntity> tile;
   private final Supplier<Item> drop;

   public BlockJars(String name, String icon, Supplier<TileEntity> tile, Supplier<Item> drop) {
      super(Material.GLASS, icon);
      this.tile = tile;
      this.drop = drop;
      this.setTranslationKey(name);
      this.setHardness(0.6F);
      this.setSoundType(SoundType.GLASS);
      this.legacyRender(-1);
   }

   @Override
   protected float[] getLegacyBounds(int meta) {
      return new float[]{0.2F, 0.0F, 0.2F, 0.8F, 0.82F, 0.8F};
   }

   @Override
   public Item getItemDropped(IBlockState state, Random random, int fortune) {
      return this.drop.get();
   }

   @Override
   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   @Override
   public TileEntity createTileEntity(World world, IBlockState state) {
      return this.tile.get();
   }

   /** Empty jar: a fairy flying into it is caught. */
   public static class Empty extends BlockJars {
      public Empty() {
         super("blockjar", "mam:ItemFairyJar", TileEntityJar::new, () -> MAMItems.jar);
      }

      @Override
      public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
         if (entity instanceof EntityFairy && !world.isRemote) {
            entity.setDead();
            world.setBlockState(pos, MAMBlocks.angryfairyjar.getDefaultState());
         }
      }
   }

   public static class Angry extends BlockJars {
      public Angry() {
         super("blockangryfairyjar", "mam:ItemFairyJarAngry", TileEntityAngryFairyJar::new, () -> MAMItems.angryfairyjar);
         this.setLightLevel(0.3F);
      }

      @Override
      @SideOnly(Side.CLIENT)
      public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
         LegacyParticles.spawn(world, "reddust", pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
      }

      @Override
      public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
         if (entity instanceof EntityFairy && !world.isRemote && world.getBlockState(pos).getBlock() == MAMBlocks.angryfairyjar) {
            world.setBlockToAir(pos);
            EntityFairy rescued = new EntityFairy(world);
            rescued.setPosition(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
            rescued.bottleRelease();
            MAMEntityUtil.spawnInWorld(world, rescued);
            rescued.dropItem(MAMItems.fairydust, 1);
            rescued.dropItem(MAMItems.jar, 1);
         }
      }

      @Override
      public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
         ItemStack held = player.getHeldItem(hand);
         if (!held.isEmpty() && held.getItem() == Items.SUGAR) {
            held.shrink(1);
            world.setBlockState(pos, MAMBlocks.happyfairyjar.getDefaultState());
            return true;
         }
         return false;
      }
   }

   public static class Happy extends BlockJars {
      public int limit = 0;

      public Happy() {
         super("blockhappyfairyjar", "mam:ItemFairyJarHappy", TileEntityHappyFairyJar::new, () -> MAMItems.happyfairyjar);
         this.setLightLevel(1.0F);
      }

      @Override
      public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
         int x = pos.getX(), y = pos.getY(), z = pos.getZ();
         if (!world.isRemote
            && MAMCompat.canSeeSky(world, x - 1, y, z - 1)
            && MAMCompat.canSeeSky(world, x - 1, y, z)
            && MAMCompat.canSeeSky(world, x - 1, y, z + 1)
            && MAMCompat.canSeeSky(world, x, y, z - 1)
            && MAMCompat.canSeeSky(world, x, y + 1, z)
            && MAMCompat.canSeeSky(world, x, y, z + 1)
            && MAMCompat.canSeeSky(world, x + 1, y, z - 1)
            && MAMCompat.canSeeSky(world, x + 1, y, z)
            && MAMCompat.canSeeSky(world, x + 1, y, z + 1)) {
            if (this.limit != 0) {
               this.limit--;
            }
         }
      }

      @Override
      public int tickRate(World worldIn) {
         return 2;
      }

      @Override
      public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
         Random rand = world.rand;
         int randomNumber = rand.nextInt(100);
         this.limit++;
         if (!world.isRemote && randomNumber < 15 && this.limit <= 20) {
            player.dropItem(MAMItems.fairydust, 1);
            return true;
         } else if (this.limit >= 20) {
            double d = rand.nextGaussian() * 0.02;
            double d1 = rand.nextGaussian() * 0.02;
            double d2 = rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            LegacyParticles.spawn(world, "angryVillager",
               player.posX + rand.nextFloat() * player.width * 2.0F - player.width - d * d3,
               player.posY + (rand.nextFloat() - 1.0F) - d1 * d3,
               player.posZ + rand.nextFloat() * player.width * 2.0F - player.width - d2 * d3, d, d1, d2);
            return true;
         }
         return false;
      }

      @Override
      public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
         if (entity instanceof EntityFairy && !world.isRemote && world.getBlockState(pos).getBlock() == MAMBlocks.happyfairyjar) {
            world.setBlockToAir(pos);
            EntityFairy rescued = new EntityFairy(world);
            rescued.setPosition(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
            MAMEntityUtil.spawnInWorld(world, rescued);
            rescued.dropItem(MAMItems.fairydust, 1);
            rescued.dropItem(MAMItems.jar, 1);
         }
      }
   }

   public static class DepthCrystal extends BlockJars {
      public DepthCrystal() {
         super("depthcrystalblock", "mam:ItemDepthCrystal", TileEntityDepthCrystal::new, () -> MAMItems.depthcrystal);
      }

      @Override
      @SideOnly(Side.CLIENT)
      public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
         if (random.nextInt(10) == 0) {
            LegacyParticles.spawn(world, "bubble", pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
         }
      }
   }
}
