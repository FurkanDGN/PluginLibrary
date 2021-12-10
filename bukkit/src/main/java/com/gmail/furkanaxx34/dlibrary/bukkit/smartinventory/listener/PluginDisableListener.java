package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Page;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartInventory;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PlgnDisableEvent;

/**
 * a class that represents plugin disable events.
 */
public final class PluginDisableListener implements Listener {

  /**
   * listens the plugin disable events.
   *
   * @param event the event to listen.
   */
  @EventHandler
  public void onPluginDisable(final PluginDisableEvent event) {
    SmartInventory.getHolders().forEach(holder -> {
      final Page page = holder.getPage();
      page.accept(new PlgnDisableEvent(holder.getContents(), event));
      page.close(holder.getPlayer());
    });
  }
}
