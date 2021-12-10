package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.DragType;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.DragEvent;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * a class that represents icon drag events.
 */
@RequiredArgsConstructor
public final class IcDragEvent implements DragEvent {

  /**
   * the contents.
   */
  @NotNull
  private final InventoryContents contents;

  /**
   * the event.
   */
  @NotNull
  private final InventoryDragEvent event;

  /**
   * the icon.
   */
  @NotNull
  private final Icon icon;

  /**
   * the plugin.
   */
  @NotNull
  private final Plugin plugin;

  @NotNull
  @Override
  public Map<Integer, ItemStack> added() {
    return this.event.getNewItems();
  }

  @NotNull
  @Override
  public DragType drag() {
    return this.event.getType();
  }

  @NotNull
  @Override
  public InventoryDragEvent getEvent() {
    return this.event;
  }

  @NotNull
  @Override
  public Optional<ItemStack> newCursor() {
    return Optional.ofNullable(this.event.getCursor());
  }

  @NotNull
  @Override
  public Set<Integer> slots() {
    return this.event.getInventorySlots();
  }

  @Override
  public void cancel() {
    this.event.setCancelled(true);
  }

  @Override
  public void close() {
    Bukkit.getScheduler().runTask(this.plugin, () ->
      this.contents.page().close(this.contents.player()));
  }

  @NotNull
  @Override
  public InventoryContents contents() {
    return this.contents;
  }

  @NotNull
  @Override
  public Icon icon() {
    return this.icon;
  }
}
