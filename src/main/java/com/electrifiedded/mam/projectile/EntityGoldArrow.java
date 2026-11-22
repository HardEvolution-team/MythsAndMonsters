package com.electrifiedded.mam.projectile;

import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Golden arrow: the 1.7 class was a copy of the vanilla arrow with a fullbright render, its own damage type and
 * "touching it clears potion effects". The vanilla 1.12 arrow physics are reused.
 */
public class EntityGoldArrow extends EntityArrow {
   public EntityGoldArrow(World world) {
      super(world);
   }

   public EntityGoldArrow(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   public EntityGoldArrow(World world, EntityLivingBase shooter, float velocity) {
      super(world, shooter);
      this.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, velocity * 1.5F, 1.0F);
   }

   @Override
   protected ItemStack getArrowStack() {
      return new ItemStack(MAMItems.goldarrow);
   }

   @Override
   protected void arrowHit(EntityLivingBase living) {
      super.arrowHit(living);
   }

   @Override
   public void applyEntityCollision(Entity entity) {
      if (entity instanceof EntityLiving) {
         ((EntityLiving)entity).clearActivePotions();
      }
      super.applyEntityCollision(entity);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getBrightnessForRender() {
      return 15728880;
   }

   public static DamageSource causeGoldArrowDamage(EntityGoldArrow arrow, Entity entity) {
      return new EntityDamageSourceIndirect("GoldenArrow", arrow, entity).setProjectile();
   }
}
