package com.electrifiedded.mam.items;
import net.minecraft.inventory.EntityEquipmentSlot;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ItemSandLegging extends ItemArmor {
   public ItemSandLegging(ArmorMaterial enumArmorMaterial, int renderIndex, EntityEquipmentSlot armorType) {
      super(enumArmorMaterial, renderIndex, armorType);
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("sandlegging");
      RegistryHelper.setTexture(this, "mam:ItemSandTassets");
   }

   public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type) {
      return "mam:textures/models/armor/sand_layer_2.png";
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.getItem() == MAMItems.scaleleather;
   }
}
