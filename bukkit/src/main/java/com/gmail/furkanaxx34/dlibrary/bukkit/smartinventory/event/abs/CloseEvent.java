package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine close evet.
 */
public interface CloseEvent extends PageEvent {

  /**
   * obtains the event.
   *
   * @return event.
   */
  @NotNull
  InventoryCloseEvent getEvent();
}
