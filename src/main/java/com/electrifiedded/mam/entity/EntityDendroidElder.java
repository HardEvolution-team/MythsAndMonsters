package com.electrifiedded.mam.entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.entity.LegacyDataWatcher;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDendroidElder extends EntityMob {
   private int attackTimer;
   private boolean setfire = false;
   private boolean golden = false;
   private boolean lowhp = false;
   int graveltickCount = 50;
   int logtickCount = 50;

   public EntityDendroidElder(World par1World) {
      super(par1World);
      this.setSize(1.0F, 2.5F);
      this.tasks.addTask(0, new EntityAISwimming(this));
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

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(130.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
   }

   protected boolean isAIEnabled() {
      return true;
   }

   protected void entityInit() {
      super.entityInit();
      LegacyDataWatcher.of(this).addObject(16, (byte)0);
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      this.attackTimer = 10;
      this.world.setEntityState(this, (byte)4);
      boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
      if (flag) {
         par1Entity.motionY += 0.4F;
      }

      this.playSound(LegacyModSounds.get("mam", "mob.irongolem.throw"), 1.0F, 1.0F);
      return flag;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.graveltickCount--;
      this.logtickCount--;
      if (this.attackTimer > 0) {
         this.attackTimer--;
      }

      if (this.isBurning() && !this.setfire) {
         this.setFire(100000000);
         this.setfire = true;
      }

      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.5000003E-7F && this.rand.nextInt(5) == 0) {
         int i = MathHelper.floor(this.posX);
         int j = MathHelper.floor(this.posY - 0.2F);
         int k = MathHelper.floor(this.posZ);
         Block block = LegacyWorld.getBlock(this.world, i, j, k);
         if (block.getDefaultState().getMaterial() != Material.AIR) {
            LegacyParticles.spawn(this.world, "blockcrack_" + Block.getIdFromBlock(block) + "_" + LegacyWorld.getBlockMetadata(this.world, i, j, k),
                  this.posX + (this.rand.nextFloat() - 0.5) * this.width,
                  this.getEntityBoundingBox().minY + 0.1,
                  this.posZ + (this.rand.nextFloat() - 0.5) * this.width,
                  4.0 * (this.rand.nextFloat() - 0.5),
                  0.5,
                  (this.rand.nextFloat() - 0.5) * 4.0
               );
         }
      }

      if (this.graveltickCount <= 0) {
         int randomPosX = (int)this.posX + this.rand.nextInt(5);
         int randomPosY = (int)this.posY + this.rand.nextInt(5);
         int randomPosZ = (int)this.posZ + this.rand.nextInt(5);
         if (LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.LEAVES
            || LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.LEAVES2) {
            LegacyParticles.spawn(this.world, "spell", randomPosX, randomPosY + 1.4F, randomPosZ, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX + 0.4, randomPosY + 1.4F, randomPosZ + 0.4, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX - 0.4, randomPosY + 1.4F, randomPosZ - 0.4, 0.0, 0.0, 0.0);
            LegacyWorld.setBlock(this.world, randomPosX, randomPosY, randomPosZ, MAMBlocks.gravel);
            this.graveltickCount = 50;
         }
      }

      if (this.getHealth() <= 50.0F && !this.lowhp) {
         EntityDendroid mob = new EntityDendroid(this.world);
         mob.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob);
         EntityDendroid mob2 = new EntityDendroid(this.world);
         mob2.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
         mob2.setAttackTarget(this.getAttackTarget());
         MAMEntityUtil.spawnInWorld(this.world, mob2);
         if (this.isBurning()) {
            mob.setFire(2);
            mob2.setFire(2);
         }

         this.lowhp = true;
      }

      if (this.world.getGameRules().getBoolean("mobGriefing") && this.logtickCount <= 0 && this.getAttackTarget() != null) {
         int i = MathHelper.floor(this.posY);
         int i1 = MathHelper.floor(this.posX);
         int j1 = MathHelper.floor(this.posZ);
         boolean flag = false;

         for (int l1 = -1; l1 <= 1; l1++) {
            for (int i2 = -1; i2 <= 1; i2++) {
               for (int j = 0; j <= 3; j++) {
                  int j2 = i1 + l1;
                  int k = i + j;
                  int l = j1 + i2;
                  Block block = LegacyWorld.getBlock(this.world, j2, k, l);
                  if (!LegacyWorld.isAir(block) && block == Blocks.LOG || block == Blocks.LOG2) {
                     flag = this.world.destroyBlock(new BlockPos(j2, k, l), true) || flag;
                  }
               }
            }
         }

         if (flag) {
            this.world.playEvent((EntityPlayer)null, 1012, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
         }

         this.logtickCount = 50;
      }
   }

   public void onDeath(DamageSource par1DamageSource) {
      super.onDeath(par1DamageSource);
      EntityDendroid mob = new EntityDendroid(this.world);
      mob.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
      MAMEntityUtil.spawnInWorld(this.world, mob);
      EntityDendroid mob2 = new EntityDendroid(this.world);
      mob2.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
      MAMEntityUtil.spawnInWorld(this.world, mob2);
      if (this.isBurning()) {
         mob.setFire(2);
         mob2.setFire(2);
      }

      if (!this.world.isRemote && par1DamageSource.getTrueSource() instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.getTrueSource();
      }

      this.setDead();
   }

   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setBoolean("HpLow", this.lowhp);
   }

   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.lowhp = compund.getBoolean("HpLow");
   }

   @SideOnly(Side.CLIENT)
   public void handleStatusUpdate(byte par1) {
      if (par1 == 4) {
         this.attackTimer = 10;
         this.playSound(LegacyModSounds.get("mam", "mob.irongolem.throw"), 1.0F, 1.0F);
      } else {
         super.handleStatusUpdate(par1);
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

   protected void dropFewItems(boolean par1, int par2) {
      Random rand = new Random();
      int randomNumber = rand.nextInt(4);
      if (randomNumber == 1) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(MAMItems.dendroidroot, 1);
            this.dropItem(new ItemStack(Blocks.LOG).getItem(), 4);
         } else {
            this.dropItem(Items.COAL, 4);
         }
      } else if (randomNumber == 2) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(MAMItems.dendroidroot, 2);
            this.dropItem(new ItemStack(Blocks.LOG).getItem(), 9);
         } else {
            this.dropItem(Items.COAL, 6);
         }
      } else if (randomNumber == 3) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(MAMItems.dendroidroot, 3);
            this.dropItem(new ItemStack(Blocks.LOG).getItem(), 11);
            this.dropItem(MAMItems.badapple, 1);
         } else {
            this.dropItem(Items.COAL, 7);
         }
      } else if (randomNumber == 4) {
         if (!this.isBurning() && !this.setfire) {
            this.dropItem(MAMItems.dendroidroot, 4);
            this.dropItem(new ItemStack(Blocks.LOG).getItem(), 6);
            this.dropItem(MAMItems.badapple, 1);
         } else {
            this.dropItem(Items.COAL, 8);
         }
      }
   }
}
