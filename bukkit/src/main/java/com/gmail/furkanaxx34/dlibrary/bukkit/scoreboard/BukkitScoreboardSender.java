package com.gmail.furkanaxx34.dlibrary.bukkit.scoreboard;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.scoreboard.Board;
import com.gmail.furkanaxx34.dlibrary.scoreboard.ScoreboardSender;
import com.gmail.furkanaxx34.dlibrary.scoreboard.line.Line;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * a {@link Player} implementation of {@link ScoreboardSender}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class BukkitScoreboardSender implements ScoreboardSender<Player> {

  /**
   * the plugin.
   */
  @NotNull
  private final Plugin plugin;

  /**
   * the scoreboards.
   */
  private final Map<UUID, BukkitPlayerScoreboard> scoreboards = new ConcurrentHashMap<>();

  @Override
  public void close() {
    this.scoreboards.values()
      .forEach(BukkitPlayerScoreboard::close);
    this.scoreboards.clear();
  }

  @Override
  @Synchronized("scoreboards")
  public void send(@NotNull final Board<Player> board, @NotNull final Collection<Player> observers,
                   @NotNull final List<Line<Player>> lines) {
    observers.stream()
      .map(Entity::getUniqueId)
      .map(uniqueId -> this.scoreboards.computeIfAbsent(uniqueId, uuid ->
        BukkitPlayerScoreboard.create(board, lines, this.plugin, uuid)))
      .forEach(scoreboard -> scoreboard.update(lines));
  }

  /**
   * obtains the scoreboards.
   *
   * @return scoreboards.
   */
  @NotNull
  @Synchronized("scoreboards")
  public Collection<BukkitPlayerScoreboard> getScoreboards() {
    return this.scoreboards.values();
  }

  /**
   * runs when the player quits from the game.
   *
   * @param player the player to quit.
   */
  @Synchronized("scoreboards")
  void onQuit(@NotNull final Player player) {
    Optional.ofNullable(this.scoreboards.remove(player.getUniqueId()))
      .ifPresent(scoreboard -> BukkitPlayerScoreboard.close(player));
  }
}
