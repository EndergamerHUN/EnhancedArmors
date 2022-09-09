package io.github.endergamerhun.enhancedarmors.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class EventListener implements Listener {

    @EventHandler
    public static void onPotionEffectChant(EntityPotionEffectEvent e) {
        if (e.getEntity() instanceof Player) ArmorSwitchListener.checkArmor((Player) e.getEntity());
    }
}
