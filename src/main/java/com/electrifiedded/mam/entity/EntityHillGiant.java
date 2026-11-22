package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.entity.LegacyCombat;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityHillGiant extends EntityMAMEntityCreature {
   public int smashCounter = 0;
   private boolean smash = false;

   public EntityHillGiant(World par1World) {
      super(par1World);
      this.setSize(1.1F, 3.9F);
      this.ignoreFrustumCheck = true;
      
      this.getNavigator().onUpdateNavigation();
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(3, new EntityAIAttackMelee(this, 0.3F, true));
      this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
      this.tasks.addTask(5, new EntityAIWander(this, 0.3F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, false));
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.smashCounter++;
      if (this.smashCounter >= 100 && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) <= 4.5F && this.onGround) {
         this.motionY++;
         this.updateArmSwingProgress();
         this.smash = true;
      }

      if (this.smash && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) <= 4.5F && this.onGround) {
         this.world.createExplosion(this, this.posX, this.posY + 1.0, this.posZ, 2.0F, true);
         this.smashCounter = 0;
         this.smash = false;
      }
   }

   public void fall(float par1, float damageMultiplier) {
      par1 /= 3.0F;
      super.fall(par1, damageMultiplier);
   }

   public boolean isAIEnabled() {
      return true;
   }

   @Override
   public boolean attackEntityAsMob(Entity par1Entity) {
      int var2 = 12;
      if (this.isPotionActive(MobEffects.STRENGTH)) {
         var2 += 3 << this.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
      }

      if (this.isPotionActive(MobEffects.WEAKNESS)) {
         var2 -= 2 << this.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier();
      }

      int var3 = 0;
      if (par1Entity instanceof EntityLiving) {
         var2 = (int)(var2 + LegacyCombat.getEnchantmentModifierLiving(this, par1Entity));
         var3 += EnchantmentHelper.getKnockbackModifier(this);
      }

      boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
      if (var4) {
         if (var3 > 0) {
            par1Entity.addVelocity(
               -MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * var3 * 0.5F,
               0.1,
               MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * var3 * 0.5F
            );
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }

         int var5 = EnchantmentHelper.getFireAspectModifier(this);
         if (var5 > 0) {
            par1Entity.setFire(var5 * 4);
         }
      }

      return var4;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(3.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
   }
}
