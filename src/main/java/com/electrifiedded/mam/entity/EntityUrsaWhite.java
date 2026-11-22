package com.electrifiedded.mam.entity;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityUrsaWhite extends EntityMAMEntityCreature implements IAnimals {
   public Entity entity = null;
   boolean tp = false;
   boolean tp1 = false;
   int xx = (int)Math.floor(this.posX);
   int yy = (int)Math.floor(this.posY);
   int zz = (int)Math.floor(this.posZ);
   Block id = LegacyWorld.getBlock(this.world, this.xx, this.yy, this.zz);
   int danceWithMe = 0;
   boolean gotcha = false;
   int target = 0;

   public EntityUrsaWhite(World par1World) {
      super(par1World);
      this.hasAttacked = false;
      this.fleeingTick = 0;
      this.setSize(1.3F, 1.5F);
      this.experienceValue = 35;
      this.stepHeight = 1.0F;
      
      this.getNavigator().onUpdateNavigation();
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.5, true));
      this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.5, 32.0F));
      this.tasks.addTask(4, new EntityAIWander(this, 0.5));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityUrsaBlack.class, 16.0F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityUrsaBlack.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
   }

   protected boolean teleportRandomly() {
      double d0 = this.posX + (this.rand.nextDouble() - 0.5) * 64.0;
      double d1 = this.posY + (this.rand.nextInt(64) - 32);
      double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 64.0;
      return this.teleportTo(d0, d1, d2);
   }

   protected boolean teleportTo(double par1, double par3, double par5) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3, par5, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         double d3 = this.posX;
         double d4 = this.posY;
         double d5 = this.posZ;
         this.posX = event.getTargetX();
         this.posY = event.getTargetY();
         this.posZ = event.getTargetZ();
         boolean flag = false;
         int i = MathHelper.floor(this.posX);
         int j = MathHelper.floor(this.posY);
         int k = MathHelper.floor(this.posZ);
         if (LegacyWorld.blockExists(this.world, i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
               Block block = LegacyWorld.getBlock(this.world, i, j - 1, k);
               if (block.getDefaultState().getMaterial().blocksMovement()) {
                  flag1 = true;
               } else {
                  this.posY--;
                  j--;
               }
            }

            if (flag1) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if (this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
                  flag = true;
               }
            }
         }

         if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
         } else {
            short short1 = 128;

            for (int l = 0; l < short1; l++) {
               double d6 = l / (short1 - 1.0);
               float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
               double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               LegacyParticles.spawn(this.world, "portal", d7, d8, d9, f, f1, f2);
            }

            LegacyWorld.playSoundEffect(this.world, d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound(LegacyModSounds.get("mam", "mob.endermen.portal"), 1.0F, 1.0F);
            return true;
         }
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
      this.danceWithMe++;
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

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(2.0, 2.0, 2.0));

      for (int i = 0; i < list.size(); i++) {
         Entity entity1 = (Entity)list.get(i);
         if (entity1 instanceof EntityPlayer) {
            ((EntityPlayer)entity1).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200, 6));
         } else if (entity1 instanceof EntityMob && !(entity1 instanceof EntityEnderman) || entity1 instanceof EntityCoreRed) {
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
      if (!this.world.isRemote && this.danceWithMe > 10) {
         LegacyParticles.spawn(this.world, "flame",
               this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25,
               this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0,
               -this.rand.nextDouble(),
               (this.rand.nextDouble() - 0.5) * 2.0
            );
         EntityCoreBlue entitycore = new EntityCoreBlue(this.world);
         entitycore.setLocationAndAngles(this.posX + 0.2F, this.posY + 0.8F, this.posZ + 0.2F, this.rotationYaw, 0.0F);
         entitycore.willDrop = true;
         MAMEntityUtil.spawnInWorld(this.world, entitycore);
         EntityCoreBlue entitycore2 = new EntityCoreBlue(this.world);
         entitycore2.setLocationAndAngles(this.posX + 0.2F, this.posY + 0.8F, this.posZ + 0.2F, this.rotationYaw, 0.0F);
         entitycore2.willDrop = true;
         MAMEntityUtil.spawnInWorld(this.world, entitycore2);
         EntityCoreBlue entitycore3 = new EntityCoreBlue(this.world);
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
