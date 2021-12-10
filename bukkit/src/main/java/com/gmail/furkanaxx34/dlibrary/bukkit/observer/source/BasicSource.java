package com.gmail.furkanaxx34.dlibrary.bukkit.observer.source;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.observer.Source;
import com.gmail.furkanaxx34.dlibrary.bukkit.observer.Target;

import java.util.ArrayList;
import java.util.Collection;

/**
 * an implementation for {@link Source}.
 *
 * @param <T> type of the argument.
 */
public final class BasicSource<T> implements Source<T> {

  /**
   * the subscriptions.
   */
  private final Collection<Target<T>> subscriptions = new ArrayList<>();

  @Override
  public void notifyTargets(@NotNull final T argument) {
    this.subscriptions.forEach(target -> target.update(argument));
  }

  @Override
  public void subscribe(@NotNull final Target<T> target) {
    if (!this.subscriptions.contains(target)) {
      this.subscriptions.add(target);
    }
  }

  @Override
  public void unsubscribe(@NotNull final Target<T> target) {
    this.subscriptions.remove(target);
  }
}
