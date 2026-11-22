package com.electrifiedded.mam.entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityFairyJar extends EntityLiving implements IAnimals {
   private boolean wagga = true;

   public EntityFairyJar(World par1World) {
      super(par1World);
      this.setSize(0.6F, 0.82F);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      Block on = LegacyWorld.getBlock(this.world, xx, yy, zz);
      if (this.ticksExisted > 3000 && (on != Blocks.AIR && on.getDefaultState().getMaterial().isReplaceable() || on == Blocks.AIR)) {
         this.attackEntityFrom(DamageSource.FALL, 10.0F);
      }
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0);
   }

   public void fall(float par1, float damageMultiplier) {
      float[] fallRes = ForgeHooks.onLivingFall(this, par1, damageMultiplier);
      if (fallRes == null) return;
      par1 = fallRes[0];
      if (!(par1 <= 0.0F)) {
         super.fall(par1, damageMultiplier);
         int var2 = MathHelper.ceil(par1 - 3.0F);
         if (var2 > 0) {
            if (var2 > 4) {
               this.playSound(LegacyModSounds.get("mam", "damage.fallbig"), 1.0F, 1.0F);
            } else {
               this.playSound(LegacyModSounds.get("mam", "damage.fallsmall"), 1.0F, 1.0F);
            }

            this.attackEntityFrom(DamageSource.FALL, var2);
         } else {
            int xx = (int)Math.floor(this.posX);
            int yy = (int)Math.floor(this.posY);
            int zz = (int)Math.floor(this.posZ);
            Block on = LegacyWorld.getBlock(this.world, xx, yy, zz);
            Block above = LegacyWorld.getBlock(this.world, xx, yy + 1, zz);
            if (!this.world.isRemote && this.getHealth() > 0.0F) {
               if ((on == Blocks.AIR || !on.getDefaultState().getMaterial().isReplaceable()) && on != Blocks.AIR) {
                  if ((above == Blocks.AIR || !above.getDefaultState().getMaterial().isReplaceable()) && above != Blocks.AIR) {
                     this.attackEntityFrom(DamageSource.FALL, 10.0F);
                  } else {
                     LegacyWorld.setBlock(this.world, xx, yy + 1, zz, MAMBlocks.angryfairyjar);
                     this.wagga = false;
                     this.setDead();
                  }
               } else {
                  LegacyWorld.setBlock(this.world, xx, yy, zz, MAMBlocks.angryfairyjar, 0, 2);
                  this.wagga = false;
                  this.setDead();
               }
            }
         }
      }
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "random.break");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "random.break");
   }

   public int getBrightnessForRender() {
      if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == MAMBlocks.fairyjar) {
         int var2 = MathHelper.floor(this.posX);
         int var3 = MathHelper.floor(this.posZ);
         if (LegacyWorld.blockExists(this.world, var2, 0, var3)) {
            double var4 = (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * 0.66;
            int var6 = MathHelper.floor(this.posY + var4);
            return this.world.getCombinedLight(new BlockPos(var2, var6, var3), 0);
         } else {
            return 0;
         }
      } else {
         return 15728880;
      }
   }

   protected float getSoundVolume() {
      return 2.5F;
   }

   public void setDead() {
      this.isDead = true;
      if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == MAMBlocks.angryfairyjar
         || LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == MAMBlocks.fairyjar
            && !this.world.isRemote
            && this.wagga) {
         EntityFairy f = new EntityFairy(this.world);
         f.setPosition(this.posX, this.posY, this.posZ);
         this.world.spawnEntity(f);
         if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == MAMBlocks.angryfairyjar) {
            f.bottleRelease();
         }
      }
   }
}
