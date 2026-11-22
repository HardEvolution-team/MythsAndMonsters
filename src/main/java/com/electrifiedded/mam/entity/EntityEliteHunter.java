package com.electrifiedded.mam.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityEliteHunter extends EntityHunter {
   public EntityEliteHunter(World world) {
      super(world);
      
      this.getNavigator().onUpdateNavigation();
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(3, new EntityAIAttackMelee(this, 0.43F, true));
      this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
      this.tasks.addTask(5, new EntityAIWander(this, 0.43F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityFairy.class, true));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGoldenRam.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySandWyrm.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPigZombie.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityEnderman.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityHillGiant.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityDendroidSentinel.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityDendroid.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityDendroidElder.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityKitsune.class, true));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
   }
}
