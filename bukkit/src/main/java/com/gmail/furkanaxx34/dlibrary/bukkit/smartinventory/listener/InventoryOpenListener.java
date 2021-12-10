package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartHolder;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PgOpenEvent;

/**
 * a class that represents inventory open listeners.
 */
public final class InventoryOpenListener implements Listener {

  /**
   * listens the inventory open events.
   *
   * @param event the event to listen.
   */
  @EventHandler
  public void onInventoryOpen(final InventoryOpenEvent event) {
    final InventoryHolder holder = event.getInventory().getHolder();
    if (!(holder instanceof SmartHolder)) {
      return;
    }
    final SmartHolder smartHolder = (SmartHolder) holder;
    smartHolder.getPage().accept(new PgOpenEvent(smartHolder.getContents(), event, smartHolder.getPlugin()));
  }
}
