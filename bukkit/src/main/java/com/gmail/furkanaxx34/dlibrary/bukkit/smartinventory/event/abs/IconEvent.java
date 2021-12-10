package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;

/**
 * an interface to determine icon events.
 */
public interface IconEvent extends SmartEvent {

  /**
   * obtains the icon.
   *
   * @return icon.
   */
  @NotNull
  Icon icon();
}
