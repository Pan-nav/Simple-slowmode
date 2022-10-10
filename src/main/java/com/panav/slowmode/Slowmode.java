package com.panav.slowmode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Slowmode extends JavaPlugin{

    public static Slowmode instance;
    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new SlowmodeManager(), this);
    }


}
