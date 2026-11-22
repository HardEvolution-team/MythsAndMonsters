package com.electrifiedded.mam.lib;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class MAMEntityUtil {
   public static void spawnInWorld(World world, Entity entity) {
      if (!world.isRemote) {
         world.spawnEntity(entity);
      }
   }
}
