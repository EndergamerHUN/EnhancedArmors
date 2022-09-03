package io.github.endergamerhun.enhancedarmors.commands;

import io.github.endergamerhun.enhancedarmors.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompletion implements TabCompleter {

    private static final List<String> COMMANDS = Arrays.asList("give", "getid", "list");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(cmd.getName().equalsIgnoreCase("enhancedarmors"))) return null;
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1 -> StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
            case 2 -> {
                if (args[0].equalsIgnoreCase("give")) {
                    completions = Arrays.stream(ItemManager.getRegisteredSets()).toList();
                }

            }
        }
        return completions;
    }
}
