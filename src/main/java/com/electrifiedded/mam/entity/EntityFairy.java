package com.electrifiedded.mam.entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.entity.LegacyDataWatcher;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMBlocks;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityFairy extends EntityMob {
   protected Entity entityToAttack;
   protected int fleeingTick;
   protected boolean hasAttacked;

   private BlockPos spawnPosition;
   private int angerLevel;

   public EntityFairy(World par1World) {
      super(par1World);
      this.setSize(0.5F, 0.9F);
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityDendroidElder.class, 8.0F));
      this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityDendroid.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroidElder.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroid.class, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   protected boolean isAIEnabled() {
      return true;
   }

   protected void entityInit() {
      super.entityInit();
      LegacyDataWatcher.of(this).addObject(16, new Byte((byte)0));
   }

   protected void playStepSound(BlockPos stepPos, Block blockIn) {
      this.playSound(LegacyModSounds.get("mam", "random.orb"), 0.15F, 1.0F);
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "random.orb");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "random.levelup");
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   protected boolean canDespawn() {
      return true;
   }

   public void fall(float par1, float damageMultiplier) {
   }

   public void onLivingUpdate() {
      if (this.motionY <= -0.2) {
         this.motionY = -0.2;
      }

      if (this.isWet()) {
         this.teleportRandomly();
      }

      for (int i = 0; i < 1; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = 10.0;
         LegacyParticles.spawn(this.world, "fireworksSpark",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
      }

      if (this.getAttackTarget() == null) {
         this.motionY *= 0.6F;
      }

      super.onLivingUpdate();
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public float getBrightness() {
      return 1.0F;
   }

   protected void updateAITasks() {
      super.updateAITasks();
      if (this.getAttackTarget() == null) {
         if (this.spawnPosition != null
            && (
               !LegacyWorld.isAirBlock(this.world, this.spawnPosition.getX(), this.spawnPosition.getY(), this.spawnPosition.getZ())
                  || this.spawnPosition.getY() < 1
            )) {
            this.spawnPosition = null;
         }

         if (this.spawnPosition == null
            || this.rand.nextInt(30) == 0
            || this.spawnPosition.distanceSq((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F) {
            this.spawnPosition = new BlockPos(
               (int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7),
               (int)this.posY + this.rand.nextInt(6) - 2,
               (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7)
            );
         }

         double d0 = this.spawnPosition.getX() + 0.5 - this.posX;
         double d1 = this.spawnPosition.getY() + 0.1 - this.posY;
         double d2 = this.spawnPosition.getZ() + 0.5 - this.posZ;
         this.motionX = this.motionX + (Math.signum(d0) * 0.5 - this.motionX) * 0.1F;
         this.motionY = this.motionY + (Math.signum(d1) * 0.7F - this.motionY) * 0.1F;
         this.motionZ = this.motionZ + (Math.signum(d2) * 0.5 - this.motionZ) * 0.1F;
         float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0F;
         float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
         this.moveForward = 0.5F;
         this.rotationYaw += f1;
      }
   }

   protected boolean teleportRandomly() {
      double d0 = this.posX + (this.rand.nextDouble() - 0.5) * 64.0;
      double d1 = this.posY + (this.rand.nextInt(64) - 32);
      double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 64.0;
      return this.teleportTo(d0, d1, d2);
   }

   protected boolean teleportTo(double par1, double par3, double par5) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3, par5, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         double d3 = this.posX;
         double d4 = this.posY;
         double d5 = this.posZ;
         this.posX = event.getTargetX();
         this.posY = event.getTargetY();
         this.posZ = event.getTargetZ();
         boolean flag = false;
         int i = MathHelper.floor(this.posX);
         int j = MathHelper.floor(this.posY);
         int k = MathHelper.floor(this.posZ);
         if (LegacyWorld.blockExists(this.world, i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
               Block block = LegacyWorld.getBlock(this.world, i, j - 1, k);
               if (block.getDefaultState().getMaterial().blocksMovement()) {
                  flag1 = true;
               } else {
                  this.posY--;
                  j--;
               }
            }

            if (flag1) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if (this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
                  flag = true;
               }
            }
         }

         if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
         } else {
            short short1 = 128;

            for (int l = 0; l < short1; l++) {
               double d6 = l / (short1 - 1.0);
               float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
               double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               LegacyParticles.spawn(this.world, "portal", d7, d8, d9, f, f1, f2);
            }

            LegacyWorld.playSoundEffect(this.world, d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound(LegacyModSounds.get("mam", "mob.endermen.portal"), 1.0F, 1.0F);
            return true;
         }
      }
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setShort("AngerFairy", (short)this.angerLevel);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.angerLevel = par1NBTTagCompound.getShort("AngerFairy");
   }

   protected Entity findPlayerToAttack() {
      return this.angerLevel == 0 ? null : this.world.getNearestAttackablePlayer(this, 16.0, 16.0);
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      if (par1DamageSource.equals(DamageSource.DROWN)) {
         return false;
      } else {
         Entity entity = par1DamageSource.getTrueSource();
         if (entity instanceof EntityPlayer) {
            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(32.0, 32.0, 32.0));

            for (int i = 0; i < list.size(); i++) {
               Entity entity1 = (Entity)list.get(i);
               if (entity1 instanceof EntityFairy) {
                  EntityFairy entitypigzombie = (EntityFairy)entity1;
                  entitypigzombie.becomeAngryAt(entity);
               }
            }

            this.becomeAngryAt(entity);
         }

         return super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   private void becomeAngryAt(Entity par1Entity) {
      this.entityToAttack = par1Entity;
      this.angerLevel = 400 + this.rand.nextInt(400);
   }

   public void bottleRelease() {
      this.attackEntityFrom(DamageSource.IN_WALL, 4.0F);
      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(10.0, 10.0, 10.0));

      for (int j = 0; j < list.size(); j++) {
         Entity entity = (Entity)list.get(j);
         if (entity instanceof EntityPlayer || entity instanceof EntityHunter) {
            this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
            break;
         }

         if (entity instanceof EntityLiving) {
            this.becomeAngryAt((EntityLiving)entity);
         }
      }
   }

   public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand) {
      ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
      if (itemstack != null && itemstack.getItem() == new ItemStack(MAMBlocks.jar).getItem() && itemstack.getMetadata() == 0) {
         this.setAttackTarget(par1EntityPlayer);
         this.becomeAngryAt(par1EntityPlayer);
         if (this.rand.nextInt(4) == 0 && !this.world.isRemote) {
            this.setDead();
            itemstack.shrink(1);
            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(MAMBlocks.angryfairyjar, 1, 1));
            LegacyWorld.playSoundAtEntity(this.world, par1EntityPlayer, "random.pop", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 1.2F) * 0.5F);
         }
      }

      return super.processInteract(par1EntityPlayer, hand);
   }
}
