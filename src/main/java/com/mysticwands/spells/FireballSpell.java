package com.mysticwands.spells;

import com.mysticwands.MysticWands;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class FireballSpell extends Spell {
    
    public FireballSpell() {
        super("Fireball", 20, 3);
    }
    
    @Override
    public boolean cast(Player player) {
        if (!MysticWands.getInstance().getManaManager().consumeMana(player, manaCost)) {
            player.sendMessage(ChatColor.RED + "Not enough mana! Need " + manaCost + " mana.");
            return false;
        }
        
        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setYield(2.0f);
        
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);
        player.sendMessage(ChatColor.RED + "Fireball launched!");
        
        return true;
    }
}