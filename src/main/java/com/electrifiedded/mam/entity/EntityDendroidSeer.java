package com.electrifiedded.mam.entity;
import net.minecraft.util.EnumHand;
import com.electrifiedded.archaiclib.legacy.entity.LegacyCombat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.projectile.EntityStick;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDendroidSeer extends EntityMob implements IRangedAttackMob {
   private boolean hasHome = false;
   private int maxWanderDistance;
   private int[] Origin = new int[]{0, 0, 0};
   private int rangedCooldown = 10;
   private int clearCooldown = 30;
   protected String particle = "spell";

   public EntityDendroidSeer(World par1World) {
      super(par1World);
      this.setSize(1.0F, 2.5F);
      this.stepHeight = 2.0F;
      this.ignoreFrustumCheck = true;
      this.experienceValue = 375;
      this.initDendroid();
   }

   public void initDendroid() {
      this.experienceValue = 100;
      this.getNavigator().onUpdateNavigation();
      
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.29F, true));
      this.tasks.addTask(5, new EntityAIWander(this, 0.29F));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25, 20, 10.0F));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMob.class, false));
      this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCreeper.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroidSentinel.class, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(180.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(14.0);
   }

   public EntityDendroidSeer(World par1World, int x, int y, int z, int r) {
      this(par1World);
      this.hasHome = true;
      this.Origin[0] = x;
      this.Origin[1] = y;
      this.Origin[2] = z;
      this.maxWanderDistance = r;
   }

   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
      int repeats = 2;
      int extrashots = this.rand.nextInt(48);
      if (this.getHealth() <= 28.0F) {
         repeats = (int)(repeats + (29.0F - this.getHealth()) / 2.0F);
      } else if (extrashots <= 7) {
         repeats += extrashots + 1;
      }

      for (int shots = 0; shots < repeats; shots++) {
         if (!(par1EntityLivingBase instanceof EntityDendroid)
            && !(par1EntityLivingBase instanceof EntityDendroidSeer)
            && !(par1EntityLivingBase instanceof EntityDendroidElder)
            && !(par1EntityLivingBase instanceof EntityDendroidRoot)) {
            EntityStick entitysnowball = new EntityStick(this.world, this);
            double d0 = par1EntityLivingBase.posX - this.posX;
            double d1 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.1F - entitysnowball.posY;
            double d2 = par1EntityLivingBase.posZ - this.posZ;
            float f1 = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
            entitysnowball.shoot(d0, d1 + f1, d2, 1.6F, 12.0F);
            entitysnowball.drop = false;
            this.playSound(LegacyModSounds.get("mam", "random.bow"), 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            MAMEntityUtil.spawnInWorld(this.world, entitysnowball);
         }
      }
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.getAttackTarget() != null && this.getAttackTarget().isDead) {
         this.setAttackTarget(null);
      }

      int x = (int)Math.floor(this.posX);
      int y = (int)Math.floor(this.posY);
      int z = (int)Math.floor(this.posZ);
      if (this.isBurning()) {
         this.extinguish();

         for (int destroyx = -2; destroyx <= 2; destroyx++) {
            for (int destroyy = -1; destroyy <= 3; destroyy++) {
               for (int destroyz = -2; destroyz <= 2; destroyz++) {
                  if (LegacyWorld.getBlock(this.world, x + destroyx, y + destroyy, z + destroyz) == Blocks.LAVA
                     || LegacyWorld.getBlock(this.world, x + destroyx, y + destroyy, z + destroyz) == Blocks.FLOWING_LAVA
                     || LegacyWorld.getBlock(this.world, x + destroyx, y + destroyy, z + destroyz) == Blocks.FIRE) {
                     LegacyWorld.setBlock(this.world, x + destroyx, y + destroyy, z + destroyz, Blocks.AIR, 0, 2);
                     LegacyParticles.spawn(this.world, this.particle, x + destroyx, y + destroyy + 1, z + destroyz, 10.0, 10.0, 10.0);
                     LegacyParticles.spawn(this.world, this.particle, x + destroyx + 0.5, y + destroyy + 1, z + destroyz + 0.5, 10.0, 10.0, 10.0);
                     LegacyParticles.spawn(this.world, this.particle, x + 0.5, y, z + 0.5, 10.0, 10.0, 10.0);
                     LegacyParticles.spawn(this.world, this.particle, x + 0.5, y + 1, z + 0.5, 10.0, 10.0, 10.0);
                  }
               }
            }
         }
      }

      if (this.getAttackTarget() != null) {
         if (this.rangedCooldown > 0) {
            this.rangedCooldown--;
         } else {
            if (this.clearCooldown > 0) {
               this.clearCooldown--;
            } else if (this.recentlyHit > 0) {
               this.clearCooldown = 10 + this.rand.nextInt(10);
               this.teleportToOrigin();
            }

            this.rangedCooldown = 30;
         }
      }

      for (int p = 0; p < 2; p++) {
         LegacyParticles.spawn(this.world, 
               this.particle,
               this.posX - 0.5 + this.rand.nextFloat(),
               this.posY + 2.0F * this.rand.nextFloat(),
               this.posZ - 0.5 + this.rand.nextFloat(),
               10.0F * (-0.5F + this.rand.nextFloat()),
               10.0F * this.rand.nextFloat(),
               10.0F * (-0.5F + this.rand.nextFloat())
            );
      }
   }

   public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand) {
      ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
      if (!this.world.isRemote && itemstack != null && itemstack == new ItemStack(Items.SHEARS)) {
         itemstack.damageItem(1, par1EntityPlayer);
         this.dropItem(new ItemStack(Blocks.VINE).getItem(), 1);
         this.attackEntityAsMob(par1EntityPlayer);
         this.attackEntityFrom(DamageSource.causePlayerDamage(par1EntityPlayer), 7.0F);
      }

      return super.processInteract(par1EntityPlayer, hand);
   }

   protected void updateAITasks() {
      super.updateAITasks();
      if (this.hasHome) {
         int dx = (int)(this.posX - this.Origin[0]);
         int dy = (int)(this.posY - this.Origin[1]);
         int dz = (int)(this.posZ - this.Origin[2]);
         if (Math.sqrt(dx * dx + dy * dy + dz * dz) > this.maxWanderDistance) {
            this.teleportToOrigin();
            this.getNavigator().clearPath();
         }
      }
   }

   private void teleportToOrigin() {
      if (this.hasHome) {
         int x = (int)Math.floor(this.posX);
         int y = (int)Math.floor(this.posY);
         int z = (int)Math.floor(this.posZ);
         this.setPosition(this.Origin[0], this.Origin[1], this.Origin[2]);
         this.clearArea();

         for (int x1 = -1; x1 <= 1; x1++) {
            for (int y1 = -1; y1 <= 2; y1++) {
               for (int z1 = -1; z1 <= 1; z1++) {
                  if (LegacyWorld.getBlock(this.world, x + x1, y + y1, z + z1) == Blocks.AIR && !this.world.isRemote) {
                     LegacyWorld.setBlock(this.world, x + x1, y + y1, z + z1, Blocks.LEAVES, 2, 2);
                  }
               }
            }
         }
      }
   }

   private void clearArea() {
      for (int destroyx = -5; destroyx <= 5; destroyx++) {
         for (int destroyz = -5; destroyz <= 5; destroyz++) {
            for (int destroyy = -2; destroyy <= 4; destroyy++) {
               if (destroyy > -1) {
                  if ((
                        LegacyWorld.getBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz)
                              == Blocks.AIR
                           || LegacyWorld.getBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz)
                              != Blocks.RED_FLOWER
                     )
                     && LegacyWorld.getBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz)
                        == Blocks.YELLOW_FLOWER) {
                     LegacyWorld.setBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz, Blocks.AIR, 0, 2);
                     LegacyParticles.spawn(this.world, this.particle, this.Origin[0] + destroyx, this.Origin[1] + destroyy + 1, this.Origin[2] + destroyz, 10.0, 10.0, 10.0);
                     LegacyParticles.spawn(this.world, 
                           this.particle, this.Origin[0] + destroyx + 0.5, this.Origin[1] + destroyy + 1, this.Origin[2] + destroyz + 0.5, 10.0, 10.0, 10.0
                        );
                  }
               } else if (LegacyWorld.getBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz)
                     == Blocks.AIR
                  || LegacyWorld.getBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz) == Blocks.AIR
                  || !LegacyWorld.getBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz).getDefaultState().isOpaqueCube()) {
                  LegacyWorld.setBlock(this.world, this.Origin[0] + destroyx, this.Origin[1] + destroyy, this.Origin[2] + destroyz, Blocks.GRASS, 0, 2);
               }
            }
         }
      }
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setIntArray("SeersCrib", this.Origin);
      par1NBTTagCompound.setInteger("Wander", this.maxWanderDistance);
      par1NBTTagCompound.setBoolean("Home", this.hasHome);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.Origin = par1NBTTagCompound.getIntArray("SeersCrib");
      this.maxWanderDistance = par1NBTTagCompound.getInteger("Wander");
      this.hasHome = par1NBTTagCompound.getBoolean("Home");
   }

   public boolean canDespawn() {
      return false;
   }

   public boolean isAIEnabled() {
      return true;
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

   protected float getSoundVolume() {
      return 0.3F;
   }

   protected void collideWithEntity(Entity par1Entity) {
      if (par1Entity instanceof EntityMinecart) {
         par1Entity.attackEntityFrom(DamageSource.GENERIC, 100.0F);
      }

      if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((EntityLivingBase)par1Entity);
      }

      super.collideWithEntity(par1Entity);
   }

   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (source.equals(DamageSource.IN_WALL)) {
         this.world
            .createExplosion(this, this.posX, this.posY, this.posZ, 3.2F, this.world.getGameRules().getBoolean("mobGriefing"));
      }

      if (source.getTrueSource() instanceof EntityPlayer
         && ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand() != null
         && ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().getItem() instanceof ItemAxe
         && !this.world.isRemote
         && this.rand.nextInt(7) > 3) {
         this.dropItem(Items.STICK, 1);
      }

      return super.attackEntityFrom(source, damage);
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      int i = 14;
      if (this.isPotionActive(MobEffects.STRENGTH)) {
         i += 3 << this.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
      }

      if (this.isPotionActive(MobEffects.WEAKNESS)) {
         i -= 2 << this.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier();
      }

      int j = 0;
      if (par1Entity instanceof EntityLiving) {
         i = (int)(i + LegacyCombat.getEnchantmentModifierLiving(this, par1Entity));
         j += EnchantmentHelper.getKnockbackModifier(this);
      }

      boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
      if (flag) {
         LegacyWorld.playSoundAtEntity(this.world, this, "mob.irongolem.throw", 1.0F, 1.0F);
         par1Entity.motionY += 0.4F;
         if (j > 0) {
            par1Entity.addVelocity(
               -MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * j * 0.5F,
               0.1,
               MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * j * 0.5F
            );
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }

         int k = EnchantmentHelper.getFireAspectModifier(this);
         if (k > 0) {
            par1Entity.setFire(k * 4);
         }
      }

      return flag;
   }

   public void dropFewItems(boolean par1, int par2) {
      this.entityDropItem(new ItemStack(Blocks.VINE, this.rand.nextInt(16), 0), 1.0F);
      this.entityDropItem(new ItemStack(Blocks.LOG, 8, 2), 1.0F);
      this.entityDropItem(new ItemStack(MAMItems.dendroidroot, this.rand.nextInt(16), 0), 2.0F);
      this.entityDropItem(new ItemStack(Blocks.LOG, 14, 2), 2.0F);
      this.entityDropItem(new ItemStack(Blocks.LOG, this.rand.nextInt(32), 2), 3.0F);
      this.dropItem(MAMItems.dendroidsword, 1);
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }
}
