package com.gmail.furkanaxx34.dlibrary.bukkit.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * a class that contains utility methods for {@link BukkitTask}.
 */
@UtilityClass
public class TaskUtilities {

  /**
   * the plugin.
   */
  @Nullable
  private Plugin plugin;

  /**
   * runs the given job.
   *
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask async(@NotNull final Consumer<BukkitRunnable> job) {
    return new RunnableWrapper(job)
      .runTaskAsynchronously(TaskUtilities.getPlugin());
  }

  /**
   * runs the given job with a delay.
   *
   * @param delay the delay to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask asyncLater(final long delay, @NotNull final Consumer<BukkitRunnable> job) {
    return new RunnableWrapper(job)
      .runTaskLaterAsynchronously(TaskUtilities.getPlugin(), delay);
  }

  /**
   * runs the given job with a period.
   *
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask asyncTimer(final long period, @NotNull final Consumer<BukkitRunnable> job) {
    return TaskUtilities.asyncTimerLater(0L, period, job);
  }

  /**
   * runs the given job with a period, then if the job returns {@code false} stops the task.
   *
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask asyncTimer(final long period, @NotNull final Predicate<BukkitRunnable> job) {
    return TaskUtilities.asyncTimerLater(0L, period, job);
  }

  /**
   * runs the given job with a delay and period, then if the job returns {@code false} stops the task.
   *
   * @param delay the delay to run.
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask asyncTimerLater(final long delay, final long period, @NotNull final Predicate<BukkitRunnable> job) {
    return new RunnableWrapper(BukkitRunnable::cancel, job)
      .runTaskTimerAsynchronously(TaskUtilities.getPlugin(), delay, period);
  }

  /**
   * runs the given job with a delay and period.
   *
   * @param delay the delay to run.
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask asyncTimerLater(final long delay, final long period, @NotNull final Consumer<BukkitRunnable> job) {
    return new RunnableWrapper(job)
      .runTaskTimerAsynchronously(TaskUtilities.getPlugin(), delay, period);
  }

  /**
   * initiates the task utilities.
   *
   * @param plugin the plugin to initiate.
   */
  public void init(@NotNull final Plugin plugin) {
    TaskUtilities.plugin = plugin;
  }

  /**
   * runs the given job.
   *
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask sync(@NotNull final Consumer<BukkitRunnable> job) {
    return new RunnableWrapper(job)
      .runTask(TaskUtilities.getPlugin());
  }

  /**
   * runs the given job with a delay.
   *
   * @param delay the delay to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask syncLater(final long delay, @NotNull final Consumer<BukkitRunnable> job) {
    return new RunnableWrapper(job)
      .runTaskLater(TaskUtilities.getPlugin(), delay);
  }

  /**
   * runs the job with a period.
   *
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask syncTimer(final long period, @NotNull final Consumer<BukkitRunnable> job) {
    return TaskUtilities.syncTimerLater(0L, period, job);
  }

  /**
   * runs the job with a period, then if the job returns {@code false} stops the task.
   *
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask syncTimer(final long period, @NotNull final Predicate<BukkitRunnable> job) {
    return TaskUtilities.syncTimerLater(0L, period, job);
  }

  /**
   * runs the given job with a delay and period, then if the job returns {@code false} stops the task.
   *
   * @param delay the delay to run.
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask syncTimerLater(final long delay, final long period, @NotNull final Predicate<BukkitRunnable> job) {
    return new RunnableWrapper(BukkitRunnable::cancel, job)
      .runTaskTimer(TaskUtilities.getPlugin(), delay, period);
  }

  /**
   * runs the given job with a delay and period.
   *
   * @param delay the delay to run.
   * @param period the period to run.
   * @param job the job to run.
   *
   * @return a newly run bukkit task instance.
   */
  @NotNull
  public BukkitTask syncTimerLater(final long delay, final long period, @NotNull final Consumer<BukkitRunnable> job) {
    return new RunnableWrapper(job)
      .runTaskTimer(TaskUtilities.getPlugin(), delay, period);
  }

  /**
   * obtains the plugin.
   *
   * @return plugin.
   *
   * @throws RuntimeException if {@link #plugin} is null.
   */
  @NotNull
  private Plugin getPlugin() {
    return Optional.ofNullable(TaskUtilities.plugin)
      .orElseThrow(() ->
        new RuntimeException("Use TaskUtilities#init(Plugin) first!"));
  }
}
