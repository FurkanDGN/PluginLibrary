package com.gmail.furkanaxx34.dlibrary.scoreboard;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.scoreboard.line.Line;

import java.io.Closeable;
import java.util.Collection;
import java.util.List;

/**
 * an interface to determine scoreboard senders.
 *
 * @param <O> type of the observers.
 */
public interface ScoreboardSender<O> extends Closeable {

  @Override
  void close();

  /**
   * sends the scoreboard lines to the observers.
   *
   * @param board the board to send.
   * @param observers the observers to send.
   * @param lines the lines to send.
   */
  void send(@NotNull Board<O> board, @NotNull Collection<O> observers, @NotNull List<Line<O>> lines);

  /**
   * a class that represents empty {@link ScoreboardSender} implementation.
   *
   * @param <O> type of the observers.
   */
  final class Empty<O> implements ScoreboardSender<O> {

    @Override
    public void close() {
    }

    @Override
    public void send(@NotNull final Board<O> board, @NotNull final Collection<O> observers,
                     @NotNull final List<Line<O>> lines) {
    }
  }
}
