package com.gmail.furkanaxx34.dlibrary.bukkit.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@UtilityClass
public class NumberUtil {

  public long diffDays(@NotNull final Date first, @NotNull final Date second) {
    return Math.abs(first.getTime() - second.getTime()) / 86400000L;
  }

  public boolean isFloat(@NotNull final String text) {
    try {
      Float.parseFloat(text);
      return true;
    } catch (final NumberFormatException e) {
      return false;
    }
  }

  public boolean isInteger(@NotNull final String text) {
    try {
      Integer.parseInt(text);
      return true;
    } catch (final NumberFormatException e) {
      return false;
    }
  }

  public boolean isLong(@NotNull final String text) {
    try {
      Long.parseLong(text);
      return true;
    } catch (final NumberFormatException e) {
      return false;
    }
  }
}
