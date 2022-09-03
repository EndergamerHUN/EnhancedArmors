package io.github.endergamerhun.enhancedarmors;

import io.github.endergamerhun.enhancedarmors.commands.EACommands;
import io.github.endergamerhun.enhancedarmors.commands.TabCompletion;
import io.github.endergamerhun.enhancedarmors.events.ArmorSwitchListener;
import io.github.endergamerhun.enhancedarmors.events.PlayerJoinListener;
import io.github.endergamerhun.enhancedarmors.events.PotionEffectChangeListener;
import io.github.endergamerhun.enhancedarmors.items.ItemManager;
import io.github.endergamerhun.enhancedarmors.utils.Util;
import org.bukkit.plugin.java.JavaPlugin;

public class EnhancedArmors extends JavaPlugin {

    public static EnhancedArmors getInstance() {
        return instance;
    }

    private static EnhancedArmors instance;

    @Override
    public void onEnable() {
        instance = this;
        ItemManager.init();
        getServer().getPluginManager().registerEvents(new ArmorSwitchListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PotionEffectChangeListener(), this);
        getCommand("enhancedarmors").setExecutor(new EACommands());
        getCommand("enhancedarmors").setTabCompleter(new TabCompletion());
        Util.log("Plugin loaded!");
    }

    @Override
    public void onDisable() {
        Util.log("Plugin unloaded!");
    }
}