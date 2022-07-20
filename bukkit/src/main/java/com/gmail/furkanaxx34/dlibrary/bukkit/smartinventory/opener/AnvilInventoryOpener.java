package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.opener;

import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryOpener;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Page;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.holder.SmartInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Furkan Doğan
 */
public class AnvilInventoryOpener implements InventoryOpener {

  @NotNull
  @Override
  public Inventory open(@NotNull final InventoryContents contents) {
    final Page page = contents.page();
    if (page.column() != 3) {
      throw new IllegalArgumentException(
        String.format("The column count for the chest inventory must be 3, found: %s.", page.column()));
    }
    if (page.row() < 1 && page.row() > 1) {
      throw new IllegalArgumentException(
        String.format("The row count for the chest inventory must be 1, found: %s", page.row()));
    }
    final SmartInventoryHolder holder = new SmartInventoryHolder(contents);
    holder.setActive(true);
    final Inventory handle = Bukkit.createInventory(holder, InventoryType.ANVIL, page.title());
    this.fill(handle, contents);
    contents.player().openInventory(handle);
    return handle;
  }

  @Override
  public boolean supports(@NotNull final InventoryType type) {
    return type == InventoryType.ANVIL;
  }
}
