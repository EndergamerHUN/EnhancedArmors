package io.github.endergamerhun.enhancedarmors.events;

import io.github.endergamerhun.enhancedarmors.items.ItemManager;
import io.github.endergamerhun.enhancedarmors.items.RecipeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        for (String set: ItemManager.getRegisteredSets()) e.getPlayer().discoverRecipes(Arrays.stream(RecipeManager.getSetRecipes(set)).toList());
    }
}
