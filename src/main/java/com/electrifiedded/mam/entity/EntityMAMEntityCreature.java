package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.entity.LegacyCombat;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityHelper;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMAMEntityCreature extends EntityMob implements IMob {
   protected Entity entityToAttack;
   protected int fleeingTick;
   protected boolean hasAttacked;

   public EntityMAMEntityCreature(World par1World) {
      super(par1World);
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
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

   public boolean attackEntityAsMob(Entity par1Entity) {
      int var2 = this.getAttackStrength(par1Entity);
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

   private int getAttackStrength(Entity par1Entity) {
      return 0;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.getAttackTarget() != null && this.getAttackTarget().isDead) {
         this.setAttackTarget(null);
      }
   }
}
