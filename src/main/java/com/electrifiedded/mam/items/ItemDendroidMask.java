package com.electrifiedded.mam.items;
import net.minecraft.inventory.EntityEquipmentSlot;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDendroidMask extends ItemArmor {
   public ItemDendroidMask(ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4) {
      super(par2EnumArmorMaterial, par3, par4);
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("dendroidmask");
      RegistryHelper.setTexture(this, "mam:ItemDendroidMask");
   }

   public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type) {
      return "mam:textures/models/armor/dendroid_layer_1.png";
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.getItem() == MAMItems.dendroidroot;
   }

   public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
      if (!par2World.isRemote && ((EntityPlayer)par3Entity).inventory.armorItemInSlot(3) != null) {
         ItemStack stack = ((EntityPlayer)par3Entity).inventory.armorItemInSlot(3);
         Item item = stack.getItem();
         int minHealth = 5;
         if (stack.getMetadata() * -1 + stack.getMaxDamage() < 10) {
            minHealth = 8;
         }

         if (par3Entity.getAir() > 0
            && !par3Entity.isBurning()
            && ((EntityPlayer)par3Entity).getHealth() < minHealth
            && item != null
            && item instanceof ItemDendroidMask) {
            ((EntityPlayer)par3Entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3, 4));
         }
      }
   }
}
