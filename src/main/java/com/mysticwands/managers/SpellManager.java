package com.mysticwands.managers;

import com.mysticwands.spells.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SpellManager {
    
    private final Map<String, Spell> spells = new HashMap<>();
    
    public SpellManager() {
        registerSpells();
    }
    
    private void registerSpells() {
        spells.put("fireball", new FireballSpell());
        spells.put("lightning", new LightningSpell());
        spells.put("teleport", new TeleportSpell());
        spells.put("heal", new HealSpell());
        spells.put("freeze", new FreezeSpell());
        spells.put("explosion", new ExplosionSpell());
    }
    
    public Spell getSpell(String name) {
        return spells.get(name.toLowerCase());
    }
    
    public boolean castSpell(Player player, String spellName) {
        Spell spell = getSpell(spellName);
        if (spell != null) {
            return spell.cast(player);
        }
        return false;
    }
    
    public Map<String, Spell> getAllSpells() {
        return new HashMap<>(spells);
    }
}