package com.gmail.furkanaxx34.dlibrary.reflection.parameterized;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.reflection.RefParameterized;

import java.util.Optional;
import java.util.function.Function;

/**
 * a class that allows you to use the given objects as parameter types.
 *
 * @param <T> type of the parameter.
 */
@RequiredArgsConstructor
public final class ParameterizedOf<T> implements RefParameterized<T> {

  /**
   * the converted.
   */
  @NotNull
  private final Converted converted;

  /**
   * ctor.
   *
   * @param primitive the primitive.
   * @param objects the objects.
   */
  public ParameterizedOf(final boolean primitive, @NotNull final Object... objects) {
    this(new Converted(primitive, objects));
  }

  /**
   * ctor.
   *
   * @param objects the objects.
   */
  public ParameterizedOf(@NotNull final Object... objects) {
    this(false, objects);
  }

  @Override
  public Optional<T> apply(@NotNull final Function<Class<?>[], Optional<T>> func) {
    return func.apply(this.converted.get());
  }
}
