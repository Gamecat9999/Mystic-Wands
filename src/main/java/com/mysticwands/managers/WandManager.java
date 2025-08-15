package com.mysticwands.managers;

import com.mysticwands.wands.Wand;
import com.mysticwands.wands.WandType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class WandManager {
    
    public ItemStack createWand(WandType type) {
        ItemStack wand = new ItemStack(Material.STICK);
        ItemMeta meta = wand.getItemMeta();
        
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + type.getDisplayName());
            
            List<String> lore = Arrays.asList(
                ChatColor.GRAY + "Type: " + ChatColor.WHITE + type.name(),
                ChatColor.GRAY + "Spell: " + ChatColor.AQUA + type.getSpell(),
                ChatColor.GRAY + "Mana Cost: " + ChatColor.BLUE + type.getManaCost(),
                "",
                ChatColor.YELLOW + "Right-click to cast!"
            );
            meta.setLore(lore);
            
            // Add custom model data for resource packs
            meta.setCustomModelData(type.ordinal() + 1);
            
            wand.setItemMeta(meta);
        }
        
        return wand;
    }
    
    public boolean isWand(ItemStack item) {
        if (item == null || item.getType() != Material.STICK) {
            return false;
        }
        
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.hasCustomModelData();
    }
    
    public WandType getWandType(ItemStack item) {
        if (!isWand(item)) {
            return null;
        }
        
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasCustomModelData()) {
            int modelData = meta.getCustomModelData();
            WandType[] types = WandType.values();
            if (modelData > 0 && modelData <= types.length) {
                return types[modelData - 1];
            }
        }
        
        return null;
    }
    
    public void giveWand(Player player, WandType type) {
        ItemStack wand = createWand(type);
        player.getInventory().addItem(wand);
        player.sendMessage(ChatColor.GREEN + "You have received a " + type.getDisplayName() + "!");
    }
}