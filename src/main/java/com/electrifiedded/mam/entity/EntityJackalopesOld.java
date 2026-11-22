package com.electrifiedded.mam.entity;
import net.minecraft.util.SoundEvent;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMItems;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityJackalopesOld extends EntityAnimal {
   public int attackTime;

   @Override
   public void onEntityUpdate() {
      super.onEntityUpdate();
      if (this.attackTime > 0) {
         this.attackTime--;
      }
   }

   int timerSound = 100;
   boolean makeSound = true;
   boolean hopping = false;
   int timer = 100;
   float jumpValue = 0.72F;

   public EntityJackalopesOld(World par1World) {
      super(par1World);
      this.setSize(0.6F, 0.7F);
      this.tasks.addTask(0, new net.minecraft.entity.ai.EntityAISwimming(this));
      this.tasks.addTask(1, new net.minecraft.entity.ai.EntityAIPanic(this, 1.25));
      this.tasks.addTask(5, new net.minecraft.entity.ai.EntityAIWander(this, 1.0));
      this.tasks.addTask(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.timerSound--;
      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(5.0, 5.0, 5.0));

      for (int j = 0; j < list.size(); j++) {
         Entity entity = (Entity)list.get(j);
         if (entity instanceof EntityPlayer) {
            this.makeSound = false;
         }
      }

      List list1 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(20.0, 20.0, 20.0));

      for (int k = 0; k < list1.size(); k++) {
         Entity entity1 = (Entity)list1.get(k);
         if (entity1 instanceof EntityPlayer) {
            this.makeSound = true;
         }

         if (this.timerSound <= 0 && this.makeSound) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(9);
            if (randomNumber == 1) {
               LegacyWorld.playSoundAtEntity(this.world, entity1, "mob.chicken.say", 1.0F, 1.0F);
            } else if (randomNumber == 2) {
               LegacyWorld.playSoundAtEntity(this.world, entity1, "mob.cow.say", 1.0F, 1.0F);
            } else if (randomNumber == 3) {
               LegacyWorld.playSoundAtEntity(this.world, entity1, "mob.cat.meow", 1.0F, 1.0F);
            } else if (randomNumber == 4) {
               LegacyWorld.playSoundAtEntity(this.world, entity1, "mob.pig.say", 1.0F, 1.0F);
            } else if (randomNumber == 5) {
               LegacyWorld.playSoundAtEntity(this.world, entity1, "mob.sheep.say", 1.0F, 1.0F);
            } else if (randomNumber == 6) {
               LegacyWorld.playSoundAtEntity(this.world, entity1, "mob.wolf.bark", 1.0F, 1.0F);
            }

            this.timerSound = 100;
         }
      }

      if (this.rand.nextInt(30) == 0 && this.timer <= 0 && !this.world.isRemote) {
         this.timer = 200 + this.rand.nextInt(450);
         this.hopping = !this.hopping;
      }

      this.timer--;
      if (this.hopping
         && (-1.0 <= this.motionX && this.motionX <= 1.0 || -1.0 <= this.motionZ && this.motionZ <= 1.0)
         && this.onGround
         && !this.world.isRemote) {
         this.motionY = this.motionY + this.jumpValue;
         this.jumpValue -= 0.06F;
         if (this.jumpValue < 0.5F) {
            this.jumpValue = 0.7F + this.rand.nextFloat() / 3.0F;
         }
      }
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      if (this.hopping) {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.3F);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9F);
      }
   }

   protected SoundEvent getAmbientSound() {
      return LegacyModSounds.get("mam", "mam:Wolpertinger");
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacyModSounds.get("mam", "mob.bat.hurt");
   }

   protected SoundEvent getDeathSound() {
      return LegacyModSounds.get("mam", "mam:Wolpertinger");
   }

   public EntityJackalopes createChild(EntityAgeable par1EntityAgeable) {
      return null;
   }

   protected void attackEntity(Entity par1Entity, float par2) {
      if (this.attackTime <= 0
         && par2 < 1.2F
         && par1Entity.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY
         && par1Entity.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
         this.attackTime = 16;
         par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength(this));
      }
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      return super.attackEntityFrom(par1DamageSource, par2);
   }

   private int getAttackStrength(Entity par1Entity) {
      return 2;
   }

   protected void dropFewItems(boolean par1, int par2) {
      if (this.rand.nextBoolean()) {
         if (this.rand.nextBoolean()) {
            this.dropItem(MAMItems.jackantler, 1);
         } else {
            this.dropItem(MAMItems.jackpaw, 1);
         }
      } else if (this.rand.nextBoolean()) {
         this.dropItem(Items.LEATHER, 1);
      } else {
         this.dropItem(MAMItems.jackalopemeat, 1);
      }
   }

   public void fall(float f, float damageMultiplier) {
      super.fall(f - 10.0F, damageMultiplier);
   }
}
