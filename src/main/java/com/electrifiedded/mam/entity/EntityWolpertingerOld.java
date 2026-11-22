package com.electrifiedded.mam.entity;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityWolpertingerOld extends EntityAnimal {
   protected Entity entityToAttack;
   protected int fleeingTick;
   protected boolean hasAttacked;

   private boolean flying = false;
   private int changetime;
   private double hoverY;
   private boolean rideable = true;
   public int courseChangeCooldown = 0;
   public double waypointX;
   public double waypointY;
   public double waypointZ;

   public EntityWolpertingerOld(World par1World) {
      super(par1World);
      this.setSize(0.45F, 0.63F);
      this.tasks.addTask(0, new net.minecraft.entity.ai.EntityAISwimming(this));
      this.tasks.addTask(1, new net.minecraft.entity.ai.EntityAIPanic(this, 1.25));
      this.tasks.addTask(5, new net.minecraft.entity.ai.EntityAIWander(this, 1.0));
      this.tasks.addTask(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.3F);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9F);
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "mam:Wolpertinger");
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (LegacyEntityHelper.getRider(this) != null) {
         double d = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = 10.0;
         LegacyParticles.spawn(this.world, "enchantmenttable",
               LegacyEntityHelper.getRider(this).posX
                  + this.rand.nextFloat() * LegacyEntityHelper.getRider(this).width * 2.0F
                  - LegacyEntityHelper.getRider(this).width
                  - d * d3,
               LegacyEntityHelper.getRider(this).posY + this.rand.nextFloat() * LegacyEntityHelper.getRider(this).height - d1 * d3,
               LegacyEntityHelper.getRider(this).posZ
                  + this.rand.nextFloat() * LegacyEntityHelper.getRider(this).width * 2.0F
                  - LegacyEntityHelper.getRider(this).width
                  - d2 * d3,
               d,
               d1,
               d2
            );
      }

      if (this.isInWater()) {
         this.motionY += 0.1F;
      }

      if (this.motionY <= -0.25) {
         this.motionY = -0.25;
      }

      this.changetime++;
      if (this.flying && !this.world.isRemote) {
         this.doFlyingStuff();
      }

      if (!this.world.isRemote && LegacyEntityHelper.getRider(this) != null && this.rand.nextInt(1000) == 0) {
         LegacyEntityHelper.getRider(this).dismountRidingEntity();
         this.rideable = false;
      }

      if (this.rand.nextInt(80) == 0 && this.changetime >= 250 && !this.world.isRemote) {
         this.changetime = 0;
         this.flying = !this.flying;
         if (this.flying) {
            this.hoverY = this.posY;
            this.rideable = true;
         }
      }
   }

   private void doFlyingStuff() {
      if (this.posY < this.hoverY + 1.0 + this.rand.nextFloat()) {
         this.motionY += 0.3F;
      }

      double var1 = this.waypointX - this.posX;
      double var3 = this.waypointY - this.posY;
      double var5 = this.waypointZ - this.posZ;
      double var7 = var1 * var1 + var3 * var3 + var5 * var5;
      if (var7 < 1.0 || var7 > 3600.0) {
         this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
         this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 0.5F) * 10.0F;
         this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
      }

      if (this.courseChangeCooldown-- <= 0) {
         this.courseChangeCooldown = this.courseChangeCooldown + this.rand.nextInt(5) + 50;
         var7 = MathHelper.sqrt(var7);
         if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
            this.motionX += var1 / var7 * 0.1;
            this.motionY += var3 / var7 * 0.1;
            this.motionZ += var5 / var7 * 0.1;
         } else {
            this.waypointX = this.posX;
            this.waypointY = this.posY;
            this.waypointZ = this.posZ;
         }
      }

      this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
   }

   public void applyEntityCollision(Entity par1Entity) {
      if (!this.world.isRemote
         && this.rideable
         && par1Entity != LegacyEntityHelper.getRider(this)
         && par1Entity.getRidingEntity() == null
         && LegacyEntityHelper.getRider(this) == null
         && this.recentlyHit < 10) {
         double size = Math.sqrt(par1Entity.width * par1Entity.height);
         if (!(par1Entity instanceof EntityIronGolem)
            && !(par1Entity instanceof EntitySandWyrm)
            && !(par1Entity instanceof EntityDendroidElder)
            && !(par1Entity instanceof EntityHillGiant)
            && !(par1Entity instanceof EntityDendroidSeer)
            && !(par1Entity instanceof EntityWolpertinger)) {
            par1Entity.startRiding(this);
         }
      }
   }

   protected void dropFewItems(boolean par1, int par2) {
      if (this.rand.nextBoolean()) {
         if (this.rand.nextBoolean()) {
            this.entityDropItem(new ItemStack(MAMItems.wolantler), 1.0F);
         } else {
            this.entityDropItem(new ItemStack(MAMItems.wolantler), 1.0F);
         }
      } else {
         this.dropItem(Items.LEATHER, 1);
      }
   }

   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
      double var9 = (this.waypointX - this.posX) / par7;
      double var11 = (this.waypointY - this.posY) / par7;
      double var13 = (this.waypointZ - this.posZ) / par7;
      AxisAlignedBB var15 = this.getEntityBoundingBox();

      for (int var16 = 1; var16 < par7; var16++) {
         var15.offset(var9, var11, var13);
         if (!this.world.getCollisionBoxes(this, var15).isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      if (this.isEntityInvulnerable(par1DamageSource)) {
         return false;
      } else {
         if (LegacyEntityHelper.getRider(this) != null && !this.world.isRemote) {
            LegacyEntityHelper.getRider(this).dismountRidingEntity();
         }

         this.fleeingTick = 120;
         this.entityToAttack = null;
         return super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   public float getBlockPathWeight(net.minecraft.util.math.BlockPos bpw) {
      int par1 = bpw.getX(), par2 = bpw.getY(), par3 = bpw.getZ();
      return LegacyWorld.getBlock(this.world, par1, par2, par3) == MAMBlocks.fairymushroom
         ? 30.0F
         : (
            LegacyWorld.getBlock(this.world, par1, par2 - 1, par3) == Blocks.LEAVES
               ? 20.0F
               : (
                  LegacyWorld.getBlock(this.world, par1, par2 - 1, par3) == Blocks.LOG
                     ? 20.0F
                     : (
                        LegacyWorld.getBlock(this.world, par1, par2 - 1, par3) == Blocks.GRASS
                           ? 10.0F
                           : this.world.getLightBrightness(new BlockPos(par1, par2, par3)) - 0.5F
                     )
               )
         );
   }

   public void fall(float f, float damageMultiplier) {
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setBoolean("FlyState", this.flying);
      par1NBTTagCompound.setBoolean("Ride", this.rideable);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.flying = par1NBTTagCompound.getBoolean("FlyState");
      this.rideable = par1NBTTagCompound.getBoolean("Ride");
   }

   public EntityAgeable createChild(EntityAgeable entityageable) {
      return null;
   }
}
