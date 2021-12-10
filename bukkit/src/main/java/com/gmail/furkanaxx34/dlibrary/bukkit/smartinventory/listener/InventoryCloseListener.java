package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Page;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartHolder;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PgCloseEvent;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * a class that represents inventory close listeners.
 */
@RequiredArgsConstructor
public final class InventoryCloseListener implements Listener {

  /**
   * the stop tick function.
   */
  @NotNull
  private final Consumer<UUID> stopTickFunction;

  /**
   * listens inventory close events.
   *
   * @param event the event to listen.
   */
  @EventHandler
  public void onInventoryClose(final InventoryCloseEvent event) {
    final InventoryHolder holder = event.getInventory().getHolder();
    if (!(holder instanceof SmartHolder)) {
      return;
    }
    final SmartHolder smartHolder = (SmartHolder) holder;
    final Inventory inventory = event.getInventory();
    final Page page = smartHolder.getPage();
    final PgCloseEvent close = new PgCloseEvent(smartHolder.getContents(), event);
    page.accept(close);
    if (!page.canClose(close)) {
      Bukkit.getScheduler().runTask(smartHolder.getPlugin(), () ->
        event.getPlayer().openInventory(inventory));
      return;
    }
    inventory.clear();
    this.stopTickFunction.accept(event.getPlayer().getUniqueId());
  }
}
