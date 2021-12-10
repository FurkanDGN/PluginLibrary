package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartHolder;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.IcDragEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.SlotPos;

/**
 * a class that represents inventory drag listeners.
 */
public final class InventoryDragListener implements Listener {

  /**
   * listens inventory drag events.
   *
   * @param event the event to listen.
   */
  @EventHandler(priority = EventPriority.LOW)
  public void onInventoryDrag(final InventoryDragEvent event) {
    final InventoryHolder holder = event.getInventory().getHolder();
    if (!(holder instanceof SmartHolder)) {
      return;
    }
    final SmartHolder smartHolder = (SmartHolder) holder;
    final Inventory inventory = event.getInventory();
    final InventoryContents contents = smartHolder.getContents();
    for (final Integer slot : event.getRawSlots()) {
      final SlotPos pos = SlotPos.of(slot / 9, slot % 9);
      contents.get(pos).ifPresent(icon ->
        icon.accept(new IcDragEvent(contents, event, icon, smartHolder.getPlugin())));
      if (slot >= inventory.getSize() || contents.isEditable(pos)) {
        continue;
      }
      event.setCancelled(true);
      break;
    }
  }
}
