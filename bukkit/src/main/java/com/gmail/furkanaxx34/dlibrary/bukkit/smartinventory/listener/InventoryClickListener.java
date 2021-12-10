package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.listener;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Page;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartHolder;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.IcClickEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PgBottomClickEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PgClickEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.PgOutsideClickEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.SlotPos;

/**
 * a class that represents inventory click listeners.
 */
public final class InventoryClickListener implements Listener {

  /**
   * listens inventory click events.
   *
   * @param event the event to listen.
   */
  @EventHandler
  public void onInventoryClick(final InventoryClickEvent event) {
    final InventoryHolder holder = event.getInventory().getHolder();
    if (!(holder instanceof SmartHolder)) {
      return;
    }
    final SmartHolder smartHolder = (SmartHolder) holder;
    if (event.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
      event.setCancelled(true);
      return;
    }
    final Page page = smartHolder.getPage();
    final InventoryContents contents = smartHolder.getContents();
    final Plugin plugin = smartHolder.getPlugin();
    final Inventory clicked = event.getClickedInventory();
    if (clicked == null) {
      page.accept(new PgOutsideClickEvent(contents, event, plugin));
      return;
    }
    final HumanEntity player = event.getWhoClicked();
    if (clicked.equals(player.getOpenInventory().getBottomInventory())) {
      page.accept(new PgBottomClickEvent(contents, event, plugin));
      return;
    }
    final ItemStack current = event.getCurrentItem();
    if (current == null || current.getType() == Material.AIR) {
      page.accept(new PgClickEvent(contents, event, plugin));
      return;
    }
    final int slot = event.getSlot();
    final int row = slot / 9;
    final int column = slot % 9;
    if (!page.checkBounds(row, column)) {
      return;
    }
    final SlotPos slotPos = SlotPos.of(row, column);
    if (!contents.isEditable(slotPos)) {
      event.setCancelled(true);
    }
    contents.get(slotPos).ifPresent(item ->
      item.accept(new IcClickEvent(contents, event, item, plugin)));
    if (!contents.isEditable(slotPos) && player instanceof Player) {
      ((Player) player).updateInventory();
    }
  }
}
