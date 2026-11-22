package com.electrifiedded.mam.items;
import net.minecraft.inventory.EntityEquipmentSlot;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHunterLegging extends ItemArmor {
   public ItemHunterLegging(ArmorMaterial enumArmorMaterial, int renderIndex, EntityEquipmentSlot armorType) {
      super(enumArmorMaterial, renderIndex, armorType);
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("huntmanlegging");
      RegistryHelper.setTexture(this, "mam:HuntmansLeggings");
   }

   public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type) {
      return "mam:textures/models/armor/huntsman_layer_2.png";
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.COMMON;
   }

   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
      player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 1));
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.getItem() == MAMItems.frostjackalopehide;
   }
}
