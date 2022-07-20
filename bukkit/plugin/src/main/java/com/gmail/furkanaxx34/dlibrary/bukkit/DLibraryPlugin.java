package com.gmail.furkanaxx34.dlibrary.bukkit;

import com.cryptomorin.xseries.SkullCacheListener;
import com.gmail.furkanaxx34.dlibrary.bukkit.listeners.ListenerBasic;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Furkan Doğan
 */
public class DLibraryPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    DLibrary.initialize(this);

    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
      SkullCacheListener.addToCache(onlinePlayer.getUniqueId(), onlinePlayer.getName());
    }

    new ListenerBasic<>(
      PlayerJoinEvent.class,
      playerJoinEvent -> new Thread(() -> {
        Player player = playerJoinEvent.getPlayer();
        SkullCacheListener.addToCache(player.getUniqueId(), player.getName());
      }).start()
    ).register(this);
  }
}
