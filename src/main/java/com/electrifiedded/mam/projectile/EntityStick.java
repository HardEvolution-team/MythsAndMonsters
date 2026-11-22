package com.electrifiedded.mam.projectile;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityStick extends EntityThrowable {
   public boolean drop = true;

   public EntityStick(World par1World) {
      super(par1World);
   }

   public EntityStick(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
   }

   public EntityStick(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
   }

   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      if (par1MovingObjectPosition.entityHit != null) {
         double b0 = 1.5;
         par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
      } else if (!this.world.isRemote && this.drop) {
         this.dropItem(Items.STICK, 1);
      }

      for (int i = 0; i < 8; i++) {
         LegacyParticles.spawn(this.world, "crit", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
      }

      if (!this.world.isRemote) {
         this.setDead();
      }
   }
}
