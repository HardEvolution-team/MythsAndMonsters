package com.electrifiedded.mam.blocks;

import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityGravelMAM extends TileEntity implements ITickable {
   int tick = 100;

   @Override
   public void update() {
      this.tick--;
      if (this.tick == 0) {
         int x = this.pos.getX(), y = this.pos.getY(), z = this.pos.getZ();
         this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
         LegacyParticles.spawn(this.world, "spell", x, y + 1.4F, z, 0.0, 0.0, 0.0);
         LegacyParticles.spawn(this.world, "spell", x + 0.4, y + 1.4F, z + 0.4, 0.0, 0.0, 0.0);
         LegacyParticles.spawn(this.world, "spell", x - 0.4, y + 1.4F, z - 0.4, 0.0, 0.0, 0.0);
      }
   }
}
