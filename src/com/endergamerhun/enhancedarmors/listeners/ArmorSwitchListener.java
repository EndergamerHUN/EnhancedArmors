package com.endergamerhun.enhancedarmors.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorSwitchListener implements Listener {

    @EventHandler
    public static void onArmorChange(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        for (ItemStack i: p.getInventory().getArmorContents()) {
            i.clone();
        }

    }
}
