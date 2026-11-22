package com.electrifiedded.mam.entity;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIEatGrassPlus extends EntityAIBase {
   private EntityLiving theEntity;
   private World theWorld;
   int eatGrassTickPlus = 0;

   public EntityAIEatGrassPlus(EntityLiving par1EntityLiving) {
      this.theEntity = par1EntityLiving;
      this.theWorld = par1EntityLiving.world;
      this.setMutexBits(7);
   }

   public boolean shouldExecute() {
      if (this.theEntity.getRNG().nextInt(this.theEntity.isChild() ? 50 : 1000) != 0) {
         return false;
      } else {
         int i = MathHelper.floor(this.theEntity.posX);
         int j = MathHelper.floor(this.theEntity.posY);
         int k = MathHelper.floor(this.theEntity.posZ);
         return LegacyWorld.getBlock(this.theWorld, i, j, k) == Blocks.TALLGRASS && LegacyWorld.getBlockMetadata(this.theWorld, i, j, k) == 1
            ? true
            : LegacyWorld.getBlock(this.theWorld, i, j - 1, k) == Blocks.GRASS;
      }
   }

   public void startExecuting() {
      this.eatGrassTickPlus = 40;
      this.theWorld.setEntityState(this.theEntity, (byte)10);
      this.theEntity.getNavigator().clearPath();
   }

   public void resetTask() {
      this.eatGrassTickPlus = 0;
   }

   public boolean continueExecuting() {
      return this.eatGrassTickPlus > 0;
   }

   public int func_48396_h() {
      return this.eatGrassTickPlus;
   }

   public void updateTask() {
      this.eatGrassTickPlus = Math.max(0, this.eatGrassTickPlus - 1);
      if (this.eatGrassTickPlus == 4) {
         int i = MathHelper.floor(this.theEntity.posX);
         int j = MathHelper.floor(this.theEntity.posY);
         int k = MathHelper.floor(this.theEntity.posZ);
         if (LegacyWorld.getBlock(this.theWorld, i, j, k) == Blocks.TALLGRASS) {
            this.theWorld.playEvent(2001, new BlockPos(i, j, k), 1);
            LegacyWorld.setBlock(this.theWorld, i, j, k, Blocks.AIR, 0, 2);
            this.theEntity.eatGrassBonus();
         } else if (LegacyWorld.getBlock(this.theWorld, i, j - 1, k) == Blocks.GRASS) {
            this.theWorld.playEvent(2001, new BlockPos(i, j - 1, k), 1);
            LegacyWorld.setBlock(this.theWorld, i, j - 1, k, Blocks.DIRT, 0, 2);
            this.theEntity.eatGrassBonus();
         }
      }
   }
}
