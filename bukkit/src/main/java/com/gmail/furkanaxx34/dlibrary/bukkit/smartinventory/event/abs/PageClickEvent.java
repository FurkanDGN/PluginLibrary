package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine page click events.
 */
public interface PageClickEvent extends PageEvent {

  /**
   * obtains the event.
   *
   * @return event.
   */
  @NotNull
  InventoryClickEvent getEvent();
}
