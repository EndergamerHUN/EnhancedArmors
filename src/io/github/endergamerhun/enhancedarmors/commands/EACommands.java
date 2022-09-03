package io.github.endergamerhun.enhancedarmors.commands;

import io.github.endergamerhun.enhancedarmors.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EACommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("enhancedarmors")) return true;
        if (args.length < 1) {
            sender.sendMessage("§cPlease specify a subcommand!");
            return true;
        }
        Player p = null;
        if (sender instanceof Player) p = (Player) sender;

        switch (args[0].toLowerCase()) {

            // Gives a whole set to a certain player
            case "give" -> {
                if (assertPlayer(sender)) break;
                assert p != null;
                if (args.length < 2) {
                    p.sendMessage("§cYou need to provide a set id to get!");
                    break;
                }

                if (!ItemManager.setExists(args[1])) {
                    p.sendMessage("§cCould not find set with id '" + args[1] + "'.");
                    break;
                }
                Inventory inv = p.getInventory();
                for (ItemStack item : ItemManager.getSetItems(args[1])) inv.addItem(item);
                p.sendMessage("§aItems added to your inventory!");
            }

            // Get the id of the held item!
            case "getid" -> {
                if (assertPlayer(sender)) break;
                assert p != null;

                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.getType().isAir()) {
                    p.sendMessage("§cYou must be holding something!");
                } else if (ItemManager.isSetPiece(item)) {
                    String s = ItemManager.getItemId(item);
                    p.sendMessage("§aThe id of the item is §e" + s + "§a.");
                } else {
                    p.sendMessage("§cThis is not an EnhancedArmors item!");
                }
            }

            // Lists every existing set
            case "list" -> {
                sender.sendMessage("§aHere is the list of every set:");
                for (String id : ItemManager.getRegisteredSets()) {
                    sender.sendMessage("§8- §e" + id);
                }
            }

            default -> {
                sender.sendMessage("§cThe subcommand '" + args[0] + "' doesn't exist!");
            }
        }
        return true;
    }
    private static boolean assertPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis subcommand can only be used by players!");
            return true;
        }
        return false;
    }
}