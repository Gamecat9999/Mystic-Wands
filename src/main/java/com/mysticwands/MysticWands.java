package com.mysticwands;

import com.mysticwands.commands.WandCommand;
import com.mysticwands.listeners.PlayerInteractListener;
import com.mysticwands.listeners.PlayerJoinListener;
import com.mysticwands.managers.ManaManager;
import com.mysticwands.managers.SpellManager;
import com.mysticwands.managers.WandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MysticWands extends JavaPlugin {
    
    private static MysticWands instance;
    private ManaManager manaManager;
    private SpellManager spellManager;
    private WandManager wandManager;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // Save default config
        saveDefaultConfig();
        
        // Initialize managers
        manaManager = new ManaManager();
        spellManager = new SpellManager();
        wandManager = new WandManager();
        
        // Register events
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        
        // Register commands
        getCommand("wand").setExecutor(new WandCommand());
        
        // Start mana regeneration task
        manaManager.startManaRegenTask();
        
        getLogger().info("Mystic Wands has been enabled!");
    }
    
    @Override
    public void onDisable() {
        if (manaManager != null) {
            manaManager.stopManaRegenTask();
        }
        getLogger().info("Mystic Wands has been disabled!");
    }
    
    public static MysticWands getInstance() {
        return instance;
    }
    
    public ManaManager getManaManager() {
        return manaManager;
    }
    
    public SpellManager getSpellManager() {
        return spellManager;
    }
    
    public WandManager getWandManager() {
        return wandManager;
    }
}