package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityClayMinion extends EntityMob {
   public boolean burn = false;
   public boolean absorb = false;

   public EntityClayMinion(World par1World) {
      super(par1World);
      this.setSize(0.6F, 0.7F);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      if (this.burn) {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
      }

      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.burn) {
         this.isImmuneToFire = true;

         for (int i = 0; i < 1; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            LegacyParticles.spawn(this.world, "smoke",
                  this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
                  this.posY + this.rand.nextFloat() * this.height - d1 * d3,
                  this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
                  d,
                  d1,
                  d2
               );
            if (!this.absorb) {
               this.setAbsorptionAmount(25.0F);
               this.absorb = true;
            }
         }
      }

      if (this.isBurning() && !this.burn) {
         this.burn = true;
      }
   }

   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setBoolean("BurnedClayMinion", this.burn);
      compund.setBoolean("AbsorbDamageMinion", this.absorb);
   }

   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.burn = compund.getBoolean("BurnedClayMinion");
      this.absorb = compund.getBoolean("AbsorbDamageMinion");
   }

   protected void playStepSound(BlockPos stepPos, Block blockIn) {
      this.playSound(LegacyModSounds.get("mam", "step.gravel"), 0.15F, 1.0F);
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "dig.gravel");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "dig.gravel");
   }

   protected boolean isAIEnabled() {
      return true;
   }

   protected void dropFewItems(boolean par1, int par2) {
      this.dropItem(Items.CLAY_BALL, this.rand.nextInt(5));
   }
}
