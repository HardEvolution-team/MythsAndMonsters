package com.electrifiedded.mam.gui;

import com.electrifiedded.mam.MythsAndMonstersMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GUIConfig extends GuiConfig {
   public GUIConfig(GuiScreen parent) {
      super(parent, new ConfigElement(MythsAndMonstersMod.configFile.getCategory("general")).getChildElements(), "mam", false, false,
         GuiConfig.getAbridgedConfigPath(MythsAndMonstersMod.configFile.toString()));
   }
}
