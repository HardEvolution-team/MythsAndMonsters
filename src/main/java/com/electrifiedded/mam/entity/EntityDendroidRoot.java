package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDendroidRoot extends EntityMob {
   private int flailCounter = 0;
   private boolean setfire = false;
   private boolean hasHome = false;
   private boolean hasSapling = false;
   private int[] AnchoredBlock = new int[]{0, 0, 0};
   private int[] DendroidSapling = new int[]{0, 0, 0};
   private int lifespan = -1;
   private EntityDendroidSentinel daddy = null;
   private boolean dying = false;
   public boolean grows = false;

   public EntityDendroidRoot(World par1World) {
      super(par1World);
      this.setSize(0.5F, 0.9F);
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityFairy.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityJackalopes.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntitySkeleton.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityZombie.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPigZombie.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityEnderman.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntitySilverfish.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityHillGiant.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityGoldenRam.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFairy.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityJackalopes.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPigZombie.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEnderman.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHillGiant.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGoldenRam.class, false));
   }

   public EntityDendroidRoot(World par1World, EntityDendroidSentinel daddy) {
      this(par1World);
      this.lifespan = 500;
      this.daddy = daddy;
   }

   public EntityDendroidRoot(World par1World, int x, int y, int z) {
      this(par1World);
      this.AnchoredBlock[0] = x;
      this.AnchoredBlock[1] = y;
      this.AnchoredBlock[2] = z;
      this.hasHome = true;
   }

   public EntityDendroidRoot(World par1World, int x, int y, int z, int sx, int sy, int sz) {
      this(par1World, x, y, z);
      this.hasSapling = true;
      this.DendroidSapling[0] = sx;
      this.DendroidSapling[1] = sy;
      this.DendroidSapling[2] = sz;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(13.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   protected boolean isAIEnabled() {
      return true;
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setIntArray("RootBeer", this.AnchoredBlock);
      par1NBTTagCompound.setIntArray("SaplingCoords", this.DendroidSapling);
      par1NBTTagCompound.setBoolean("Home", this.hasHome);
      par1NBTTagCompound.setBoolean("Sapling", this.hasSapling);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.AnchoredBlock = par1NBTTagCompound.getIntArray("RootBeer");
      this.DendroidSapling = par1NBTTagCompound.getIntArray("SaplingCoords");
      this.hasHome = par1NBTTagCompound.getBoolean("Home");
      this.hasSapling = par1NBTTagCompound.getBoolean("Sapling");
   }

   public int getFlailCounter() {
      return this.flailCounter;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.lifespan--;
      if (this.isBurning() && !this.setfire) {
         this.setFire(100000000);
         this.setfire = true;
      }

      if (this.getAttackTarget() != null) {
         this.flailCounter = 64;
      } else {
         this.flailCounter = 0;
      }

      if (this.daddy != null) {
         LegacyParticles.spawn(this.world, "blockcrack_17_0",
               this.posX - 0.5 + this.rand.nextFloat(),
               this.posY + this.rand.nextFloat(),
               this.posZ - 0.5 + this.rand.nextFloat(),
               10.0F * (-0.5F + this.rand.nextFloat()),
               10.0F * this.rand.nextFloat(),
               10.0F * (-0.5F + this.rand.nextFloat())
            );
         this.lifespan--;
         if (this.daddy.isDead) {
            this.dying = true;
         }

         if (!this.dying && this.lifespan == 0) {
            this.setDead();
         }
      }

      if (this.hasSapling
         && LegacyWorld.getBlock(this.world, this.DendroidSapling[0], this.DendroidSapling[1], this.DendroidSapling[2]) == MAMBlocks.dendroidsapling) {
         if (this.isBurning() && this.rand.nextInt(500) == 0) {
            LegacyWorld.setBlock(this.world, this.DendroidSapling[0], this.DendroidSapling[1], this.DendroidSapling[2], Blocks.FIRE);
         }

         if (this.grows && this.rand.nextInt(1000) == 0) {
            this.setDead();
            EntityDendroid dendy = new EntityDendroid(this.world);
            if (this.isBurning()) {
               dendy.setFire(2);
            }

            dendy.setPosition(this.posX, this.posY, this.posZ);
            MAMEntityUtil.spawnInWorld(this.world, dendy);
         }
      } else if (this.hasSapling) {
         this.hasSapling = false;
         this.dying = true;
      }

      if (this.dying && this.rand.nextInt(10) != 0) {
         this.attackEntityFrom(DamageSource.WITHER, 1.0F);
      }

      if (this.flailCounter > 0) {
         this.flailCounter--;
      }

      if (this.hasHome
         && LegacyWorld.getBlock(this.world, this.AnchoredBlock[0], this.AnchoredBlock[1], this.AnchoredBlock[2]) == Blocks.AIR
         && !this.world.isRemote) {
         this.setDead();
         EntityDendroid dendy = new EntityDendroid(this.world);
         if (this.isBurning()) {
            dendy.setFire(2);
         }

         dendy.setPosition(this.posX, this.posY, this.posZ);
         MAMEntityUtil.spawnInWorld(this.world, dendy);
      }
   }

   public boolean canBePushed() {
      return false;
   }

   public void collideWithEntity(Entity e) {
      if (this.isBurning()) {
         e.setFire(4);
      }
   }

   protected void playStepSound(BlockPos stepPos, Block blockIn) {
      this.playSound(LegacyModSounds.get("mam", "step.wood"), 0.15F, 1.0F);
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "mob.zombie.wood");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "mob.zombie.woodbreak");
   }
}
