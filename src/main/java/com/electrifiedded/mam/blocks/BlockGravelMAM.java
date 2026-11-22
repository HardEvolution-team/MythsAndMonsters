package com.electrifiedded.mam.blocks;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Temporary gravel (thrown by the Sand Wyrm etc.): falls like gravel and vanishes after 100 ticks. */
public class BlockGravelMAM extends BlockFalling implements IArchaicRenderBlock {
   @SideOnly(Side.CLIENT)
   private IIcon icon;

   public BlockGravelMAM() {
      super();
      this.setTranslationKey("mamgravel");
      this.setHardness(0.6F);
      this.setSoundType(SoundType.GROUND);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random random, int fortune) {
      if (fortune > 3) {
         fortune = 3;
      }
      return random.nextInt(10 - fortune * 3) == 0 ? Items.FLINT : Item.getItemFromBlock(Blocks.GRAVEL);
   }

   @Override
   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   @Override
   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileEntityGravelMAM();
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.icon = reg.registerIcon("gravel");
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return this.icon;
   }

   @Override
   public int getArchaicRenderKey(IBlockState state) {
      return 0;
   }
}
