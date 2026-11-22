package com.electrifiedded.mam.entity;

import net.minecraft.world.World;

public class EntityJar extends EntityFairyJar {
   public EntityJar(World par1World) {
      super(par1World);
      this.setSize(0.6F, 0.82F);
   }

   public String getTexture() {
      return "/M&Mmod/Blocks/EmptyFairyJar.png";
   }
}
