package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.entity.EntityDendroid;
import com.electrifiedded.mam.entity.EntityDendroidElder;
import com.electrifiedded.mam.entity.EntityDendroidRoot;
import com.electrifiedded.mam.entity.EntityDendroidSeer;
import com.electrifiedded.mam.entity.EntityDendroidSentinel;
import com.electrifiedded.mam.entity.EntityGoodDendroid;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemDendroidBlade extends ItemSword {
   int count = 400;

   public ItemDendroidBlade() {
      super(MAMItems.EnumToolMaterialDendroidBlade);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("dendroidblade");
      RegistryHelper.setTexture(this, "mam:DendroidBlade");
   }

   @Override
   public net.minecraft.util.ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, net.minecraft.util.EnumHand hand) {
      ItemStack itemStackIn = player.getHeldItem(hand);
      if (!player.isSneaking() && !player.world.isRemote && this.count <= 0) {
         EntityGoodDendroid f = new EntityGoodDendroid(worldIn);
         f.setPosition(player.posX, player.posY, player.posZ);
         MAMEntityUtil.spawnInWorld(worldIn, f);
         itemStackIn.damageItem(10, player);
         this.count = 400;
      }

      return new net.minecraft.util.ActionResult<>(net.minecraft.util.EnumActionResult.SUCCESS, itemStackIn);
   }

   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
      this.count--;
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
      return EnumRarity.UNCOMMON;
   }
}
