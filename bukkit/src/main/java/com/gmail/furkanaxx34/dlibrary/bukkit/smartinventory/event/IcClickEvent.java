package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.ClickEvent;

import java.util.Optional;

/**
 * a class that represents icon click events.
 */
@RequiredArgsConstructor
public final class IcClickEvent implements ClickEvent {

  /**
   * the contents.
   */
  @NotNull
  private final InventoryContents contents;

  /**
   * the event.
   */
  @NotNull
  private final InventoryClickEvent event;

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
  public InventoryAction action() {
    return this.event.getAction();
  }

  @NotNull
  @Override
  public ClickType click() {
    return this.event.getClick();
  }

  @Override
  public int column() {
    return this.event.getSlot() % 9;
  }

  @NotNull
  @Override
  public Optional<ItemStack> current() {
    return Optional.ofNullable(this.event.getCurrentItem());
  }

  @NotNull
  @Override
  public Optional<ItemStack> cursor() {
    return Optional.ofNullable(this.event.getCursor());
  }

  @NotNull
  @Override
  public InventoryClickEvent getEvent() {
    return this.event;
  }

  @Override
  public int row() {
    return this.event.getSlot() / 9;
  }

  @NotNull
  @Override
  public InventoryType.SlotType slot() {
    return this.event.getSlotType();
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
