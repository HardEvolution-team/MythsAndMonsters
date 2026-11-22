package com.electrifiedded.mam.blocks;

import com.electrifiedded.mam.entity.EntityDendroid;
import com.electrifiedded.mam.entity.EntityDendroidElder;
import com.electrifiedded.mam.lib.MAMEntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityDendroidElderSpawner extends TileEntity implements ITickable {
   @Override
   public void update() {
      if (this.world.isRemote) return;
      AxisAlignedBB box = new AxisAlignedBB(this.pos).grow(30.0);
      if (!this.world.getEntitiesWithinAABB(EntityPlayer.class, box).isEmpty()) {
         this.spawn(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.world);
      }
   }

   public void spawn(int x, int y, int z, World world) {
      EntityDendroidElder dendroidelder = new EntityDendroidElder(world);
      dendroidelder.setPosition(x, y, z);
      MAMEntityUtil.spawnInWorld(world, dendroidelder);
      EntityDendroid dendroid1 = new EntityDendroid(world);
      dendroid1.setPosition(x, y, z - 1);
      MAMEntityUtil.spawnInWorld(world, dendroid1);
      EntityDendroid dendroid2 = new EntityDendroid(world);
      dendroid2.setPosition(x, y, z + 1);
      MAMEntityUtil.spawnInWorld(world, dendroid2);
      world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
   }
}
