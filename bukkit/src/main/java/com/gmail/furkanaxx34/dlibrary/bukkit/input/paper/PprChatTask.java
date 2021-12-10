package com.gmail.furkanaxx34.dlibrary.bukkit.input.paper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.input.ChatTask;

/**
 * an implementation for {@link ChatTask}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class PprChatTask implements ChatTask {

  /**
   * the task.
   */
  @NotNull
  private final BukkitTask task;

  @Override
  public void cancel() {
    this.task.cancel();
  }

  @Override
  public boolean isCancelled() {
    return this.task.isCancelled();
  }
}
