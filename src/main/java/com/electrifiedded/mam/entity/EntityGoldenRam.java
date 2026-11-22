package com.electrifiedded.mam.entity;
import net.minecraft.util.EnumHand;
import com.electrifiedded.archaiclib.legacy.entity.LegacyCombat;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.entity.LegacyDataWatcher;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMBlocks;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGoldenRam extends EntityMAMEntityCreature implements IAnimals {
   protected int attackStrength;
   private boolean hasInteracted;
   int leavetickCount = 50;
   private boolean fairy = true;
   private int ramTimer;
   private boolean fed = false;
   private int grudge = 0;
   private EntityAIEatGrassPlus aiEatGrass;
   private int fedtick = 0;
   public Entity entity = null;
   int target = 0;
   boolean gotcha = false;
   private int fedcount = 0;

   public EntityGoldenRam(World par1World) {
      super(par1World);
      this.setSize(0.9F, 1.3F);
      this.attackStrength = 4;
      this.experienceValue = 15;
      this.aiEatGrass = new EntityAIEatGrassPlus(this);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.6000000000000001, true));
      this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.25, 32.0F));
      this.tasks.addTask(4, new EntityAITempt(this, 0.4, Items.GOLDEN_APPLE, false));
      this.tasks.addTask(4, new EntityAITempt(this, 0.4, MAMItems.badapple, false));
      this.tasks.addTask(6, new EntityAIWander(this, 0.4));
      this.tasks.addTask(10, new EntityAILookIdle(this));
      this.tasks.addTask(5, this.aiEatGrass);
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroidSentinel.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroid.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroidElder.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDendroidRoot.class, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPigZombie.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEnderman.class, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, false));
   }

   protected boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0);
   }

   public void knockBack(Entity par1Entity, float par2, double par3, double par5) {
      this.isAirBorne = true;
      float f = MathHelper.sqrt(par3 * par3 + par5 * par5);
      float f1 = 0.4F;
      this.motionX -= par3 / f * f1 * 0.1;
      this.motionY += f1;
      this.motionZ -= par5 / f * f1 * 0.1;
      if (this.motionY > 0.20000000596046447) {
         this.motionY = 0.20000000596046447;
      }
   }

   @Override
   public boolean attackEntityAsMob(Entity par1Entity) {
      int i = this.attackStrength;
      boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.attackStrength);
      int var3 = 0;
      if (par1Entity instanceof EntityLiving) {
         i = (int)(i + LegacyCombat.getEnchantmentModifierLiving(this, par1Entity));
         var3 += EnchantmentHelper.getKnockbackModifier(this);
      }

      if (flag) {
         par1Entity.motionY += 0.2;
         if (par1Entity instanceof EntityPlayer) {
            ((EntityPlayer)par1Entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 3));
            ((EntityPlayer)par1Entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 3));
         } else if (par1Entity instanceof EntityHunter) {
            ((EntityHunter)par1Entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 3));
         }

         if (var3 > 0) {
            par1Entity.addVelocity(
               -MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * var3 * 0.5F,
               0.1,
               MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * var3 * 0.5F
            );
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }

         int var5 = EnchantmentHelper.getFireAspectModifier(this);
         if (var5 > 0) {
            par1Entity.setFire(var5 * 4);
         }
      }

      if (this.isPotionActive(MobEffects.STRENGTH)) {
         i += 3 << this.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
      }

      if (this.isPotionActive(MobEffects.WEAKNESS)) {
         i -= 2 << this.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier();
      }

      if (!(par1Entity instanceof EntityDendroidElder) && !(par1Entity instanceof EntityDendroid)) {
         if (par1Entity instanceof EntityPlayer) {
            this.grudge = 500;
         }

         return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
      } else {
         if (this.fairy) {
            EntityFairy entitya = new EntityFairy(this.world);
            entitya.setLocationAndAngles(this.posX - 0.2F, this.posY, this.posZ - 0.2F, this.rotationYaw, 0.0F);
            this.world.spawnEntity(entitya);
            EntityFairy entityb = new EntityFairy(this.world);
            entityb.setLocationAndAngles(this.posX + 0.2F, this.posY, this.posZ + 0.2F, this.rotationYaw, 0.0F);
            this.world.spawnEntity(entityb);
            this.fairy = false;
         }

         return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3 * i);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      this.entity = par1DamageSource.getTrueSource();
      return super.attackEntityFrom(par1DamageSource, par2);
   }

   protected void updateAITasks() {
      this.ramTimer = this.aiEatGrass.func_48396_h();
      super.updateAITasks();
   }

   @Override
   public void onLivingUpdate() {
      this.leavetickCount--;
      if (this.isAngry()) {
         this.target++;
         if (!this.gotcha) {
            this.setAttackTarget((EntityLivingBase)this.entity);
            if (this.entity instanceof EntityPlayer) {
               this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
            }

            this.gotcha = true;
         }

         if (this.target >= 500) {
            this.setAngry(false);
            this.target = 0;
         }
      }

      if (this.fed) {
         this.fedtick++;
      }

      if (this.grudge >= 0) {
         this.grudge--;
      }

      if (this.fedtick == 400) {
         this.fedtick = 0;
         this.fed = false;
      }

      if (this.fedcount == 5) {
         LegacyParticles.spawn(this.world, "spell",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
               this.posY + 0.5 + this.rand.nextFloat() * this.height,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width,
               0.0,
               0.0,
               0.0
            );
         LegacyParticles.spawn(this.world, "spell",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
               this.posY + 0.5 + this.rand.nextFloat() * this.height,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width,
               0.0,
               0.0,
               0.0
            );
         LegacyParticles.spawn(this.world, "spell",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
               this.posY + 0.5 + this.rand.nextFloat() * this.height,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width,
               0.0,
               0.0,
               0.0
            );
         this.fedcount = 0;
         if (this.rand.nextBoolean()) {
            if (this.rand.nextBoolean() && !this.getSheared()) {
               if (this.rand.nextBoolean()) {
                  if (!this.world.isRemote) {
                     this.dropItem(Items.GOLDEN_SWORD, 1);
                     this.dropItem(Items.GOLDEN_CHESTPLATE, 1);
                     this.dropItem(Items.GOLDEN_HELMET, 1);
                  }
               } else if (!this.world.isRemote) {
                  this.dropItem(Items.GOLDEN_LEGGINGS, 1);
                  this.dropItem(Items.GOLDEN_BOOTS, 1);
               }
            } else if (!this.world.isRemote) {
               this.dropItem(new ItemStack(Blocks.RED_FLOWER).getItem(), 1);
               this.dropItem(new ItemStack(Blocks.YELLOW_FLOWER).getItem(), 1);
               this.dropItem(new ItemStack(Blocks.RED_FLOWER).getItem(), 1);
               this.dropItem(new ItemStack(Blocks.YELLOW_FLOWER).getItem(), 1);
            }
         } else if (!this.world.isRemote) {
            this.dropItem(Items.WHEAT_SEEDS, 1);
            this.dropItem(Items.WHEAT_SEEDS, 1);
            this.dropItem(Items.WHEAT_SEEDS, 1);
            this.dropItem(Items.WHEAT_SEEDS, 1);
         }
      }

      if (this.fed) {
         LegacyParticles.spawn(this.world, "heart",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
               this.posY + 0.5 + this.rand.nextFloat() * this.height,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width,
               0.0,
               0.0,
               0.0
            );
      }

      if (this.world.isRemote) {
         this.ramTimer = Math.max(0, this.ramTimer - 1);
      }

      int xx = (int)Math.floor(this.posX);
      int yy = (int)Math.floor(this.posY);
      int zz = (int)Math.floor(this.posZ);
      if (!this.getSheared() && !this.world.isRemote && this.leavetickCount <= 0) {
         int randomPosX = (int)this.posX + this.rand.nextInt(5);
         int randomPosY = (int)this.posY + this.rand.nextInt(5);
         int randomPosZ = (int)this.posZ + this.rand.nextInt(5);
         if (LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == MAMBlocks.gravel) {
            LegacyParticles.spawn(this.world, "spell", randomPosX, randomPosY + 1.4F, randomPosZ, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX + 0.4, randomPosY + 1.4F, randomPosZ + 0.4, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX - 0.4, randomPosY + 1.4F, randomPosZ - 0.4, 0.0, 0.0, 0.0);
            LegacyWorld.setBlock(this.world, randomPosX, randomPosY, randomPosZ, Blocks.LEAVES);
            this.leavetickCount = 50;
         } else if (LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.FIRE) {
            LegacyParticles.spawn(this.world, "spell", randomPosX, randomPosY + 1.4F, randomPosZ, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX + 0.4, randomPosY + 1.4F, randomPosZ + 0.4, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX - 0.4, randomPosY + 1.4F, randomPosZ - 0.4, 0.0, 0.0, 0.0);
            LegacyWorld.setBlock(this.world, randomPosX, randomPosY, randomPosZ, Blocks.AIR);
            this.leavetickCount = 50;
         } else if (LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.DIRT
            && LegacyWorld.getBlock(this.world, randomPosX, randomPosY + 1, randomPosZ) == Blocks.AIR) {
            LegacyParticles.spawn(this.world, "spell", randomPosX, randomPosY + 1.4F, randomPosZ, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX + 0.4, randomPosY + 1.4F, randomPosZ + 0.4, 0.0, 0.0, 0.0);
            LegacyParticles.spawn(this.world, "spell", randomPosX - 0.4, randomPosY + 1.4F, randomPosZ - 0.4, 0.0, 0.0, 0.0);
            LegacyWorld.setBlock(this.world, randomPosX, randomPosY, randomPosZ, Blocks.GRASS);
            this.leavetickCount = 50;
         }
      }

      super.onLivingUpdate();
   }

   protected void dropFewItems(boolean par1, int par2) {
      if (!this.getSheared()) {
         this.entityDropItem(new ItemStack(MAMItems.goldenfleece, 1), 1.0F);
      }
   }

   protected Item getDropItem() {
      return MAMItems.goldenfleece;
   }

   public void handleStatusUpdate(byte par1) {
      if (par1 == 10) {
         this.ramTimer = 40;
      } else {
         super.handleStatusUpdate(par1);
      }
   }

   public float func_44003_c(float par1) {
      if (this.ramTimer <= 0) {
         return 0.0F;
      } else if (this.ramTimer >= 4 && this.ramTimer <= 36) {
         return 1.0F;
      } else {
         return this.ramTimer < 4 ? (this.ramTimer - par1) / 4.0F : -(this.ramTimer - 40 - par1) / 4.0F;
      }
   }

   public float func_44002_d(float par1) {
      if (this.ramTimer > 4 && this.ramTimer <= 36) {
         float f = (this.ramTimer - 4 - par1) / 32.0F;
         return (float) (Math.PI / 5) + 0.2199115F * MathHelper.sin(f * 28.7F);
      } else {
         return this.ramTimer > 0 ? (float) (Math.PI / 5) : this.rotationPitch / (180.0F / (float)Math.PI);
      }
   }

   public boolean isGA(ItemStack par1ItemStack) {
      return par1ItemStack.getItem() == Items.GOLDEN_APPLE;
   }

   public boolean isBA(ItemStack par1ItemStack) {
      return par1ItemStack.getItem() == MAMItems.badapple;
   }

   public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand) {
      ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
      if (itemstack != null && this.isGA(itemstack) && this.grudge <= 0) {
         if (this.fed) {
            this.fedcount++;
            itemstack.shrink(1);
            if (itemstack.getCount() <= 0) {
               par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
            }
         } else {
            this.fed = true;
            this.fedcount = 0;
            itemstack.shrink(1);
            if (itemstack.getCount() <= 0) {
               par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
            }
         }
      } else if (itemstack != null && this.isBA(itemstack) && this.grudge <= 0) {
         itemstack.shrink(1);
         if (itemstack.getCount() <= 0) {
            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(MAMItems.taintedseed));
         }

         this.addPotionEffect(new PotionEffect(MobEffects.WITHER, 300, 4));
         this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 6));
         this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 6));
         this.attackEntityFrom(DamageSource.causeMobDamage(par1EntityPlayer), 15);
         this.setAttackTarget(par1EntityPlayer);
         MAMEntityUtil.spawnInWorld(this.world, new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, 50));
      } else if (itemstack != null && itemstack.getItem() == Items.SHEARS && !this.getSheared() && !this.isChild()) {
         if (!this.world.isRemote) {
            this.setSheared(true);
            EntityItem entityitem = this.entityDropItem(new ItemStack(MAMItems.goldenfleece, 1), 1.0F);
            entityitem.motionY = entityitem.motionY + this.rand.nextFloat() * 0.05F;
            entityitem.motionX = entityitem.motionX + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
            entityitem.motionZ = entityitem.motionZ + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
         }

         if (this.fed) {
         }

         itemstack.damageItem(1, par1EntityPlayer);
         if (!this.fed) {
            this.attackEntityAsMob(par1EntityPlayer);
            this.attackEntityFrom(DamageSource.causeMobDamage(par1EntityPlayer), 20);
         }
      }

      return super.processInteract(par1EntityPlayer, hand);
   }

   protected SoundEvent getAmbientSound() {
      return LegacyModSounds.get("mam", "mob.sheep.say");
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "mob.sheep.say");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "mob.sheep.say");
   }

   public boolean getSheared() {
      return (LegacyDataWatcher.of(this).getWatchableObjectByte(16) & 16) != 0;
   }

   public void setSheared(boolean par1) {
      byte byte0 = LegacyDataWatcher.of(this).getWatchableObjectByte(16);
      if (par1) {
         LegacyDataWatcher.of(this).updateObject(16, (byte)(byte0 | 16));
      } else {
         LegacyDataWatcher.of(this).updateObject(16, (byte)(byte0 & -17));
      }
   }

   public void eatGrassBonus() {
      this.setSheared(false);
   }

   public void setAttackTarget(EntityLivingBase par1EntityLiving) {
      super.setAttackTarget(par1EntityLiving);
      if (par1EntityLiving instanceof EntityPlayer) {
         this.setAngry(true);
      }
   }

   protected void entityInit() {
      super.entityInit();
      LegacyDataWatcher.of(this).addObject(16, (byte)0);
   }

   public boolean isAngry() {
      return (LegacyDataWatcher.of(this).getWatchableObjectByte(16) & 2) != 0;
   }

   public void setAngry(boolean par1) {
      byte byte0 = LegacyDataWatcher.of(this).getWatchableObjectByte(16);
      if (par1) {
         LegacyDataWatcher.of(this).updateObject(16, (byte)(byte0 | 2));
      } else {
         LegacyDataWatcher.of(this).updateObject(16, (byte)(byte0 & -3));
      }
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setBoolean("Angry", this.isAngry());
      par1NBTTagCompound.setBoolean("Interacted", this.hasInteracted);
      par1NBTTagCompound.setBoolean("Sheared", this.getSheared());
      par1NBTTagCompound.setInteger("grudge", this.grudge);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.hasInteracted = par1NBTTagCompound.getBoolean("Interacted");
      this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
      this.setSheared(par1NBTTagCompound.getBoolean("Sheared"));
      this.grudge = par1NBTTagCompound.getInteger("grudge");
   }
}
