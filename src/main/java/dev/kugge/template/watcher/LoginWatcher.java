package dev.kugge.template.watcher;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginWatcher implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        // Do something
    }
}
