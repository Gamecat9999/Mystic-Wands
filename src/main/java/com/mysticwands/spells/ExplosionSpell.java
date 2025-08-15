package com.mysticwands.spells;

import com.mysticwands.MysticWands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ExplosionSpell extends Spell {
    
    public ExplosionSpell() {
        super("Explosion", 40, 15);
    }
    
    @Override
    public boolean cast(Player player) {
        if (!MysticWands.getInstance().getManaManager().consumeMana(player, manaCost)) {
            player.sendMessage(ChatColor.RED + "Not enough mana! Need " + manaCost + " mana.");
            return false;
        }
        
        Block targetBlock = player.getTargetBlock(null, 50);
        Location explosionLocation = targetBlock.getLocation().add(0.5, 0.5, 0.5);
        
        // Create explosion with custom power
        player.getWorld().createExplosion(explosionLocation, 4.0f, false, true);
        
        // Spawn additional particle effects
        player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, explosionLocation, 5, 1, 1, 1, 0.1);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
        player.sendMessage(ChatColor.DARK_RED + "EXPLOSION!");
        
        return true;
    }
}