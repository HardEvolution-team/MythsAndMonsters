package com.electrifiedded.mam.items;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.registry.MAMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.Vec3d;

public class ItemMarrowSword extends ItemSword {
   public ItemMarrowSword() {
      super(MAMItems.EnumToolMaterialDendroid);
      this.maxStackSize = 1;
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("marrowsword");
      RegistryHelper.setTexture(this, "mam:ItemMarrowSword");
   }

   public void onUsingTick(ItemStack stack, net.minecraft.entity.EntityLivingBase player_, int count) {
      if (!(player_ instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)player_;
      if (count % 17 == 16 && player.getHealth() != player.getMaxHealth()) {
         Vec3d var5 = new Vec3d(
            (player.world.rand.nextFloat() - 0.5) * 0.3, -player.world.rand.nextFloat() * 0.6 - 0.3, 0.6
         );
         var5 = var5.rotatePitch(-player.rotationPitch * (float) Math.PI / 180.0F);
         var5 = var5.rotateYaw(-player.rotationYaw * (float) Math.PI / 180.0F);
         var5 = var5.add(player.posX, player.posY + player.getEyeHeight(), player.posZ);
         if (player.getFoodStats().getFoodLevel() > 0) {
            player.getFoodStats().addStats(-1, 0.0F);
            player.heal(2.0F);

            for (int repeat = 0; repeat > 5; repeat++) {
               LegacyParticles.spawn(player.world, "largesmoke", var5.x, var5.y, var5.z, 2.0, 2.0, 2.0);
            }

            LegacyWorld.playSoundAtEntity(player.world, player, "random.chestopen", 2.5F, 1.5F);
            LegacyWorld.playSoundAtEntity(player.world, player, "random.chestclosed", 2.5F, 1.5F);
         } else if (stack.getMetadata() * -1 + stack.getMaxDamage() > 3) {
            stack.damageItem(3, player);
            player.heal(2.0F);

            for (int repeat = 0; repeat > 5; repeat++) {
               LegacyParticles.spawn(player.world, "largesmoke", var5.x, var5.y, var5.z, 0.0, 0.0, 0.0);
            }

            LegacyWorld.playSoundAtEntity(player.world, player, "random.break", 1.4F, 0.1F);
         }
      }
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.RARE;
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.getItem() == Items.GOLD_INGOT;
   }
}
