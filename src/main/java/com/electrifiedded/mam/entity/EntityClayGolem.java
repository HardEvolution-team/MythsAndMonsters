package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityClayGolem extends EntityMob {
   public int attack = 0;
   public boolean burn = false;
   public boolean absorb = false;

   public EntityClayGolem(World par1World) {
      super(par1World);
      this.setSize(1.1F, 3.9F);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityFairy.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityJackalopes.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntitySkeleton.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityZombie.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPigZombie.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityEnderman.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntitySilverfish.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityHillGiant.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityGoldenRam.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFairy.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityJackalopes.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPigZombie.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEnderman.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHillGiant.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGoldenRam.class, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      if (this.burn) {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
      }

      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
   }

   protected boolean isAIEnabled() {
      return true;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.CLAY) {
         this.heal(10.0F);
      }

      if (this.burn) {
         this.isImmuneToFire = true;

         for (int i = 0; i < 1; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            LegacyParticles.spawn(this.world, "largesmoke",
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

      if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) != Blocks.AIR
         && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) != Blocks.CLAY
         && this.getHealth() < 50.0F) {
         LegacyWorld.setBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ + 1, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ - 1, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ - 1, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ + 1, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ + 1, Blocks.CLAY);
         LegacyWorld.setBlock(this.world, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ - 1, Blocks.CLAY);
      }
   }

   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("Attacked", this.attack);
      compund.setBoolean("BurnedClay", this.burn);
      compund.setBoolean("AbsorbDamage", this.absorb);
   }

   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.attack = compund.getInteger("Attacked");
      this.burn = compund.getBoolean("BurnedClay");
      this.absorb = compund.getBoolean("AbsorbDamage");
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

   protected void dropFewItems(boolean par1, int par2) {
      this.dropItem(new ItemStack(Blocks.CLAY).getItem(), this.rand.nextInt(50));
   }

   public boolean attackEntityFrom(DamageSource source, float damage) {
      if ((
            source.getTrueSource() instanceof EntityMob
               || source.getTrueSource() instanceof EntityPlayer
               || source.getImmediateSource() instanceof EntityArrow && source.getTrueSource() instanceof EntityPlayer
         )
         && this.attack <= 6
         && !this.world.isRemote) {
         this.attack++;
         EntityClayMinion mob = new EntityClayMinion(this.world);
         mob.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob);
         EntityClayMinion mob2 = new EntityClayMinion(this.world);
         mob2.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob2.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob2);
         EntityClayMinion mob3 = new EntityClayMinion(this.world);
         mob3.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob3.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob3);
         if (this.burn) {
            mob.setFire(10);
            mob2.setFire(10);
            mob3.setFire(10);
         }
      }

      return super.attackEntityFrom(source, damage);
   }
}
