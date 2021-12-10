package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartInventory;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PlyrQuitEvent;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * a class that represents player quit listeners.
 */
@RequiredArgsConstructor
public final class PlayerQuitListener implements Listener {

  /**
   * the stop tick function.
   */
  @NotNull
  private final Consumer<UUID> stopTickFunction;

  /**
   * listens the player quit event.
   *
   * @param event the event to listen.
   */
  @EventHandler
  public void onPlayerQuit(final PlayerQuitEvent event) {
    SmartInventory.getHolder(event.getPlayer()).ifPresent(holder -> {
      holder.getPage().accept(new PlyrQuitEvent(holder.getContents(), event));
      this.stopTickFunction.accept(event.getPlayer().getUniqueId());
    });
  }
}
