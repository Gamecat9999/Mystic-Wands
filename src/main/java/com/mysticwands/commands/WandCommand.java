package com.mysticwands.commands;

import com.mysticwands.MysticWands;
import com.mysticwands.wands.WandType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WandCommand implements CommandExecutor, TabCompleter {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (!player.hasPermission("mysticwands.give")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        
        if (args.length == 0) {
            showHelp(player);
            return true;
        }
        
        switch (args[0].toLowerCase()) {
            case "give":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usage: /wand give <type>");
                    return true;
                }
                giveWand(player, args[1]);
                break;
                
            case "list":
                listWands(player);
                break;
                
            case "mana":
                showMana(player);
                break;
                
            default:
                showHelp(player);
                break;
        }
        
        return true;
    }
    
    private void giveWand(Player player, String wandType) {
        try {
            WandType type = WandType.valueOf(wandType.toUpperCase());
            MysticWands.getInstance().getWandManager().giveWand(player, type);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid wand type! Use /wand list to see available wands.");
        }
    }
    
    private void listWands(Player player) {
        player.sendMessage(ChatColor.GOLD + "=== Available Wands ===");
        for (WandType type : WandType.values()) {
            player.sendMessage(ChatColor.YELLOW + "- " + type.name().toLowerCase() + 
                             ChatColor.GRAY + " (Spell: " + type.getSpell() + 
                             ", Mana: " + type.getManaCost() + ")");
        }
    }
    
    private void showMana(Player player) {
        int currentMana = MysticWands.getInstance().getManaManager().getMana(player);
        int maxMana = MysticWands.getInstance().getManaManager().getMaxMana();
        player.sendMessage(ChatColor.BLUE + "Mana: " + currentMana + "/" + maxMana);
    }
    
    private void showHelp(Player player) {
        player.sendMessage(ChatColor.GOLD + "=== Mystic Wands Commands ===");
        player.sendMessage(ChatColor.YELLOW + "/wand give <type>" + ChatColor.GRAY + " - Give yourself a wand");
        player.sendMessage(ChatColor.YELLOW + "/wand list" + ChatColor.GRAY + " - List available wands");
        player.sendMessage(ChatColor.YELLOW + "/wand mana" + ChatColor.GRAY + " - Check your mana");
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            completions = Arrays.asList("give", "list", "mana");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            for (WandType type : WandType.values()) {
                completions.add(type.name().toLowerCase());
            }
        }
        
        return completions;
    }
}