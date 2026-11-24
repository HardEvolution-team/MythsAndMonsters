package com.electrifiedded.mam.entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.projectile.EntityStick;
import com.electrifiedded.mam.registry.MAMItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDendroid extends EntityMob implements IRangedAttackMob {
   private boolean setfire = false;

   public EntityDendroid(World par1World) {
      super(par1World);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25, 20, 10.0F));
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

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
   }

   protected boolean isAIEnabled() {
      return true;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.isBurning() && !this.setfire) {
         this.setFire(100000000);
         this.setfire = true;
      }
   }

   protected void playStepSound(BlockPos stepPos, Block blockIn) {
      this.playSound(LegacyModSounds.get("mam", "dig.wood"), 0.15F, 1.0F);
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "mob.zombie.wood");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "mob.zombie.woodbreak");
   }

   protected void dropFewItems(boolean par1, int par2) {
      Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
      int randomNumber = rand.nextInt(4);
      if (randomNumber == 1) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(MAMItems.dendroidroot, 1);
            this.dropItem(Items.STICK, 4);
         } else {
            this.dropItem(Items.COAL, 4);
         }
      } else if (randomNumber == 2) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(MAMItems.dendroidroot, 2);
            this.dropItem(Items.STICK, 9);
         } else {
            this.dropItem(Items.COAL, 6);
         }
      } else if (randomNumber == 3) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(Items.STICK, 11);
         } else {
            this.dropItem(Items.COAL, 7);
         }
      } else if (randomNumber == 4) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(new ItemStack(Blocks.LOG).getItem(), 2);
         } else {
            this.dropItem(Items.COAL, 8);
         }
      }
   }

   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
      EntityStick entitysnowball = new EntityStick(this.world, this);
      double d0 = par1EntityLivingBase.posX - this.posX;
      double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.1F - entitysnowball.posY;
      double d2 = par1EntityLivingBase.posZ - this.posZ;
      float f1 = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
      entitysnowball.shoot(d0, d1 + f1, d2, 1.6F, 12.0F);
      this.playSound(LegacyModSounds.get("mam", "random.bow"), 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      MAMEntityUtil.spawnInWorld(this.world, entitysnowball);
   }

   protected void dropRareDrop(int p_70600_1_) {
      this.entityDropItem(new ItemStack(MAMItems.dendroideye), 1.0F);
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }
}
