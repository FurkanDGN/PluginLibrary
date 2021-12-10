package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;

/**
 * an interface to determine smart events.
 */
public interface SmartEvent {

  /**
   * cancels the vent.
   */
  default void cancel() {
  }

  /**
   * closes the inventory.
   */
  default void close() {
  }

  /**
   * obtains the contents.
   *
   * @return contents.
   */
  @NotNull
  InventoryContents contents();
}
