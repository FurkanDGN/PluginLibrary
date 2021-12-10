package com.gmail.furkanaxx34.dlibrary.replaceable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * an implementation of {@link com.gmail.furkanaxx34.dlibrary.replaceable.RpBase} for {@link String}.
 */
public final class RpString extends RpBase<RpString, String> {

  /**
   * ctor.
   *
   * @param value the value.
   */
  private RpString(@NotNull final String value) {
    super(value);
  }

  /**
   * creates a replaceable string instance.
   *
   * @param builder the builder to create.
   *
   * @return a newly created replaceable string.
   */
  @NotNull
  public static RpString from(@NotNull final StringBuilder builder) {
    return RpString.from(builder.toString());
  }

  /**
   * creates a replaceable string instance.
   *
   * @param text the text to create.
   *
   * @return a newly created replaceable string.
   */
  @NotNull
  public static RpString from(@NotNull final String text) {
    return new RpString(text);
  }

  /**
   * creates a replaceable string instance.
   *
   * @param object the object to create.
   *
   * @return a newly created replaceable string.
   */
  @NotNull
  public static RpString fromObject(@NotNull final Object object) {
    return RpString.from(Objects.toString(object));
  }

  @NotNull
  @Override
  public Supplier<RpString> newSelf(@NotNull final String value) {
    return () -> RpString.from(value);
  }

  @NotNull
  @Override
  public String replace(@NotNull final String value, @NotNull final CharSequence regex,
                        @NotNull final CharSequence replace) {
    return value.replace(regex, replace);
  }

  @NotNull
  @Override
  public RpString self() {
    return this;
  }
}
