package com.electrifiedded.mam.proxy;

import com.electrifiedded.archaiclib.legacy.client.LegacyRenderLiving;
import com.electrifiedded.archaiclib.v1710.api.client.ArchaicMinecraftForgeClient;
import com.electrifiedded.mam.blocks.*;
import com.electrifiedded.mam.entity.*;
import com.electrifiedded.mam.models.*;
import com.electrifiedded.mam.projectile.*;
import com.electrifiedded.mam.registry.MAMItems;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
   private static final Random RAND = new com.electrifiedded.archaiclib.util.FastRandom();

   private static ResourceLocation tex(String name) {
      return new ResourceLocation("mam", "textures/entity/" + name.toLowerCase() + ".png");
   }

   @Override
   public void preInit() {
      LegacyRenderLiving.register(EntityFairy.class, ModelFairy::new, 0.5F, "mam:textures/entity/Fairy.png");
      LegacyRenderLiving.register(EntityCetus.class, ModelCetus::new, 0.5F, "mam:textures/entity/Cetus.png");
      LegacyRenderLiving.register(EntityHillGiant.class, ModelHillGiant::new, 0.5F, "mam:textures/entity/HillGiant.png");
      LegacyRenderLiving.register(EntityJackalopes.class, ModelJackalopes::new, 0.5F, "mam:textures/entity/Jackalope.png");
      LegacyRenderLiving.register(EntityJackalopesOld.class, ModelJackalopeOld::new, 0.5F, "mam:textures/entity/Old_Jackalope.png");
      LegacyRenderLiving.register(EntityFrostJakalopes.class, ModelJackalopes::new, 0.5F, "mam:textures/entity/Frost_Jackalope.png");
      LegacyRenderLiving.register(EntityFrostJakalopesOld.class, ModelJackalopeOld::new, 0.5F, "mam:textures/entity/FrostJackalope.png");
      LegacyRenderLiving.register(EntityDendroidElder.class, ModelDendroidElder::new, 0.5F, "mam:textures/entity/DendroidElder.png");
      LegacyRenderLiving.register(EntityDendroid.class, ModelDendroid::new, 0.5F, "mam:textures/entity/Dendroid.png");
      LegacyRenderLiving.register(EntityGoodDendroid.class, ModelDendroid::new, 0.5F, "mam:textures/entity/DendroidGood.png");
      LegacyRenderLiving.register(EntityDendroidRoot.class, ModelDendroidRoot::new, 0.5F, "mam:textures/entity/DendroidRoot.png");
      LegacyRenderLiving.register(EntityDendroidSeer.class, ModelDendroidSeer::new, 0.5F, "mam:textures/entity/DendroidSeerGood.png");
      LegacyRenderLiving.register(EntityDendroidSentinel.class, ModelDendroidSeer::new, 0.5F, "mam:textures/entity/DendroidSeerBad.png");
      LegacyRenderLiving.register(EntitySandWyrm.class, ModelSandWyrm::new, 0.5F, "mam:textures/entity/SandWyrm.png");
      RenderingRegistry.registerEntityRenderingHandler(EntityGoldenRam.class, m -> new RenderGoldenRam(m, new ModelGoldenRam2(), new ModelGoldenRam1(), 0.7F));
      // the 1.7 renderer picked one of the star textures at random every frame (twinkle effect)
      LegacyRenderLiving.register(EntityUrsaBlack.class, ModelUrsa::new, 0.5F, e -> tex(RAND.nextInt(4) == 0 ? "UrsaBlack" : "UrsaBlack" + (1 + RAND.nextInt(3))));
      LegacyRenderLiving.register(EntityUrsaWhite.class, ModelUrsa::new, 0.5F, e -> tex(RAND.nextInt(4) == 0 ? "UrsaWhite" : "UrsaWhite" + (1 + RAND.nextInt(3))));
      LegacyRenderLiving.register(EntityCoreRed.class, ModelCore::new, 0.5F, "mam:textures/entity/UrsaBlack1.png");
      LegacyRenderLiving.register(EntityCoreBlue.class, ModelCore::new, 0.5F, "mam:textures/entity/UrsaWhitePeaceful.png");
      LegacyRenderLiving.register(EntityWolpertinger.class, ModelWolpertinger::new, 0.5F, "mam:textures/entity/NewWolpertinger.png");
      LegacyRenderLiving.register(EntityWolpertingerOld.class, ModelWolpertingerOld::new, 0.5F, "mam:textures/entity/Wolpertinger.png");
      LegacyRenderLiving.register(EntityHunter.class, ModelBiped::new, 0.5F, "mam:textures/entity/Hunter1.png");
      LegacyRenderLiving.register(EntityEliteHunter.class, ModelBiped::new, 0.5F, "mam:textures/entity/HunterElite.png");
      LegacyRenderLiving.register(EntityKitsune.class, ModelKitsune::new, 0.5F, "mam:textures/entity/Kitsune.png");
      RenderingRegistry.registerEntityRenderingHandler(EntityKitsuneMinion.class, m -> new LegacyRenderLiving<EntityKitsuneMinion>(m, new ModelKitsuneMinion(), 0.3F, e -> tex("Kitsune")) {
         @Override
         public void doRender(EntityKitsuneMinion e, double x, double y, double z, float yaw, float partial) {
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(0.8F, 0.8F, 0.8F, 0.5F);
            super.doRender(e, x, y, z, yaw, partial);
            GlStateManager.disableBlend();
         }
      });
      LegacyRenderLiving.register(EntityDropBear.class, ModelDropBear::new, 0.5F, "mam:textures/entity/Dropbear.png");
      LegacyRenderLiving.register(EntityClayGolem.class, ModelClayGolem::new, 0.5F, "mam:textures/entity/ClayGolem.png");
      LegacyRenderLiving.register(EntityClayMinion.class, ModelClayMinion::new, 0.5F, "mam:textures/entity/ClayGolem.png");
      RenderingRegistry.registerEntityRenderingHandler(EntityStick.class, m -> new RenderSnowball<>(m, Items.STICK, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EntityDust.class, m -> new RenderSnowball<>(m, MAMItems.redstardust, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EntityDust2.class, m -> new RenderSnowball<>(m, MAMItems.bluestardust, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EntityDendroidChakram.class, m -> new RenderSnowball<>(m, MAMItems.dendroidchakramprojectile, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EntityMusic.class, m -> new RenderSnowball<>(m, MAMItems.goldenlyre, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EntityGoldArrow.class, m -> new RenderArrow<EntityGoldArrow>(m) {
         @Override
         protected ResourceLocation getEntityTexture(EntityGoldArrow e) {
            return tex("GoldArrow");
         }
      });
   }

   private static void held(Item item, RenderItem r) {
      ArchaicMinecraftForgeClient.registerHeldItemRenderer(item, r, item.getRegistryName() + "/meta_0");
   }

   @Override
   public void registerRenderThings() {
      held(MAMItems.dendroidroot, new RenderItem(new ModelDendroidRoot(), "mam:textures/entity/dendroidroot.png"));
      held(MAMItems.depthcrystal, new RenderItem(new ModelDepthCrystal(), "mam:textures/items/depthcrystal.png"));
      held(MAMItems.tusklance, new RenderItem(new ModelTuskLance(), "mam:textures/items/heldtusklance.png"));
      held(MAMItems.redcore, new RenderItem(new ModelCore(), "mam:textures/entity/ursablack1.png"));
      held(MAMItems.bluecore, new RenderItem(new ModelCore(), "mam:textures/entity/ursawhitepeaceful.png"));
      held(MAMItems.jar, new RenderItem(new ModelJar(), "mam:textures/blocks/emptyfairyjar.png"));
      held(MAMItems.angryfairyjar, new RenderItem(new ModelFairyJar(), "mam:textures/blocks/fairyjar.png"));
      held(MAMItems.happyfairyjar, new RenderItem(new ModelFairyJar(), "mam:textures/blocks/fairyjar.png"));
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJar.class, new RenderJar());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAngryFairyJar.class, new RenderJarAngryFairy());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHappyFairyJar.class, new RenderJarHappyFairy());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDepthCrystal.class, new RenderDepthCrystal());
   }
}
