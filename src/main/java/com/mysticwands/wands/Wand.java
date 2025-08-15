package com.mysticwands.wands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Wand {
    
    private final WandType type;
    private final ItemStack item;
    
    public Wand(WandType type, ItemStack item) {
        this.type = type;
        this.item = item;
    }
    
    public WandType getType() {
        return type;
    }
    
    public ItemStack getItem() {
        return item;
    }
    
    public boolean use(Player player) {
        // This method would handle the wand usage logic
        // For now, it's a placeholder that always returns true
        return true;
    }
}