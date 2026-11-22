package com.electrifiedded.mam.items;
import net.minecraft.util.EnumHand;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemTuskLance extends ItemSword {
   public ItemTuskLance() {
      super(MAMItems.EnumToolMaterialDendroid);
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("tusklance");
      RegistryHelper.setTexture(this, "mam:ItemTuskLance");
   }

   public float getDestroySpeed(ItemStack par1ItemStack, net.minecraft.block.state.IBlockState st) {
      Block par2Block = st.getBlock();
      return par2Block == Blocks.SAND ? 12.0F : (par2Block == Blocks.GRAVEL ? 12.0F : 1.5F);
   }

   public boolean hitEntity(ItemStack par1ItemStack, net.minecraft.entity.EntityLivingBase par2EntityLiving, net.minecraft.entity.EntityLivingBase par3EntityLiving) {
      par1ItemStack.damageItem(1, par3EntityLiving);
      return true;
   }

   public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, net.minecraft.block.state.IBlockState st, net.minecraft.util.math.BlockPos bp, net.minecraft.entity.EntityLivingBase par7EntityLiving) {
      if (st.getBlock() != Blocks.AIR) {
         par1ItemStack.damageItem(2, par7EntityLiving);
      }

      return true;
   }

   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
      return EnumAction.BOW;
   }

   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
      return 295000;
   }

   @Override
   public net.minecraft.util.ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, net.minecraft.util.EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      if (par3EntityPlayer.onGround && !par3EntityPlayer.isInWater()) {
         par3EntityPlayer.setActiveHand(EnumHand.MAIN_HAND);
         par3EntityPlayer.addVelocity(
            -(-MathHelper.sin(par3EntityPlayer.rotationYaw * (float) Math.PI / 180.0F) * 0.5F),
            0.1,
            -(MathHelper.cos(par3EntityPlayer.rotationYaw * (float) Math.PI / 180.0F) * 0.5F)
         );
      }

      return new net.minecraft.util.ActionResult<>(net.minecraft.util.EnumActionResult.SUCCESS, par1ItemStack);
   }

   public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, net.minecraft.entity.EntityLivingBase par3EntityPlayer_, int par4) {
      if (!(par3EntityPlayer_ instanceof EntityPlayer)) return;
      EntityPlayer par3EntityPlayer = (EntityPlayer)par3EntityPlayer_;
      ItemStack legs = par3EntityPlayer.inventory.armorInventory.get(1);
      ItemStack chest = par3EntityPlayer.inventory.armorInventory.get(2);
      boolean maxed = false;
      int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
      float var7 = var6 / 20.0F;
      var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
      if (!(var7 < 0.1)) {
         if (var7 > 2.1F) {
            var7 = 2.1F;
            maxed = true;
         }

         if (par3EntityPlayer.onGround && !par3EntityPlayer.isInWater()) {
            par3EntityPlayer.setSprinting(true);
            if (maxed) {
               par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 1));
            }

            if (var7 >= 1.5) {
               if (!legs.isEmpty()
                  && !chest.isEmpty()
                  && legs.getItem() == MythsAndMonstersMod.scalelegging
                  && chest.getItem() == MythsAndMonstersMod.scalechest) {
                  par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 1));
               }

               par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 2));
            }

            par3EntityPlayer.addVelocity(
               -MathHelper.sin(par3EntityPlayer.rotationYaw * (float) Math.PI / 180.0F) * 2.0F * var7,
               0.1,
               MathHelper.cos(par3EntityPlayer.rotationYaw * (float) Math.PI / 180.0F) * 2.0F * var7
            );
         }
      }
   }

   public boolean canHarvestBlock(net.minecraft.block.state.IBlockState st) {
      Block par1Block = st.getBlock();
      return par1Block == Blocks.SAND ? true : par1Block == Blocks.GRAVEL;
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.getItem() == MAMItems.tusk;
   }
}
