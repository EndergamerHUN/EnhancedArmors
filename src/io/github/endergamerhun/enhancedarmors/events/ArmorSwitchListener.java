package io.github.endergamerhun.enhancedarmors.events;

import io.github.endergamerhun.enhancedarmors.items.ItemEffects;
import io.github.endergamerhun.enhancedarmors.items.ItemManager;
import io.github.endergamerhun.enhancedarmors.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Hashtable;

public class ArmorSwitchListener implements Listener {

    public static void checkArmor(Player p, String preCheck) {
        final Hashtable<String, Integer> count = new Hashtable<>();
        final ItemStack[] armor = p.getInventory().getArmorContents().clone();

        if (preCheck != null) count.put(preCheck, 0);

        for (ItemStack item : armor) {
            if (item == null) continue;
            if (item.getType().isAir()) continue;
            if (!ItemManager.isSetPiece(item)) continue;

            String id = ItemManager.getItemId(item);
            if (!count.containsKey(id)) count.put(id, 1);
            else count.replace(id, count.get(id) + 1);
        }
        for (String id : count.keySet()) {
            if (count.get(id) < ItemManager.getSetSize(id)) {
                ItemEffects.removeEffect(p, id);
            } else {
                ItemEffects.applyEffect(p, id);
            }
        }
    }
    public static void checkArmor(Player p) {
        checkArmor(p, null);
    }

    @EventHandler
    public static void onArmorChange(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final Inventory inv = e.getClickedInventory();
        if (inv == null) return;
        if (!inv.getType().equals(InventoryType.PLAYER)) return;

        final ItemStack previousItem;
        if (e.getCurrentItem() != null) previousItem = e.getCurrentItem().clone();
        else previousItem = null;

        Util.waitFor(() -> {
            final Hashtable<String, Integer> count = new Hashtable<>();
            final ItemStack[] armor = p.getInventory().getArmorContents().clone();

            if (previousItem != null && !previousItem.getType().isAir() && ItemManager.isSetPiece(previousItem)) count.put(ItemManager.getItemId(previousItem), 0);

            for (ItemStack item : armor) {
                if (item == null) continue;
                if (!ItemManager.isSetPiece(item)) continue;

                String id = ItemManager.getItemId(item);
                if (!count.containsKey(id)) count.put(id, 1);
                else count.replace(id, count.get(id) + 1);
            }
            for (String id : count.keySet()) {
                if (count.get(id) < ItemManager.getSetSize(id)) {
                    ItemEffects.removeEffect(p, id);
                } else {
                    ItemEffects.applyEffect(p, id);
                }
            }
        }, 0.05);
    }
}
