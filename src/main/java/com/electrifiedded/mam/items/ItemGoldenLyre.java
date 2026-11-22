package com.electrifiedded.mam.items;
import com.electrifiedded.archaiclib.legacy.item.LegacyItem;
import com.electrifiedded.mam.lib.RegistryHelper;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mam.MythsAndMonstersMod;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import com.electrifiedded.mam.projectile.EntityMusic;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemGoldenLyre extends LegacyItem {
   Random rand = new Random();
   int tick = 0;

   public ItemGoldenLyre() {
      this.maxStackSize = 1;
      this.setMaxDamage(800);
      this.setCreativeTab(MythsAndMonstersMod.tabMythsAndMonsters);
      this.setTranslationKey("goldenlyre");
      RegistryHelper.setTexture(this, "mam:ItemGoldLyre");
   }

   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.EPIC;
   }

   public boolean hasEffect(ItemStack par1ItemStack) {
      return true;
   }

   public ItemStack onItemRightClickLegacy(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      this.tick++;
      Vec3d look = par3EntityPlayer.getLookVec();
      EntityMusic fireball = new EntityMusic(par2World, par3EntityPlayer, 1.0, 1.0, 1.0);
      fireball.setPosition(
         par3EntityPlayer.posX + look.x * 1.0,
         par3EntityPlayer.posY + look.y * 1.0 + par3EntityPlayer.getEyeHeight(),
         par3EntityPlayer.posZ + look.z * 1.0
      );
      fireball.accelerationX = look.x * 0.1;
      fireball.accelerationY = look.y * 0.1;
      fireball.accelerationZ = look.z * 0.1;
      MAMEntityUtil.spawnInWorld(par2World, fireball);
      par1ItemStack.damageItem(1, par3EntityPlayer);
      Vec3d var4 = new Vec3d((this.rand.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
      var4 = var4.rotatePitch(-par3EntityPlayer.rotationPitch * (float) Math.PI / 180.0F);
      var4 = var4.rotateYaw(-par3EntityPlayer.rotationYaw * (float) Math.PI / 180.0F);
      Vec3d var5 = new Vec3d((this.rand.nextFloat() - 0.5) * 0.3, -this.rand.nextFloat() * 0.6 - 0.3, 0.6);
      var5 = var5.rotatePitch(-par3EntityPlayer.rotationPitch * (float) Math.PI / 180.0F);
      var5 = var5.rotateYaw(-par3EntityPlayer.rotationYaw * (float) Math.PI / 180.0F);
      var5 = var5.add(par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight(), par3EntityPlayer.posZ);
      LegacyParticles.spawn(par2World, 
         "note", var5.x, var5.y, var5.z, var4.x, var4.y + 0.05, var4.z
      );
      LegacyWorld.playSoundEffect(par2World, 
         par3EntityPlayer.posX,
         par3EntityPlayer.posY,
         par3EntityPlayer.posZ,
         "note." + "harp",
         3.0F,
         par2World.rand.nextFloat()
      );
      LegacyWorld.playSoundAtEntity(par2World, par3EntityPlayer, "note.harp", 1.0F, 1.0F);
      return par1ItemStack;
   }
}
