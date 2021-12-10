package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.CloseEvent;

/**
 * a class that represents page close events.
 */
@RequiredArgsConstructor
public final class PgCloseEvent implements CloseEvent {

  /**
   * the contents.
   */
  @NotNull
  private final InventoryContents contents;

  /**
   * the event.
   */
  @NotNull
  private final InventoryCloseEvent event;

  @NotNull
  @Override
  public InventoryContents contents() {
    return this.contents;
  }

  @NotNull
  @Override
  public InventoryCloseEvent getEvent() {
    return this.event;
  }
}
