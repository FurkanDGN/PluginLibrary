package com.gmail.furkanaxx34.dlibrary.input;

import org.jetbrains.annotations.NotNull;

/**
 * a class that wraps an abstract object to get the real object.
 *
 * @param <T> the wrapped object type.
 */
public interface ChatWrap<T> {

  /**
   * obtains the wrapped object.
   *
   * @return wrapped object.
   */
  @NotNull
  T getWrapped();
}
