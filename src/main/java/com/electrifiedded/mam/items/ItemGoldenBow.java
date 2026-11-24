package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import net.minecraft.init.Enchantments;
import net.minecraft.util.EnumHand;
import com.electrifiedded.mam.lib.MAMCompat;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.projectile.EntityGoldArrow;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import net.minecraft.world.World;

public class ItemGoldenBow extends LegacyItem {
   public ItemGoldenBow() {
      this.maxStackSize = 1;
      this.setMaxDamage(384);
      this.setTranslationKey("goldbow");
      // 1.7 swapped the icon in onUpdate (ItemGoldBow / 1 / 2 / 3 at 0 / 14 / 18 ticks); 1.12: json overrides on pull/pulling
      this.addPropertyOverride(new net.minecraft.util.ResourceLocation("pull"), (stack, world, entity) ->
         entity == null || entity.getActiveItemStack().getItem() != this ? 0.0F : (stack.getMaxItemUseDuration() - entity.getItemInUseCount()) / 20.0F);
      this.addPropertyOverride(new net.minecraft.util.ResourceLocation("pulling"), (stack, world, entity) ->
         entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }

   public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, net.minecraft.entity.EntityLivingBase par3EntityPlayer_, int par4) {
      if (!(par3EntityPlayer_ instanceof EntityPlayer)) return;
      EntityPlayer par3EntityPlayer = (EntityPlayer)par3EntityPlayer_;

      if (!par3EntityPlayer.capabilities.isCreativeMode && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, par1ItemStack) <= 0) {
         boolean var13 = false;
      } else {
         boolean var10000 = true;
      }

      boolean var5 = par3EntityPlayer.capabilities.isCreativeMode
         || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, par1ItemStack) > 0;
      if (var5 || MAMCompat.hasItem(par3EntityPlayer, MAMItems.goldarrow)) {
         int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
         float var7 = var6 / 20.0F;
         var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
         if (var7 < 0.1) {
            return;
         }

         if (var7 > 1.0F) {
            var7 = 1.0F;
         }

         EntityGoldArrow var8 = new EntityGoldArrow(par2World, par3EntityPlayer, var7 * 3.0F);
         if (var7 == 1.0F) {
            var8.setIsCritical(true);
         }

         int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, par1ItemStack);
         if (var9 > 0) {
            var8.setDamage(var8.getDamage() + var9 * 0.5 + 0.8);
         }

         int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, par1ItemStack);
         if (var10 > 0) {
            var8.setKnockbackStrength(var10);
         }

         if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, par1ItemStack) > 0) {
            var8.setFire(100);
         }

         par1ItemStack.damageItem(1, par3EntityPlayer);
         LegacyWorld.playSoundAtEntity(par2World, par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
         if (var5) {
            var8.pickupStatus = net.minecraft.entity.projectile.EntityArrow.PickupStatus.CREATIVE_ONLY;
         } else {
            MAMCompat.consumeItem(par3EntityPlayer, MAMItems.goldarrow);
         }

         if (!par2World.isRemote) {
            par2World.spawnEntity(var8);
         }
      }
   }

   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
      return 72000;
   }

   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
      return EnumAction.BOW;
   }

   public ItemStack onItemRightClickLegacy(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (par3EntityPlayer.capabilities.isCreativeMode || MAMCompat.hasItem(par3EntityPlayer, MAMItems.goldarrow)) {
         par3EntityPlayer.setActiveHand(EnumHand.MAIN_HAND);
      }

      return par1ItemStack;
   }

   public int getItemEnchantability() {
      return 1;
   }

   public boolean isFull3D() {
      return true;
   }
}
