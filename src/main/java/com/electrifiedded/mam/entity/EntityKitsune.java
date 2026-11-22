package com.electrifiedded.mam.entity;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityKitsune extends EntityMob {
   private boolean spawnMinion = false;
   private boolean isInvinsible = false;
   public int timer = 1000;

   public EntityKitsune(World par1World) {
      super(par1World);
      this.setSize(0.6F, 0.7F);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityWolf.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   protected boolean isAIEnabled() {
      return true;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.getHealth() <= 10.0F && !this.spawnMinion) {
         EntityKitsuneMinion mob = new EntityKitsuneMinion(this.world);
         mob.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob);
         EntityKitsuneMinion mob2 = new EntityKitsuneMinion(this.world);
         mob2.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob2.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob2);
         EntityKitsuneMinion mob3 = new EntityKitsuneMinion(this.world);
         mob3.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob3.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob3);
         if (this.isBurning()) {
            mob.setFire(2);
            mob2.setFire(2);
            mob3.setFire(2);
         }

         this.spawnMinion = true;
      }

      if (this.spawnMinion) {
         this.timer--;
      }

      if (this.spawnMinion && this.timer > 0) {
         this.setInvisible(true);
         this.isInvinsible = true;
      } else if (this.spawnMinion && this.timer == 0 && !this.isPotionActive(MobEffects.INVISIBILITY)) {
         this.setInvisible(false);
         this.isInvinsible = false;
      }
   }

   public boolean attackEntityFrom(DamageSource source, float damage) {
      return this.isInvinsible ? false : super.attackEntityFrom(source, damage);
   }

   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.spawnMinion = compund.getBoolean("Spawn");
      this.timer = compund.getInteger("TimerKitsune");
   }

   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setBoolean("Spawn", this.spawnMinion);
      compund.setInteger("TimerKitsune", this.timer);
   }

   protected void dropFewItems(boolean par1, int par2) {
      this.dropItem(MAMItems.kitsunetail, this.rand.nextInt(4));
   }
}
