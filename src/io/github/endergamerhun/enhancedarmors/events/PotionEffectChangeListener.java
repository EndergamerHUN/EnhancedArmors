package io.github.endergamerhun.enhancedarmors.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class PotionEffectChangeListener implements Listener {

    @EventHandler
    public static void onPotionEffectChant(EntityPotionEffectEvent e) {
        switch (e.getAction()) {
            case CLEARED, REMOVED, CHANGED -> {
                if (e.getOldEffect().getDuration() > 99999) e.setCancelled(true);
            }
        }
    }
}
