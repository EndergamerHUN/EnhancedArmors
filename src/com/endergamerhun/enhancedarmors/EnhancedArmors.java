package com.endergamerhun.enhancedarmors;

import com.endergamerhun.enhancedarmors.listeners.ArmorSwitchListener;
import com.endergamerhun.enhancedarmors.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class EnhancedArmors extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ArmorSwitchListener(), this);
        Utils.log("Plugin loaded!");
    }

    @Override
    public void onDisable() {
        Utils.log("Plugin unloaded!");
    }
}