package com.mysticwands.spells;

import org.bukkit.entity.Player;

public abstract class Spell {
    
    protected final String name;
    protected final int manaCost;
    protected final int cooldown; // in seconds
    
    public Spell(String name, int manaCost, int cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
    }
    
    public abstract boolean cast(Player player);
    
    public String getName() {
        return name;
    }
    
    public int getManaCost() {
        return manaCost;
    }
    
    public int getCooldown() {
        return cooldown;
    }
}