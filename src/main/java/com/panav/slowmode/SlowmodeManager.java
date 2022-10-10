package com.panav.slowmode;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SlowmodeManager implements Listener {

    Slowmode main = Slowmode.instance;

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(main.getConfig().getLong("Slowmode"), TimeUnit.SECONDS).build();

    @EventHandler

    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        if (!player.hasPermission("chatcooldown.bypass")) {

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000);
            } else {
                long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " to use this again");
            }
        }
    }
}
