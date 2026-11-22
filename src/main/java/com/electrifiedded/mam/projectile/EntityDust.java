package com.electrifiedded.mam.projectile;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDust extends EntityThrowable implements IThrowableEntity {
   private EntityLiving thrower;
   int mountedTicks = 0;
   int trail = 1;

   public EntityDust(World par1World) {
      super(par1World);
      this.setSize(0.35F, 0.35F);
   }

   public EntityDust(World par1World, EntityLivingBase par2EntityLiving) {
      super(par1World, par2EntityLiving);
   }

   public EntityDust(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
   }

   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      Block id = LegacyWorld.getBlock(this.world, xx, yy, zz);
      Entity ToHit = par1MovingObjectPosition.entityHit;
      if (ToHit != null && ToHit instanceof EntityLivingBase) {
         this.startRiding(ToHit);
         ((EntityLivingBase)ToHit).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 1));
         ((EntityLivingBase)ToHit).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 1));
         if (((EntityLivingBase)ToHit).attackEntityFrom(DamageSource.causeIndirectMagicDamage(ToHit, this.thrower), 3.0F)) {
            this.setDead();
         }
      }
   }

   public void onUpdate() {
      super.onUpdate();
      this.createTrail();
      this.dustStuff();
      this.motionStuff();
   }

   protected void motionStuff() {
      this.motionX *= 0.9;
      this.motionY *= 0.9;
      this.motionZ *= 0.9;
      this.motionX = this.motionX + (this.rand.nextFloat() - 0.5F) / 10.0F;
      this.motionY = this.motionY + (this.rand.nextFloat() - 0.5F) / 20.0F;
      this.motionZ = this.motionZ + (this.rand.nextFloat() - 0.5F) / 10.0F;
      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      Block id = LegacyWorld.getBlock(this.world, xx, yy, zz);
      Block under = LegacyWorld.getBlock(this.world, xx, yy - 1, zz);
      if (under != Blocks.AIR
         && under != Blocks.FLOWING_WATER
         && under != Blocks.WATER
         && under != Blocks.FLOWING_LAVA
         && under != Blocks.LAVA
         && under != Blocks.LEAVES
         && under != Blocks.TALLGRASS
         && under != Blocks.DEADBUSH
         && under != Blocks.YELLOW_FLOWER
         && under != Blocks.RED_FLOWER
         && under != Blocks.BROWN_MUSHROOM
         && under != Blocks.RED_MUSHROOM
         && under != Blocks.SNOW_LAYER) {
         this.posY++;
      }
   }

   protected void dustStuff() {
      if (this.ticksExisted >= 100) {
         this.setDead();
      }

      if (this.isRiding()) {
         this.mountedTicks++;
         if (this.mountedTicks >= 100) {
            this.setDead();
         }
      }
   }

   protected float getGravityVelocity() {
      return 0.0F;
   }

   protected void createTrail() {
      this.trail++;
      if (this.trail > 1) {
         LegacyParticles.spawn(this.world, "reddust",
               this.posX + this.motionX / 4.0,
               this.posY + this.motionY / 4.0,
               this.posZ + this.motionZ / 4.0,
               this.getColour(),
               this.getColour(),
               this.getColour()
            );
         LegacyParticles.spawn(this.world, "reddust",
               this.posX + this.motionX / 4.0,
               this.posY + this.motionY / 4.0,
               this.posZ + this.motionZ / 4.0,
               this.getColour(),
               this.getColour(),
               this.getColour()
            );
         this.trail = 0;
      }
   }

   protected double getColour() {
      return 0.0;
   }

   public EntityLiving getThrower() {
      return this.thrower;
   }

   public void setThrower(Entity entity) {
      this.thrower = (EntityLiving)entity;
   }
}
