package com.mysticwands.spells;

import com.mysticwands.MysticWands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeSpell extends Spell {
    
    public FreezeSpell() {
        super("Freeze", 20, 6);
    }
    
    @Override
    public boolean cast(Player player) {
        if (!MysticWands.getInstance().getManaManager().consumeMana(player, manaCost)) {
            player.sendMessage(ChatColor.RED + "Not enough mana! Need " + manaCost + " mana.");
            return false;
        }
        
        Location centerLocation = player.getTargetBlock(null, 30).getLocation();
        
        // Create ice blocks in a small area
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                Location loc = centerLocation.clone().add(x, 0, z);
                Block block = loc.getBlock();
                if (block.getType() == Material.WATER) {
                    block.setType(Material.ICE);
                } else if (block.getType() == Material.AIR) {
                    if (loc.clone().add(0, -1, 0).getBlock().getType().isSolid()) {
                        block.setType(Material.SNOW);
                    }
                }
            }
        }
        
        // Freeze nearby entities
        for (Entity entity : centerLocation.getWorld().getNearbyEntities(centerLocation, 5, 5, 5)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 3));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 3));
            }
        }
        
        // Spawn snow particles
        player.getWorld().spawnParticle(Particle.SNOWFLAKE, centerLocation.add(0, 1, 0), 50, 3, 3, 3, 0.1);
        player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 0.5f);
        player.sendMessage(ChatColor.AQUA + "Freeze spell cast!");
        
        return true;
    }
}