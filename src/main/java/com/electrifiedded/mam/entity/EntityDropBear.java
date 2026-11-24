package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.entity.ai.ILegacyCreature;
import com.electrifiedded.archaiclib.legacy.entity.ai.EntityAILegacyCreature;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDropBear extends EntityMob implements ILegacyCreature {
   public int attackTime;

   @Override
   public void onEntityUpdate() {
      super.onEntityUpdate();
      if (this.attackTime > 0) {
         this.attackTime--;
      }
   }

   private Entity entityToAttack = null;
   private int attackStrength = 4;

   public EntityDropBear(World par1World) {
      super(par1World);
      this.setSize(0.9F, 0.5F);
      this.tasks.addTask(0, new net.minecraft.entity.ai.EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAILegacyCreature(this, 1.0));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      int totalDamage = this.attackStrength;
      this.world.setEntityState(this, (byte)4);
      if (par1Entity instanceof EntityPlayer) {
         this.startRiding(par1Entity);
         EntityPlayer steve = (EntityPlayer)par1Entity;
         if (!steve.inventory.armorInventory.get(3).isEmpty()) {
            totalDamage--;
         }

         if (steve.getHealth() - totalDamage < 1.0F && this.getRidingEntity() == steve) {
            this.dismountRidingEntity();
         }
      }

      return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), totalDamage);
   }

   public void fall(float distance, float damageMultiplier) {
   }

   public Entity findPlayerToAttack() {
      EntityPlayer entityplayer = this.world.getNearestAttackablePlayer(this, 24.0, 24.0);
      this.entityToAttack = entityplayer;
      return entityplayer;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.isRiding()) {
         this.ignoreFrustumCheck = true;
      }

      if (this.entityToAttack == null) {
         List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(24.0, 24.0, 24.0));

         for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity)targetList.get(i);
            if (entitytargetcheck instanceof EntityVillager || entitytargetcheck instanceof EntityHunter) {
               this.entityToAttack = entitytargetcheck;
            }
         }
      }

      if (!this.isInWater()) {
         Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
         int randomNumber = rand.nextInt(20);
         int randomYNumber = rand.nextInt(2);
         int randomPosX = (int)this.posX + randomNumber;
         int randomPosY = (int)this.posY + randomYNumber;
         int randomPosZ = (int)this.posZ + randomNumber;
         if (LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.WATER
            || LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.FLOWING_WATER) {
            this.getNavigator().tryMoveToXYZ(randomPosX + 0.5, randomPosY + 0.5, randomPosZ + 0.5, 0.2);
         }
      }
   }

   public void attackEntity(Entity par1Entity, float par2) {
      if (this.attackTime <= 0
         && par2 < 2.0F
         && par1Entity.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY
         && par1Entity.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
         this.attackTime = 65;
         this.attackEntityAsMob(par1Entity);
      }
   }

   @Override
   public Entity getEntityToAttack() {
      return this.entityToAttack;
   }

   @Override
   public void setEntityToAttack(Entity target) {
      this.entityToAttack = target;
   }
}
