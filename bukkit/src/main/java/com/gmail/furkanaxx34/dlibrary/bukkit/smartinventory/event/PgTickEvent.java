package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.TickEvent;

/**
 * a class that represents page tick events.
 */
@RequiredArgsConstructor
public final class PgTickEvent implements TickEvent {

  /**
   * the contents.
   */
  @NotNull
  private final InventoryContents contents;

  @NotNull
  @Override
  public InventoryContents contents() {
    return this.contents;
  }
}
