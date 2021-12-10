package com.gmail.furkanaxx34.dlibrary.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * an interface to determine applied {@link java.lang.reflect.Field}.
 */
public interface RefFieldExecuted {

  /**
   * gets the field's value.
   *
   * @return the field value.
   */
  @NotNull
  Optional<Object> getValue();

  /**
   * sets the field to the given value.
   *
   * @param value the value to set.
   */
  void setValue(@Nullable Object value);
}
