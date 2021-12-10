package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.QuitEvent;

/**
 * a class that represents player quit events.
 */
@RequiredArgsConstructor
public final class PlyrQuitEvent implements QuitEvent {

  /**
   * the contents.
   */
  @NotNull
  private final InventoryContents contents;

  /**
   * the event.
   */
  @NotNull
  private final PlayerQuitEvent event;

  @NotNull
  @Override
  public InventoryContents contents() {
    return this.contents;
  }

  @NotNull
  @Override
  public PlayerQuitEvent getEvent() {
    return this.event;
  }
}
