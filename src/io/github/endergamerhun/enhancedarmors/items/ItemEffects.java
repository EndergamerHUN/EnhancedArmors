package io.github.endergamerhun.enhancedarmors.items;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemEffects {

    public static void applyEffect(Player p, String id) {
        switch (id) {
            case "miner" -> p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
            case "speed" -> p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4, false, false));
            case "magma" -> p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 9, false, false));
        }
    }
    public static void removeEffect(Player p, String id) {
        switch (id) {
            case "miner" -> p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            case "speed" -> p.removePotionEffect(PotionEffectType.SPEED);
            case "magma" -> p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }
}
