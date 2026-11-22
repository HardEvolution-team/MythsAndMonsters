package com.electrifiedded.mam.projectile;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDendroidChakram extends EntityThrowable {
   private EntityLiving thrower;
   public boolean dropping = true;
   double bounceFactor;
   private int bounced = 0;
   private int frodo = 20;
   private int ticksInExisted = 0;

   public EntityDendroidChakram(World par1World) {
      super(par1World);
   }

   public EntityDendroidChakram(World par1World, EntityLivingBase par2EntityLiving) {
      super(par1World, par2EntityLiving);
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.bounceFactor = 0.5;
   }

   public EntityDendroidChakram(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
   }

   protected float getGravityVelocity() {
      return 0.01F;
   }

   public void onUpdate() {
      super.onUpdate();
      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      Block id = LegacyWorld.getBlock(this.world, xx, yy, zz);
      if (id == Blocks.TALLGRASS || id == Blocks.WHEAT) {
         LegacyWorld.setBlock(this.world, xx, yy, zz, Blocks.AIR, 0, 2);
         if (this.rand.nextBoolean() && !this.world.isRemote) {
            this.entityDropItem(new ItemStack(Items.WHEAT_SEEDS, 1), 0.0F);
         }
      }

      this.ticksExisted++;
      if (this.ticksExisted == 30.0) {
         this.motionY = 1.35 * this.motionY;
         this.motionX = 1.35 * this.motionX;
         this.motionZ = 1.35 * this.motionZ;
      }

      if (this.ticksExisted == 60) {
         this.motionY = -1.4 * this.motionY;
         this.motionX = -1.4 * this.motionX;
         this.motionZ = -1.4 * this.motionZ;
      }

      if (this.ticksExisted > 180 && !this.world.isRemote) {
         this.setDead();
         if (this.dropping) {
            this.entityDropItem(new ItemStack(MAMItems.dendroidchakram, 1, 1), 0.0F);
         }
      }

      double prevVelX = this.motionX;
      double prevVelY = this.motionY;
      double prevVelZ = this.motionZ;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.move(net.minecraft.entity.MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      if (this.motionX != prevVelX) {
         this.motionX = -this.bounceFactor * prevVelX;
         this.bounced++;
      }

      if (this.motionY != prevVelY) {
         this.motionY = -this.bounceFactor * prevVelY;
         this.bounced++;
      } else {
         this.motionY -= 0.005;
      }

      if (this.motionZ != prevVelZ) {
         this.motionZ = -this.bounceFactor * prevVelZ;
         this.bounced++;
      }

      this.motionX *= 0.99;
      this.motionY *= 0.99;
      this.motionZ *= 0.99;
      if (this.bounced >= 4 && !this.world.isRemote) {
         this.setDead();
         if (this.dropping) {
            this.entityDropItem(new ItemStack(MAMItems.dendroidchakram, 1, 1), 0.0F);
         }

         LegacyParticles.spawn(this.world, "crit",
               this.posX + this.motionX / 4.0,
               this.posY + this.motionY / 4.0,
               this.posZ + this.motionZ / 4.0,
               -this.motionX,
               -this.motionY + 0.2,
               -this.motionZ
            );
      }
   }

   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      Block id = LegacyWorld.getBlock(this.world, xx, yy, zz);
      if (par1MovingObjectPosition.entityHit != null) {
         if (!par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), 5.0F)) {
         }

         for (int i = 0; i < 8; i++) {
            LegacyParticles.spawn(this.world, "crit", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
         }

         if (!this.world.isRemote) {
            int cat = this.rand.nextInt(9);
            double motionY1 = this.motionY;
            double motionX1 = this.motionX;
            double motionZ1 = this.motionZ;
            if (cat == 0) {
               this.motionY = motionX1;
               this.motionX = motionZ1;
               this.motionZ = motionY1;
            }

            if (cat == 1) {
               this.motionY = motionZ1;
               this.motionX = motionY1;
               this.motionZ = motionX1;
            }

            if (cat == 2) {
               this.motionX = motionZ1;
               this.motionZ = motionX1;
            }

            if (cat == 3) {
               this.motionY = motionX1;
               this.motionX = motionY1;
            }

            if (cat == 4) {
               this.motionY = motionZ1;
               this.motionZ = motionY1;
            }

            if (cat == 5) {
               this.motionY = -motionX1;
               this.motionX = -motionZ1;
               this.motionZ = -motionY1;
            }

            if (cat == 6) {
               this.motionY = -motionZ1;
               this.motionX = -motionY1;
               this.motionZ = -motionX1;
            }

            if (cat == 7) {
               this.motionX = -motionZ1;
               this.motionZ = -motionX1;
            }

            if (cat == 8) {
               this.motionY = -motionX1;
               this.motionX = -motionY1;
            }

            if (cat == 9) {
               this.motionY = -motionZ1;
               this.motionZ = -motionY1;
            }

            this.motionX = this.motionX * this.bounceFactor;
            this.motionY = this.motionY * this.bounceFactor;
            this.motionZ = this.motionZ * this.bounceFactor;
         }
      }

      if (id != Blocks.AIR
         && id != Blocks.WATER
         && id != Blocks.FLOWING_WATER
         && id != Blocks.LAVA
         && id != Blocks.FLOWING_LAVA
         && id != Blocks.LEAVES
         && id != Blocks.DEADBUSH
         && id != Blocks.TALLGRASS
         && id != Blocks.YELLOW_FLOWER
         && id != Blocks.RED_FLOWER
         && id != Blocks.BROWN_MUSHROOM
         && id != Blocks.RED_MUSHROOM
         && id != Blocks.SNOW_LAYER) {
         LegacyParticles.spawn(this.world, "crit",
               this.posX + this.motionX / 4.0,
               this.posY + this.motionY / 4.0,
               this.posZ + this.motionZ / 4.0,
               -this.motionX,
               -this.motionY + 0.2,
               -this.motionZ
            );
      }
   }
}
