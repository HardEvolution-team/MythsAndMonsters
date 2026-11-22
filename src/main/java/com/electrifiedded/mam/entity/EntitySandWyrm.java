package com.electrifiedded.mam.entity;
import com.electrifiedded.archaiclib.legacy.entity.ai.ILegacyCreature;
import com.electrifiedded.archaiclib.legacy.entity.ai.EntityAILegacyCreature;

import net.minecraft.util.math.BlockPos;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMItems;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySandWyrm extends EntityMAMEntityCreature implements ILegacyCreature, IMob {
   public int attackTime;

   @Override
   public void onEntityUpdate() {
      super.onEntityUpdate();
      if (this.attackTime > 0) {
         this.attackTime--;
      }
   }

   public Entity entity = null;
   protected int attackStrength = 5;
   private boolean leapcheck = false;
   private boolean discovered = false;
   private int activator = 0;
   boolean leapcheckground = false;

   public EntitySandWyrm(World par1World) {
      super(par1World);
      this.setSize(1.2F, 0.5F);
      this.experienceValue = 10;
      this.stepHeight = 1.0F;
      this.noClip = false;
      this.ignoreFrustumCheck = true;
      this.isImmuneToFire = false;
      this.tasks.addTask(0, new net.minecraft.entity.ai.EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAILegacyCreature(this, 1.0));
   }

   protected void updateAITasks() {
      super.updateAITasks();
   }

   public boolean isAIEnabled() {
      return false;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0);
   }

   public float getAnimation(float par1) {
      if (this.isAirBorne) {
         return LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.SAND
            ? 2.0F
            : (float)(-this.motionY);
      } else if (!this.onGround && this.motionY != 0.0) {
         if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == Blocks.SAND) {
            return 1.0F;
         } else {
            return this.noClip && !this.isAirBorne ? 0.0F : (float)(-this.motionY);
         }
      } else {
         return 94.2F;
      }
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.activator++;
      if (this.activator >= 30) {
         this.activator = 0;
         this.leapcheckground = false;
      }

      if (this.entityToAttack != null) {
         this.discovered = true;
      } else {
         this.discovered = false;
         List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(16.0, 16.0, 16.0));

         for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity)targetList.get(i);
            if (entitytargetcheck instanceof EntityVillager || entitytargetcheck instanceof EntityHunter) {
               this.entityToAttack = entitytargetcheck;
            }
         }
      }

      if (!this.world.isRemote) {
         this.divingAI();
      }
   }

   private void divingAI() {
      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(2.0, 12.0, 2.0));
      if (this.discovered && this.getHealth() > 7.0F) {
         if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.SAND) {
            if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.SAND) {
               this.noClip = true;
            } else if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.SAND
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.AIR
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.FLOWING_WATER
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.WATER
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.FLOWING_LAVA
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.LAVA
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.LEAVES
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.TALLGRASS
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.DEADBUSH
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.YELLOW_FLOWER
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.RED_FLOWER
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.BROWN_MUSHROOM
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.RED_MUSHROOM
               && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.SNOW_LAYER) {
               this.noClip = true;
               this.motionY += 0.2;
            } else {
               this.noClip = false;
               if (!this.leapcheckground
                  && (
                     LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.GRASS
                        || LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ)
                           == Blocks.DIRT
                  )) {
                  for (int i = 0; i < list.size(); i++) {
                     Entity entitycheck = (Entity)list.get(i);
                     if (entitycheck == this.entityToAttack) {
                        this.motionY += 0.5;
                        this.leapcheckground = true;
                     }
                  }
               }
            }
         } else {
            this.noClip = true;

            for (int ix = 0; ix < list.size(); ix++) {
               Entity entitycheck = (Entity)list.get(ix);
               if (entitycheck instanceof EntityPlayer || entitycheck == this.entityToAttack) {
                  this.motionY += 0.5;
                  this.leapcheck = true;
               }
            }

            if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.SAND
               || LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.AIR) {
               this.noClip = true;
               if (this.motionY <= -1.0) {
                  this.motionY = 0.0;
               }
            } else if (this.motionY < 0.0) {
               this.motionY = 0.0;
            }
         }

         if (!this.noClip
            && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) != Blocks.SAND
            && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) != Blocks.AIR) {
            this.leapcheck = false;
         }
      } else if (this.discovered && this.getHealth() <= 7.0F || !this.discovered) {
         if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == Blocks.SAND) {
            this.motionY += 0.5;
         } else {
            this.noClip = false;
         }
      }
   }

   public void fall(float par1, float damageMultiplier) {
      if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.GRASS
         && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) != Blocks.GRASS) {
         if (!this.noClip) {
            int i = (int)Math.ceil(par1 - 3.0F);
            if (i > 0) {
               if (LegacyWorld.getBlock(this.world, 
                           MathHelper.floor(this.posX),
                           MathHelper.floor(this.posY - 0.2F),
                           MathHelper.floor(this.posZ)
                        )
                     == Blocks.SAND
                  || LegacyWorld.getBlock(this.world, 
                           MathHelper.floor(this.posX),
                           MathHelper.floor(this.posY - 0.2F),
                           MathHelper.floor(this.posZ)
                        )
                     == Blocks.AIR) {
                  return;
               }

               if (i > 4) {
                  LegacyWorld.playSoundAtEntity(this.world, this, "damage.fallbig", 1.0F, 1.0F);
               } else {
                  LegacyWorld.playSoundAtEntity(this.world, this, "damage.fallsmall", 1.0F, 1.0F);
               }

               if (LegacyWorld.getBlock(this.world, 
                        MathHelper.floor(this.posX),
                        MathHelper.floor(this.posY - 0.2F),
                        MathHelper.floor(this.posZ)
                     )
                  != Blocks.AIR) {
                  LegacyWorld.playSoundAtEntity(this.world, this, "dig.sand", 0.5F, 0.75F);
               }

               this.attackEntityFrom(DamageSource.FALL, i);
            }
         }
      }
   }

   @Override
   public boolean attackEntityAsMob(Entity par1Entity) {
      this.world.setEntityState(this, (byte)4);
      boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.attackStrength);
      if (flag) {
         par1Entity.motionY += 0.4F;
      }

      return flag;
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      return !par1DamageSource.equals(DamageSource.IN_WALL) && !par1DamageSource.equals(DamageSource.CACTUS)
         ? super.attackEntityFrom(par1DamageSource, par2)
         : false;
   }

   public void attackEntity(Entity par1Entity, float par2) {
      if (this.attackTime <= 0
         && par2 < 1.2F
         && par1Entity.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY
         && par1Entity.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
         this.attackTime = 20;
         par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.attackStrength);
      }
   }

   public Entity findPlayerToAttack() {
      return this.world.getNearestAttackablePlayer(this, 16.0, 16.0);
   }

   protected void dropFewItems(boolean par1, int par2) {
      boolean g = this.rand.nextBoolean();
      boolean g1 = this.rand.nextBoolean();
      if (g) {
         this.dropItem(MAMItems.sandscale, 1);
         this.dropItem(MAMItems.sandscale, 1);
      } else {
         int lol = this.rand.nextInt(3);
         if (g1) {
            lol = this.rand.nextInt(6);
         }

         if (lol <= 1) {
            this.dropItem(MAMItems.sandscale, 1);
            this.dropItem(MAMItems.sandscale, 2);
         } else if (lol == 2) {
            this.dropItem(MAMItems.sandscale, 1);
         } else if (lol == 3) {
            this.dropItem(MAMItems.sandscale, 1);
            this.dropItem(MAMItems.tusk, 1);
         } else if (lol == 4 || lol == 5) {
            this.dropItem(MAMItems.sandscale, 2);
            this.dropItem(MAMItems.tusk, 1);
         } else if (lol == 6) {
            this.dropItem(MAMItems.sandscale, 1);
            this.dropItem(MAMItems.tusk, 2);
         }
      }
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setBoolean("discovered", this.discovered);
      par1NBTTagCompound.setBoolean("noClip", this.noClip);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      par1NBTTagCompound.getBoolean("discovered");
      par1NBTTagCompound.getBoolean("noClip");
   }

   public float getBlockPathWeight(net.minecraft.util.math.BlockPos bp) {
      return LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.SAND
         ? 30.0F
         : super.getBlockPathWeight(bp);
   }

   public int getBrightnessForRender() {
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.posZ);
      if (LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == Blocks.SAND) {
         return 0;
      } else if (LegacyWorld.blockExists(this.world, i, 0, j)) {
         double d = (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * 0.66;
         int k = MathHelper.floor(this.posY + d);
         return this.world.getCombinedLight(new BlockPos(i, k, j), 0);
      } else {
         return 0;
      }
   }

   public void collideWithEntity(Entity e) {
      if (e instanceof EntityVillager || e instanceof EntityHunter || e instanceof EntityPlayer) {
         this.attackEntityAsMob(e);
      }
   }

   @Override
   public Entity getEntityToAttack() {
      return this.entityToAttack;
   }

   @Override
   public void setEntityToAttack(Entity target) {
      this.entityToAttack = target;
   }
}
