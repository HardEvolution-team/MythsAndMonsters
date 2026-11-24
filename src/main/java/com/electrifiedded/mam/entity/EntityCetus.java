package com.electrifiedded.mam.entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.mam.lib.MAMCompat;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.registry.MAMItems;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCetus extends EntityWaterMob implements IAnimals {
   public int attackTime;

   @Override
   public void onEntityUpdate() {
      super.onEntityUpdate();
      if (this.attackTime > 0) {
         this.attackTime--;
      }
   }

   private int attackStrength = 2;
   private Entity entityToAttack = null;
   int tailCount = 10;
   private boolean canMount = true;
   public int courseChangeCooldown = 0;
   public double waypointX;
   public double waypointY;
   public double waypointZ;

   public EntityCetus(World par1World) {
      super(par1World);
      this.setSize(0.95F, 0.8F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0);
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }

   public float getPitchRotation() {
      return MathHelper.clamp((float)this.motionY, (float) Math.PI, (float) Math.PI);
   }

   protected boolean canDespawn() {
      return true;
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      int totalDamage = this.attackStrength;
      this.world.setEntityState(this, (byte)4);
      if (par1Entity instanceof EntityPlayer) {
         this.startRiding(par1Entity);
         EntityPlayer steve = (EntityPlayer)par1Entity;
         if (!steve.inventory.armorInventory.get(3).isEmpty()) {
            totalDamage--;
         }

         if (steve.getHealth() - totalDamage < 1.0F && this.getRidingEntity() == steve) {
            this.dismountRidingEntity();
         }
      }

      return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), totalDamage);
   }

   public boolean isInWater() {
      return this.world.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0, -0.5, 0.0), Material.WATER, this);
   }

   protected Entity findPlayerToAttack() {
      EntityPlayer entityplayer = this.world.getNearestAttackablePlayer(this, 24.0, 24.0);
      this.entityToAttack = entityplayer;
      return entityplayer;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.world.isRemote) {
         if (this.entityToAttack == null) {
            this.findPlayerToAttack();
         } else if (this.canEntityBeSeen(this.entityToAttack)) {
            this.attackEntity(this.entityToAttack, this.entityToAttack.getDistance(this));
         }
      }
      if (this.isRiding()) {
         this.ignoreFrustumCheck = true;
         if (this.tailCount >= 15) {
            if (this.inWater) {
               this.doRidingStuff(this.getRidingEntity());
            }

            this.tailCount = 0;
         }

         this.tailCount++;
      }

      if (!this.isRiding() && this.isInWater() && !this.world.isRemote) {
         this.doSwimmingStuff();
      }

      if (this.entityToAttack == null) {
         List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(24.0, 24.0, 24.0));

         for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity)targetList.get(i);
            if (entitytargetcheck instanceof EntityVillager || entitytargetcheck instanceof EntityHunter) {
               this.entityToAttack = entitytargetcheck;
            }
         }
      }

      if (!this.isInWater()) {
         Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
         int randomNumber = rand.nextInt(5);
         int randomYNumber = rand.nextInt(2);
         int randomPosX = (int)this.posX + randomNumber;
         int randomPosY = (int)this.posY + randomYNumber;
         int randomPosZ = (int)this.posZ + randomNumber;
         if (LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.WATER
            || LegacyWorld.getBlock(this.world, randomPosX, randomPosY, randomPosZ) == Blocks.FLOWING_WATER) {
            this.getNavigator().tryMoveToXYZ(randomPosX + 0.5, randomPosY + 0.5, randomPosZ + 0.5, 0.2);
         }
      }
   }

   private void doRidingStuff(Entity entity) {
      this.attackEntityAsMob(entity);
      if (entity instanceof EntityPlayer && !this.world.isRemote) {
         int r = this.rand.nextInt(3);
         int dr = this.rand.nextInt(6);
         boolean d = this.rand.nextBoolean();
         EntityPlayer steve = (EntityPlayer)entity;
         InventoryPlayer inv = steve.inventory;
         switch (dr) {
            case 0:
               MAMCompat.consumeItem(inv.player, Items.BEEF);
               MAMCompat.consumeItem(inv.player, Items.COOKED_BEEF);
               break;
            case 1:
               MAMCompat.consumeItem(inv.player, Items.PORKCHOP);
               MAMCompat.consumeItem(inv.player, Items.COOKED_PORKCHOP);
               break;
            case 2:
               MAMCompat.consumeItem(inv.player, Items.FISH);
               MAMCompat.consumeItem(inv.player, Items.COOKED_FISH);
               break;
            case 3:
               MAMCompat.consumeItem(inv.player, Items.CHICKEN);
               MAMCompat.consumeItem(inv.player, Items.COOKED_CHICKEN);
               break;
            case 4:
               MAMCompat.consumeItem(inv.player, MAMItems.jackalopemeat);
               MAMCompat.consumeItem(inv.player, MAMItems.jackalopemeatcooked);
               break;
            case 5:
               if (MAMCompat.consumeItem(inv.player, Items.ROTTEN_FLESH)) {
                  this.canMount = false;
                  this.dismountRidingEntity();
               }

               inv.addItemStackToInventory(new ItemStack(MAMItems.depthcrystal, 1));
         }

         if (!inv.armorInventory.get(r).isEmpty() && d) {
            inv.armorItemInSlot(r).damageItem(dr, steve);
         }
      }
   }

   protected void attackEntity(Entity par1Entity, float par2) {
      if (this.attackTime <= 0
         && par2 < 2.0F
         && par1Entity.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY
         && par1Entity.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
         this.attackTime = 65;
         this.attackEntityAsMob(par1Entity);
      }
   }

   public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand) {
      if (!par1EntityPlayer.getHeldItemMainhand().isEmpty() && par1EntityPlayer.getHeldItemMainhand() == new ItemStack(Items.ROTTEN_FLESH)) {
         if (this.getRidingEntity() != null && par1EntityPlayer == this.getRidingEntity()) {
            this.dismountRidingEntity();
         }

         par1EntityPlayer.getHeldItemMainhand().shrink(1);
         this.canMount = false;
         return true;
      } else {
         return false;
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

   private void doSwimmingStuff() {
      if (this.waypointY > 50.0) {
         this.waypointY -= 20.0;
      }

      double var1 = this.waypointX - this.posX;
      double var3 = this.waypointY - this.posY;
      double var5 = this.waypointZ - this.posZ;
      double var7 = var1 * var1 + var3 * var3 + var5 * var5;
      if (this.entityToAttack != null && this.canEntityBeSeen(this.entityToAttack)) {
         this.waypointX = this.entityToAttack.posX;
         this.waypointY = this.entityToAttack.posY + this.entityToAttack.getEyeHeight();
         this.waypointZ = this.entityToAttack.posZ;
         double varX = this.entityToAttack.posX - this.posX;
         double varY = this.entityToAttack.posY + this.entityToAttack.getEyeHeight() - this.posY;
         double varZ = this.entityToAttack.posZ - this.posZ;
         double varXXZZ = varX * varX + varZ * varZ;
         double varYY = varY * varY;
         if (varXXZZ < 20.0) {
            if (this.entityToAttack.posY + 1.0 > this.posY) {
               this.motionY += 0.08F;
            } else if (this.entityToAttack.posY + 1.0 < this.posY) {
               this.motionY -= 0.08F;
            }

            if (varXXZZ < 1.0 && varY < 2.0 && !(LegacyEntityHelper.getRider(this.entityToAttack) instanceof EntityCetus) && this.canMount) {
               this.startRiding(this.entityToAttack);
            }
         }
      } else if (var7 < 1.0 || var7 > 3600.0) {
         this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
         this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
         this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
      }

      if (this.courseChangeCooldown-- <= 0) {
         this.courseChangeCooldown = this.courseChangeCooldown + this.rand.nextInt(5) + 2;
         var7 = MathHelper.sqrt(var7);
         if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
            this.motionX += var1 / var7 * 0.1;
            this.motionY += var3 / var7 * 0.2;
            this.motionZ += var5 / var7 * 0.1;
         } else {
            this.waypointX = this.posX;
            this.waypointY = this.posY;
            this.waypointZ = this.posZ;
         }
      }

      if (this.entityToAttack != null && this.entityToAttack.isDead) {
         this.entityToAttack = null;
      }

      this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
   }

   public float getBlockPathWeight(net.minecraft.util.math.BlockPos bpw) {
      int par1 = bpw.getX(), par2 = bpw.getY(), par3 = bpw.getZ();
      return 1.0F - 3.0F * this.world.getLightBrightness(new BlockPos(par1, par2, par3));
   }
}
