package com.mysticwands.managers;

import com.mysticwands.MysticWands;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManaManager {
    
    private final Map<UUID, Integer> playerMana = new HashMap<>();
    private final Map<UUID, BossBar> manaBars = new HashMap<>();
    private final int maxMana = 100;
    private final int manaRegenRate = 2;
    private BukkitRunnable manaRegenTask;
    
    public void setMana(Player player, int mana) {
        playerMana.put(player.getUniqueId(), Math.min(mana, maxMana));
        updateManaBar(player);
    }
    
    public int getMana(Player player) {
        return playerMana.getOrDefault(player.getUniqueId(), maxMana);
    }
    
    public boolean consumeMana(Player player, int amount) {
        int currentMana = getMana(player);
        if (currentMana >= amount) {
            setMana(player, currentMana - amount);
            return true;
        }
        return false;
    }
    
    public void addMana(Player player, int amount) {
        int currentMana = getMana(player);
        setMana(player, currentMana + amount);
    }
    
    public void createManaBar(Player player) {
        BossBar manaBar = Bukkit.createBossBar("Mana", BarColor.BLUE, BarStyle.SOLID);
        manaBar.addPlayer(player);
        manaBars.put(player.getUniqueId(), manaBar);
        updateManaBar(player);
    }
    
    public void removeManaBar(Player player) {
        BossBar manaBar = manaBars.remove(player.getUniqueId());
        if (manaBar != null) {
            manaBar.removePlayer(player);
        }
    }
    
    private void updateManaBar(Player player) {
        BossBar manaBar = manaBars.get(player.getUniqueId());
        if (manaBar != null) {
            int currentMana = getMana(player);
            double progress = (double) currentMana / maxMana;
            manaBar.setProgress(progress);
            manaBar.setTitle("Mana: " + currentMana + "/" + maxMana);
            
            if (progress > 0.6) {
                manaBar.setColor(BarColor.BLUE);
            } else if (progress > 0.3) {
                manaBar.setColor(BarColor.YELLOW);
            } else {
                manaBar.setColor(BarColor.RED);
            }
        }
    }
    
    public void startManaRegenTask() {
        manaRegenTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    int currentMana = getMana(player);
                    if (currentMana < maxMana) {
                        addMana(player, manaRegenRate);
                    }
                }
            }
        };
        manaRegenTask.runTaskTimer(MysticWands.getInstance(), 0L, 40L); // Every 2 seconds
    }
    
    public void stopManaRegenTask() {
        if (manaRegenTask != null) {
            manaRegenTask.cancel();
        }
    }
    
    public int getMaxMana() {
        return maxMana;
    }
}