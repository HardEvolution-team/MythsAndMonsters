package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityUrsaBlack extends EntityMob implements IMob {
   protected Entity entityToAttack;
   protected int fleeingTick;
   protected boolean hasAttacked;

   public Entity entity = null;
   boolean tp = false;
   boolean tp1 = false;
   int xx = (int)Math.floor(this.posX);
   int yy = (int)Math.floor(this.posY);
   int zz = (int)Math.floor(this.posZ);
   Block id = LegacyWorld.getBlock(this.world, this.xx, this.yy, this.zz);
   int danceWithYou = 0;

   public EntityUrsaBlack(World par1World) {
      super(par1World);
      this.hasAttacked = false;
      this.fleeingTick = 0;
      this.setSize(1.3F, 1.5F);
      this.experienceValue = 30;
      this.stepHeight = 1.0F;
      
      this.getNavigator().onUpdateNavigation();
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.5, true));
      this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.5, 32.0F));
      this.tasks.addTask(4, new EntityAIWander(this, 0.5));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityOcelot.class, 16.0F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityUrsaWhite.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHunter.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEliteHunter.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(140.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
   }

   protected boolean teleportRandomly() {
      double d = this.posX + (this.rand.nextDouble() - 0.5) * 64.0;
      double d1 = this.posY + (this.rand.nextInt(64) - 32);
      double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 64.0;
      return this.teleportTo(d, d1, d2);
   }

   protected boolean teleportTo(double par1, double par3, double par5) {
      double d = this.posX;
      double d1 = this.posY;
      double d2 = this.posZ;
      this.posX = par1;
      this.posY = par3;
      this.posZ = par5;
      boolean flag = false;
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.posY);
      int k = MathHelper.floor(this.posZ);
      if (LegacyWorld.blockExists(this.world, i, j, k)) {
         boolean flag1 = false;

         while (!flag1 && j > 0) {
            Block i1 = LegacyWorld.getBlock(this.world, i, j - 1, k);
            if (i1 != Blocks.AIR && i1.getDefaultState().getMaterial().blocksMovement()) {
               flag1 = true;
            } else {
               this.posY--;
               j--;
            }
         }

         if (flag1) {
            this.setPosition(this.posX, this.posY, this.posZ);
            if (this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).size() == 0 && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
               flag = true;
            }
         }
      }

      if (!flag) {
         this.setPosition(d, d1, d2);
         return false;
      } else {
         int l = 128;

         for (int j1 = 0; j1 < l; j1++) {
            double d3 = j1 / (l - 1.0);
            float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            double d4 = d + (this.posX - d) * d3 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
            double d5 = d1 + (this.posY - d1) * d3 + this.rand.nextDouble() * this.height;
            double d6 = d2 + (this.posZ - d2) * d3 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
         }

         LegacyWorld.playSoundEffect(this.world, d, d1, d2, "mob.endermen.portal", 1.0F, 1.0F);
         LegacyWorld.playSoundAtEntity(this.world, this, "mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void updateAITasks() {
      super.updateAITasks();
      if (this.isInWater() || this.id == Blocks.FLOWING_LAVA || this.id == Blocks.LAVA) {
         this.teleportRandomly();
      }

      if (this.getHealth() <= 30.0F && !this.tp) {
         this.teleportRandomly();
         this.tp = true;
      }

      if (this.getHealth() <= 70.0F && !this.tp1) {
         this.teleportRandomly();
         this.tp1 = true;
      }
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      this.danceWithYou++;
      this.entity = par1DamageSource.getTrueSource();
      if (this.isEntityInvulnerable(par1DamageSource)) {
         return false;
      } else if (super.attackEntityFrom(par1DamageSource, par2)) {
         Entity var3 = par1DamageSource.getTrueSource();
         if (LegacyEntityHelper.getRider(this) != var3 && this.getRidingEntity() != var3) {
            if (var3 != this) {
               this.entityToAttack = var3;
            }

            return true;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   protected SoundEvent getAmbientSound() {
      return LegacyModSounds.get("mam", "mob.blaze.breathe");
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "mob.blaze.hit");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "mob.blaze.death");
   }

   protected float getSoundVolume() {
      return 0.7F;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.getAttackTarget() != null && this.getAttackTarget().isDead) {
         this.setAttackTarget(null);
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(2.0, 2.0, 2.0));

      for (int i = 0; i < list.size(); i++) {
         Entity entity1 = (Entity)list.get(i);
         if (entity1 instanceof EntityPlayer) {
            ((EntityPlayer)entity1).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 6));
         }

         if (entity1 instanceof EntityLiving
            && !(entity1 instanceof EntityEnderman)
            && !(entity1 instanceof EntityUrsaBlack)
            && !(entity1 instanceof EntityCoreRed)) {
            if (this.rand.nextInt(12) == 0) {
               entity1.attackEntityFrom(DamageSource.MAGIC, 1.0F);
               LegacyParticles.spawn(this.world, "portal",
                     entity1.posX + (this.rand.nextDouble() - 0.5) * entity1.width,
                     entity1.posY + this.rand.nextDouble() * entity1.height - 0.25,
                     entity1.posZ + (this.rand.nextDouble() - 0.5) * entity1.width,
                     (this.rand.nextDouble() - 0.5) * 2.0,
                     -this.rand.nextDouble(),
                     (this.rand.nextDouble() - 0.5) * 2.0
                  );
               LegacyParticles.spawn(this.world, "portal",
                     entity1.posX + (this.rand.nextDouble() - 0.5) * entity1.width,
                     entity1.posY + this.rand.nextDouble() * entity1.height - 0.25,
                     entity1.posZ + (this.rand.nextDouble() - 0.5) * entity1.width,
                     (this.rand.nextDouble() - 0.5) * 2.0,
                     -this.rand.nextDouble(),
                     (this.rand.nextDouble() - 0.5) * 2.0
                  );
               LegacyParticles.spawn(this.world, "portal",
                     entity1.posX + (this.rand.nextDouble() - 0.5) * entity1.width,
                     entity1.posY + this.rand.nextDouble() * entity1.height - 0.25,
                     entity1.posZ + (this.rand.nextDouble() - 0.5) * entity1.width,
                     (this.rand.nextDouble() - 0.5) * 2.0,
                     -this.rand.nextDouble(),
                     (this.rand.nextDouble() - 0.5) * 2.0
                  );
            }

            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
         }
      }

      if (this.world.isDaytime() && !this.world.isRemote) {
         this.setDead();
         LegacyWorld.playSoundAtEntity(this.world, this, "mob.endermen.portal", 1.3F, 0.8F);
         LegacyParticles.spawn(this.world, "portal",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         LegacyParticles.spawn(this.world, "portal",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         LegacyParticles.spawn(this.world, "portal",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         LegacyParticles.spawn(this.world, "portal",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         LegacyParticles.spawn(this.world, "portal",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         LegacyParticles.spawn(this.world, "portal",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
      }
   }

   public void fall(float f, float damageMultiplier) {
   }

   public void setDead() {
      if (!this.world.isRemote && this.danceWithYou > 10) {
         LegacyParticles.spawn(this.world, "flame",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         EntityCoreRed entitycore = new EntityCoreRed(this.world);
         entitycore.setLocationAndAngles(this.posX + 0.2F, this.posY + 0.8F, this.posZ + 0.2F, this.rotationYaw, 0.0F);
         entitycore.willDrop = true;
         MAMEntityUtil.spawnInWorld(this.world, entitycore);
         EntityCoreRed entitycore2 = new EntityCoreRed(this.world);
         entitycore2.setLocationAndAngles(this.posX + 0.2F, this.posY + 0.8F, this.posZ + 0.2F, this.rotationYaw, 0.0F);
         entitycore2.willDrop = true;
         MAMEntityUtil.spawnInWorld(this.world, entitycore2);
         EntityCoreRed entitycore3 = new EntityCoreRed(this.world);
         entitycore3.setLocationAndAngles(this.posX + 0.2F, this.posY + 0.8F, this.posZ + 0.2F, this.rotationYaw, 0.0F);
         entitycore3.willDrop = true;
         MAMEntityUtil.spawnInWorld(this.world, entitycore3);
         entitycore.addVelocity(
            (this.rand.nextFloat() - 0.5) / 2.0, (this.rand.nextFloat() - 0.5) / 2.0, (this.rand.nextFloat() - 0.5) / 2.0
         );
         entitycore2.addVelocity(
            (this.rand.nextFloat() - 0.5) / 2.0, (this.rand.nextFloat() - 0.5) / 2.0, (this.rand.nextFloat() - 0.5) / 2.0
         );
         entitycore3.addVelocity(
            (this.rand.nextFloat() - 0.5) / 2.0, (this.rand.nextFloat() - 0.5) / 2.0, (this.rand.nextFloat() - 0.5) / 2.0
         );
      }

      this.isDead = true;
   }
}
