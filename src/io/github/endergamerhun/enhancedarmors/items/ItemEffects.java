package io.github.endergamerhun.enhancedarmors.items;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemEffects {

    public static void applyEffect(Player p, String id) {
        switch (id) {
            case "miner" -> p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false, true));
            case "speed" -> p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4, false, false, false));
        }
    }
    public static void removeEffect(Player p, String id) {
        switch (id) {
            case "miner" -> p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            case "speed" -> p.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
