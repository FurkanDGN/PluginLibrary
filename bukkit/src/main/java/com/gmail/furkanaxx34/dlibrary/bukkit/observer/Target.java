package com.gmail.furkanaxx34.dlibrary.bukkit.observer;

import org.jetbrains.annotations.NotNull;

/**
 * a class that runs the update method when the observer call it.
 *
 * @param <T> type of the argument.
 */
public interface Target<T> {

  /**
   * runs when the observer calls it.
   *
   * @param argument the argument to update.
   */
  void update(@NotNull T argument);
}
