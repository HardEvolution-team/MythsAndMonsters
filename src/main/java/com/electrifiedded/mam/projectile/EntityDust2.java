package com.electrifiedded.mam.projectile;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDust2 extends EntityDust implements IThrowableEntity {
   public EntityDust2(World par1World) {
      super(par1World);
   }

   public EntityDust2(World par1World, EntityLivingBase par2EntityLiving) {
      super(par1World, par2EntityLiving);
   }

   public EntityDust2(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
   }

   @Override
   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      Block id = LegacyWorld.getBlock(this.world, xx, yy, zz);
      Entity ToHit = par1MovingObjectPosition.entityHit;
      if (ToHit != null && ToHit instanceof EntityLivingBase) {
         this.startRiding(ToHit);
         ((EntityLivingBase)ToHit).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 1));
         ((EntityLivingBase)ToHit).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 100, 1));
         ((EntityLivingBase)ToHit).heal(3.0F);
      }
   }

   @Override
   protected double getColour() {
      return 1.0;
   }
}
