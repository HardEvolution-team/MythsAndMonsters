package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.entity.LegacyCombat;
import com.electrifiedded.mam.lib.MAMCompat;
import com.electrifiedded.archaiclib.legacy.entity.LegacyDataWatcher;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityHunter extends EntityMAMEntityCreature implements IAnimals {
   int specialAttackTime = 0;

   public EntityHunter(World world) {
      super(world);
      ((net.minecraft.pathfinding.PathNavigateGround)this.getNavigator()).setBreakDoors(true);
      
      this.getNavigator().onUpdateNavigation();
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(3, new EntityAIAttackMelee(this, 0.4F, true));
      this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
      this.tasks.addTask(5, new EntityAIWander(this, 0.4F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityFairy.class, true));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityGoldenRam.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySandWyrm.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPigZombie.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityEnderman.class, true));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityHillGiant.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityDendroidSentinel.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityDendroid.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityDendroidElder.class, true));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityKitsune.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
   }

   protected void entityInit() {
      super.entityInit();
      LegacyDataWatcher.of(this).addObject(13, new Byte((byte)0));
   }

   @Override
   public void onLivingUpdate() {
      this.updateArmSwingProgress();
      if (this.specialAttackTime >= 200) {
         this.doSpecialAttack();
      }

      super.onLivingUpdate();
   }

   private void doSpecialAttack() {
      Item weapon = MAMCompat.getEquip(this, 0).getItem();
      if (weapon == MAMItems.tusklance) {
      }
   }

   public int getTotalArmorValue() {
      int var1 = (int)(super.getTotalArmorValue() / 1.8);
      if (var1 > 20) {
         var1 = 20;
      }

      return var1;
   }

   @Override
   public IEntityLivingData onInitialSpawn(net.minecraft.world.DifficultyInstance difficulty, IEntityLivingData p_110161_1_) {
      IEntityLivingData p_110161_1_1 = super.onInitialSpawn(difficulty, p_110161_1_);
      float f = difficulty.getClampedAdditionalDifficulty();
      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);
      this.setEquipmentBasedOnDifficulty(difficulty);
      this.setEnchantmentBasedOnDifficulty(difficulty);
      return p_110161_1_1;
   }

   @Override
   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      return super.attackEntityFrom(par1DamageSource, par2);
   }

   @Override
   public boolean attackEntityAsMob(Entity par1Entity) {
      int var2 = 3;
      if (this.isPotionActive(MobEffects.STRENGTH)) {
         var2 += 3 << this.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
      }

      if (this.isPotionActive(MobEffects.WEAKNESS)) {
         var2 -= 2 << this.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier();
      }

      int var3 = 0;
      if (par1Entity instanceof EntityLiving) {
         var2 = (int)(var2 + LegacyCombat.getEnchantmentModifierLiving(this, par1Entity));
         var3 += EnchantmentHelper.getKnockbackModifier(this);
      }

      boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
      if (var4) {
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

      return var4;
   }

   public boolean isAIEnabled() {
      return true;
   }

   @Override
   protected void setEquipmentBasedOnDifficulty(net.minecraft.world.DifficultyInstance difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      int i = this.rand.nextInt(6);
      if (i == 1) {
         MAMCompat.setEquip(this, 0, new ItemStack(Items.IRON_SWORD));
         MAMCompat.setEquip(this, 1, new ItemStack(Items.IRON_HELMET));
         MAMCompat.setEquip(this, 2, new ItemStack(Items.IRON_CHESTPLATE));
         MAMCompat.setEquip(this, 3, new ItemStack(Items.IRON_LEGGINGS));
         MAMCompat.setEquip(this, 4, new ItemStack(Items.IRON_BOOTS));
      } else if (i == 2) {
         MAMCompat.setEquip(this, 0, new ItemStack(Items.DIAMOND_SWORD));
         MAMCompat.setEquip(this, 1, new ItemStack(Items.DIAMOND_HELMET));
         MAMCompat.setEquip(this, 2, new ItemStack(Items.DIAMOND_CHESTPLATE));
         MAMCompat.setEquip(this, 3, new ItemStack(Items.DIAMOND_LEGGINGS));
         MAMCompat.setEquip(this, 4, new ItemStack(Items.DIAMOND_BOOTS));
      } else if (i == 3) {
         MAMCompat.setEquip(this, 0, new ItemStack(MAMItems.tusklance));
         MAMCompat.setEquip(this, 1, null);
         MAMCompat.setEquip(this, 2, new ItemStack(MythsAndMonstersMod.scalechest));
         MAMCompat.setEquip(this, 3, new ItemStack(MythsAndMonstersMod.scalelegging));
         MAMCompat.setEquip(this, 4, null);
      } else if (i == 4) {
         MAMCompat.setEquip(this, 0, new ItemStack(Items.STONE_SWORD));
         MAMCompat.setEquip(this, 1, new ItemStack(Items.LEATHER_HELMET));
         MAMCompat.setEquip(this, 2, new ItemStack(Items.LEATHER_CHESTPLATE));
         MAMCompat.setEquip(this, 3, new ItemStack(Items.LEATHER_LEGGINGS));
         MAMCompat.setEquip(this, 4, new ItemStack(Items.LEATHER_BOOTS));
      }
   }

   public boolean canDespawn() {
      return false;
   }
}
