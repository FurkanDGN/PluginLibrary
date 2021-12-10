package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.server.PluginDisableEvent;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.DisableEvent;

/**
 * a class that represents plugin disable events.
 */
@RequiredArgsConstructor
public final class PlgnDisableEvent implements DisableEvent {

  /**
   * the contents.
   */
  @NotNull
  private final InventoryContents contents;

  /**
   * the event.
   */
  @NotNull
  private final PluginDisableEvent event;

  @NotNull
  @Override
  public InventoryContents contents() {
    return this.contents;
  }

  @NotNull
  @Override
  public PluginDisableEvent getEvent() {
    return this.event;
  }
}
