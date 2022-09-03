package io.github.endergamerhun.enhancedarmors.utils;

import io.github.endergamerhun.enhancedarmors.EnhancedArmors;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Util {

    final static String PREFIX = "§8[§bEnhanced§6Armors§8]§f ";

    public static void log(String format, Object... objects) {
        String log = String.format(format, objects);
        Bukkit.getConsoleSender().sendMessage(PREFIX + log);
    }

    public static String toTitleCase(String input) {
        StringBuilder output = new StringBuilder(input.length());

        boolean nextUppercase = true;
        for (char c: input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextUppercase = true;
            } else if (nextUppercase) {
                c = Character.toTitleCase(c);
                nextUppercase = false;
            }
            output.append(c);
        }
        return output.toString();
    }

    public static void waitFor(Runnable action, double delay) {
        if (delay <= 0) {
            action.run();
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                action.run();
            }
        }.runTaskLater(EnhancedArmors.getInstance(), (long)(delay*20));
    }
}
