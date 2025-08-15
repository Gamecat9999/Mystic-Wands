package com.mysticwands.spells;

import com.mysticwands.MysticWands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TeleportSpell extends Spell {
    
    public TeleportSpell() {
        super("Teleport", 30, 10);
    }
    
    @Override
    public boolean cast(Player player) {
        if (!MysticWands.getInstance().getManaManager().consumeMana(player, manaCost)) {
            player.sendMessage(ChatColor.RED + "Not enough mana! Need " + manaCost + " mana.");
            return false;
        }
        
        Block targetBlock = player.getTargetBlock(null, 100);
        Location teleportLocation = targetBlock.getLocation().add(0.5, 1, 0.5);
        
        // Spawn particles at current location
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5, 0.1);
        
        // Teleport player
        player.teleport(teleportLocation);
        
        // Spawn particles at new location
        player.getWorld().spawnParticle(Particle.PORTAL, teleportLocation, 30, 0.5, 0.5, 0.5, 0.1);
        
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Teleported!");
        
        return true;
    }
}