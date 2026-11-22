package com.electrifiedded.mam.projectile;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.entity.LegacyDataWatcher;

import com.electrifiedded.mam.registry.MAMPotion;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMusic extends EntityFireball {
   public EntityMusic(World par1World) {
      super(par1World);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityMusic(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
      super(par1World, par2EntityLivingBase, par3, par5, par7);
      this.setSize(0.3125F, 0.3125F);
   }

   protected float getMotionFactor() {
      return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
   }

   @SideOnly(Side.CLIENT)
   public EntityMusic(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      super(par1World, par2, par4, par6, par8, par10, par12);
      this.setSize(0.3125F, 0.3125F);
   }

   public boolean isBurning() {
      return false;
   }

   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      if (!this.world.isRemote) {
         if (par1MovingObjectPosition.entityHit != null) {
            if (this.shootingEntity != null) {
               if (par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)
                  && !par1MovingObjectPosition.entityHit.isEntityAlive()) {
                  this.shootingEntity.heal(5.0F);
               }
            } else {
               par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.MAGIC, 5.0F);
            }

            if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
               byte b0 = 5;
               if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                  b0 = 5;
               } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                  b0 = 5;
               }

               if (b0 > 0) {
                  ((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MAMPotion.sleepingPotion, 20 * b0, 1));
               }
            }
         }

         this.setDead();
      }
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      return false;
   }

   protected void entityInit() {
      LegacyDataWatcher.of(this).addObject(10, (byte)0);
   }

   public boolean isInvulnerable() {
      return LegacyDataWatcher.of(this).getWatchableObjectByte(10) == 1;
   }

   public void setInvulnerable(boolean par1) {
      LegacyDataWatcher.of(this).updateObject(10, (byte)(par1 ? 1 : 0));
   }

   public void onUpdate() {
      float f3 = 0.25F;
      if (this.isInWater()) {
         for (int k = 0; k < 4; k++) {
            LegacyParticles.spawn(this.world, "bubble",
                  this.posX - this.motionX * 0.25,
                  this.posY - this.motionY * 0.25,
                  this.posZ - this.motionZ * 0.25,
                  this.motionX,
                  this.motionY,
                  this.motionZ
               );
         }
      }

      LegacyParticles.spawn(this.world, "note",
            this.posX - this.motionX * 0.25,
            this.posY - this.motionY * 0.25,
            this.posZ - this.motionZ * 0.25,
            this.motionX,
            this.motionY,
            this.motionZ
         );
      this.setPosition(this.posX, this.posY, this.posZ);
      super.onUpdate();
   }
}
