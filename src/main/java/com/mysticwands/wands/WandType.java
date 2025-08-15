package com.mysticwands.wands;

public enum WandType {
    FIRE("Fire Wand", "fireball", 20),
    LIGHTNING("Lightning Wand", "lightning", 25),
    TELEPORT("Teleport Wand", "teleport", 30),
    HEALING("Healing Wand", "heal", 15),
    ICE("Ice Wand", "freeze", 20),
    DESTRUCTION("Destruction Wand", "explosion", 40);
    
    private final String displayName;
    private final String spell;
    private final int manaCost;
    
    WandType(String displayName, String spell, int manaCost) {
        this.displayName = displayName;
        this.spell = spell;
        this.manaCost = manaCost;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getSpell() {
        return spell;
    }
    
    public int getManaCost() {
        return manaCost;
    }
}