package com.endergamerhun.enhancedarmors.utils;

import org.bukkit.Bukkit;

public class Utils {

    final static String PREFIX = "§8[§bEnhanced§6Armors§8]§f ";

    public static void log(String format, Object... objects) {
        String log = String.format(format, objects);
        Bukkit.getConsoleSender().sendMessage(PREFIX + log);
    }
}
