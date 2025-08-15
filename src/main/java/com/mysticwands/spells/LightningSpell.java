package com.mysticwands.spells;

import com.mysticwands.MysticWands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LightningSpell extends Spell {
    
    public LightningSpell() {
        super("Lightning", 25, 5);
    }
    
    @Override
    public boolean cast(Player player) {
        if (!MysticWands.getInstance().getManaManager().consumeMana(player, manaCost)) {
            player.sendMessage(ChatColor.RED + "Not enough mana! Need " + manaCost + " mana.");
            return false;
        }
        
        Block targetBlock = player.getTargetBlock(null, 50);
        Location strikeLocation = targetBlock.getLocation().add(0, 1, 0);
        
        player.getWorld().strikeLightning(strikeLocation);
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
        player.sendMessage(ChatColor.YELLOW + "Lightning strikes!");
        
        return true;
    }
}