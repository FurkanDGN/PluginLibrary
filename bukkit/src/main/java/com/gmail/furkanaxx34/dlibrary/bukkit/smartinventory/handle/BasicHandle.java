package com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.handle;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Handle;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.SmartEvent;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * an implementation for {@link Handle}.
 *
 * @param <T> type of the event.
 */
@RequiredArgsConstructor
public final class BasicHandle<T extends SmartEvent> implements Handle<T> {

  /**
   * the class.
   */
  @NotNull
  private final Class<T> clazz;

  /**
   * the consumer.
   */
  @NotNull
  private final Consumer<T> consumer;

  /**
   * the requirements.
   */
  @NotNull
  private final List<Predicate<T>> requirements;

  @Override
  public void accept(@NotNull final T t) {
    if (this.requirements.stream().allMatch(req -> req.test(t))) {
      this.consumer.accept(t);
    }
  }

  @NotNull
  @Override
  public Class<T> type() {
    return this.clazz;
  }
}
