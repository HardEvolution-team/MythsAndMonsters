package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;

import com.electrifiedded.mam.registry.MAMItems;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCoreRed extends EntityLiving implements IAnimals {
   public boolean willDrop = false;
   int tickCheckInt = 0;
   boolean tickCheck = false;

   public EntityCoreRed(World par1World) {
      super(par1World);
      this.setSize(0.35F, 0.35F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(3.0);
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      Entity entity = par1DamageSource.getTrueSource();
      return (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).canHarvestBlock(Blocks.OBSIDIAN.getDefaultState()))
            && par1DamageSource != DamageSource.IN_WALL
         ? false
         : super.attackEntityFrom(par1DamageSource, par2);
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public void fall(float f, float damageMultiplier) {
   }

   protected void dropFewItems(boolean par1, int par2) {
      if (this.willDrop) {
         this.entityDropItem(new ItemStack(MAMItems.redcore), 1.0F);
      } else {
         this.entityDropItem(new ItemStack(MAMItems.redstardust), 1.0F);
      }
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.tickCheckInt++;
      if (this.tickCheckInt >= 20) {
         this.tickCheck = true;
      }

      if (this.onGround) {
         this.motionY++;
      }

      if (this.motionY < 0.0) {
         this.motionY = 0.0;
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(2.0, 2.0, 2.0));

      for (int i = 0; i < list.size(); i++) {
         Entity entity1 = (Entity)list.get(i);
         if (entity1 instanceof EntityLiving
            && !(entity1 instanceof EntityEnderman)
            && !(entity1 instanceof EntityUrsaBlack)
            && !(entity1 instanceof EntityCoreRed)) {
            if (entity1 instanceof EntityPlayer) {
               ((EntityPlayer)entity1).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 6));
            }

            if (this.rand.nextInt(12) == 0) {
               entity1.attackEntityFrom(DamageSource.IN_WALL, 1.0F);
               LegacyParticles.spawn(this.world, "portal",
                     entity1.posX + (this.rand.nextDouble() - 0.5) * entity1.width,
                     entity1.posY + this.rand.nextDouble() * entity1.height - 0.25,
                     entity1.posZ + (this.rand.nextDouble() - 0.5) * entity1.width,
                     (this.rand.nextDouble() - 0.5) * 2.0,
                     -this.rand.nextDouble(),
                     (this.rand.nextDouble() - 0.5) * 2.0
                  );
               LegacyParticles.spawn(this.world, "portal",
                     entity1.posX + (this.rand.nextDouble() - 0.5) * entity1.width,
                     entity1.posY + this.rand.nextDouble() * entity1.height - 0.25,
                     entity1.posZ + (this.rand.nextDouble() - 0.5) * entity1.width,
                     (this.rand.nextDouble() - 0.5) * 2.0,
                     -this.rand.nextDouble(),
                     (this.rand.nextDouble() - 0.5) * 2.0
                  );
               LegacyParticles.spawn(this.world, "portal",
                     entity1.posX + (this.rand.nextDouble() - 0.5) * entity1.width,
                     entity1.posY + this.rand.nextDouble() * entity1.height - 0.25,
                     entity1.posZ + (this.rand.nextDouble() - 0.5) * entity1.width,
                     (this.rand.nextDouble() - 0.5) * 2.0,
                     -this.rand.nextDouble(),
                     (this.rand.nextDouble() - 0.5) * 2.0
                  );
            }

            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
            LegacyParticles.spawn(this.world, "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
         }
      }

      if (this.world.isDaytime() && !this.world.isRemote || this.isBurning()) {
         this.setDead();
         boolean var2 = this.world.getGameRules().getBoolean("mobGriefing");
         this.world.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 3.2F, var2);
      }
   }

   protected SoundEvent getAmbientSound() {
      return null;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "random.break");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "random.fizz");
   }

   protected float getSoundVolume() {
      return 2.5F;
   }

   protected float getSoundPitch() {
      return 8.0F - this.getHealth();
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setBoolean("drop", this.willDrop);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.willDrop = par1NBTTagCompound.getBoolean("drop");
   }
}
