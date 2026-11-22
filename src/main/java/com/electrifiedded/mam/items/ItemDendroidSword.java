package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.entity.EntityDendroid;
import com.electrifiedded.mam.entity.EntityDendroidElder;
import com.electrifiedded.mam.entity.EntityDendroidRoot;
import com.electrifiedded.mam.entity.EntityDendroidSeer;
import com.electrifiedded.mam.entity.EntityDendroidSentinel;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.projectile.EntityStick;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDendroidSword extends ItemSword {
   int count = 3;

   public ItemDendroidSword() {
      super(MAMItems.EnumToolMaterialDendroid);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("dendroidsword");
      RegistryHelper.setTexture(this, "mam:ItemDendroidSword");
   }

   @Override
   public net.minecraft.util.ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, net.minecraft.util.EnumHand hand) {
      ItemStack itemStackIn = player.getHeldItem(hand);
      this.count--;
      if (!player.isSneaking()) {
         if (!player.world.isRemote && this.count <= 0) {
            EntityStick s = new EntityStick(player.world, player);
            LegacyWorld.playSoundAtEntity(player.world, player, "random.bow", 1.0F, 1.0F / (player.getRNG().nextFloat() * 0.4F + 0.8F));
            s.drop = false;
            MAMEntityUtil.spawnInWorld(player.world, s);
            this.count = 3;
         }

         itemStackIn.damageItem(2, player);
      }

      return new net.minecraft.util.ActionResult<>(net.minecraft.util.EnumActionResult.SUCCESS, itemStackIn);
   }

   public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
      if (!par2World.isRemote && par3Entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)par3Entity;
         ItemStack heldItem = player.getHeldItemMainhand();
         if (heldItem != null && heldItem.getItem() == MAMItems.dendroidsword) {
            int minHealth = 4;
            if (player.getAir() > 0
               && !player.isBurning()
               && player.getHealth() < minHealth
               && par1ItemStack.getMaxDamage() + par1ItemStack.getMetadata() * -1 > 20) {
               player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 15, 4));
               par1ItemStack.damageItem(20, player);
            }
         }
      }
   }

   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
      return EnumAction.BLOCK;
   }

   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
      if (entity instanceof EntityDendroid
         || entity instanceof EntityDendroidRoot
         || entity instanceof EntityDendroidElder
         || entity instanceof EntityDendroidSeer
         || entity instanceof EntityDendroidSentinel) {
         if (stack.getMetadata() - 2 >= 0) {
            stack.damageItem(-3, player);
         } else {
            stack.setItemDamage(0);
         }
      }

      return false;
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack == new ItemStack(MAMItems.dendroidroot);
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }
}
