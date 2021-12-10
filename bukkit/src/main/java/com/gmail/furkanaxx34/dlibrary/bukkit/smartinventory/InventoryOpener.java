package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * a class that opens {@link Inventory}s from the given {@link InventoryType}s.
 */
public interface InventoryOpener {

  /**
   * fills the given contents to the given inventory.
   *
   * @param inventory the inventory to fill.
   * @param contents the contents to fill.
   */
  default void fill(@NotNull final Inventory inventory, @NotNull final InventoryContents contents) {
    final Icon[][] items = contents.all();
    for (int row = 0; row < items.length; row++) {
      for (int column = 0; column < items[row].length; column++) {
        if (items[row][column] != null) {
          inventory.setItem(9 * row + column, items[row][column].calculateItem(contents));
        }
      }
    }
  }

  /**
   * opens the page for the given player.
   *
   * @param contents the contents to open.
   *
   * @return opened inventory itself.
   */
  @NotNull
  Inventory open(@NotNull InventoryContents contents);

  /**
   * checks if the inventory type is supporting for {@code this}.
   *
   * @param type the type to check.
   *
   * @return {@code true} if the type supports the type..
   */
  boolean supports(@NotNull InventoryType type);
}
