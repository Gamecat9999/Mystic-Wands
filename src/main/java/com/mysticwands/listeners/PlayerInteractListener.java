package com.mysticwands.listeners;

import com.mysticwands.MysticWands;
import com.mysticwands.managers.WandManager;
import com.mysticwands.wands.WandType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerInteractListener implements Listener {
    
    private final Map<UUID, Long> lastCastTime = new HashMap<>();
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        WandManager wandManager = MysticWands.getInstance().getWandManager();
        
        if (!wandManager.isWand(item)) {
            return;
        }
        
        event.setCancelled(true);
        
        WandType wandType = wandManager.getWandType(item);
        if (wandType == null) {
            return;
        }
        
        // Check cooldown
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        long lastCast = lastCastTime.getOrDefault(playerId, 0L);
        long cooldownTime = 1000; // 1 second base cooldown
        
        if (currentTime - lastCast < cooldownTime) {
            long remainingTime = (cooldownTime - (currentTime - lastCast)) / 1000;
            player.sendMessage(ChatColor.RED + "Spell on cooldown! Wait " + remainingTime + " seconds.");
            return;
        }
        
        // Cast spell
        boolean success = MysticWands.getInstance().getSpellManager().castSpell(player, wandType.getSpell());
        
        if (success) {
            lastCastTime.put(playerId, currentTime);
        }
    }
}