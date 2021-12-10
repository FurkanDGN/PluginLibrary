package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine type of the classes at runtime.
 *
 * @param <T> type of the class.
 */
public interface Type<T> {

  /**
   * obtains the type of the class.
   *
   * @return the type of the class.
   */
  @NotNull
  Class<T> type();
}
