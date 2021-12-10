package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.observer.Target;

/**
 * a class that allows to manage player's inventory contents.
 */
public interface InventoryProvider extends Target<InventoryContents> {

  /**
   * an empty inventory provider.
   */
  InventoryProvider EMPTY = new InventoryProvider() {
  };

  /**
   * runs when the page has just opened.
   *
   * @param contents the contents to initiate.
   */
  default void init(@NotNull final InventoryContents contents) {
  }

  /**
   * runs every tick.
   *
   * @param contents the contents to tick.
   */
  default void tick(@NotNull final InventoryContents contents) {
  }

  /**
   * runs when {@link InventoryContents#notifyUpdate()} runs.
   * <p>
   * came from {@link Target}'s method.
   *
   * @param contents the contents to update.
   */
  @Override
  default void update(@NotNull final InventoryContents contents) {
  }
}
