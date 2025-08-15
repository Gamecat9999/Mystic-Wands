package com.mysticwands.spells;

import com.mysticwands.MysticWands;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HealSpell extends Spell {
    
    public HealSpell() {
        super("Heal", 15, 8);
    }
    
    @Override
    public boolean cast(Player player) {
        if (!MysticWands.getInstance().getManaManager().consumeMana(player, manaCost)) {
            player.sendMessage(ChatColor.RED + "Not enough mana! Need " + manaCost + " mana.");
            return false;
        }
        
        double maxHealth = player.getMaxHealth();
        double currentHealth = player.getHealth();
        double healAmount = Math.min(6.0, maxHealth - currentHealth);
        
        if (healAmount <= 0) {
            player.sendMessage(ChatColor.YELLOW + "You are already at full health!");
            return false;
        }
        
        player.setHealth(currentHealth + healAmount);
        player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0, 2, 0), 10, 0.5, 0.5, 0.5, 0.1);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
        player.sendMessage(ChatColor.GREEN + "Healed for " + (int) healAmount + " hearts!");
        
        return true;
    }
}