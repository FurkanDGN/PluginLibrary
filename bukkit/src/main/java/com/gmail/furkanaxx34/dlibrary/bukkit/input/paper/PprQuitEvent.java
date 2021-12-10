package com.gmail.furkanaxx34.dlibrary.bukkit.input.paper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.input.ChatSender;
import com.gmail.furkanaxx34.dlibrary.input.event.QuitEvent;

/**
 * an implementation for {@link QuitEvent}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class PprQuitEvent implements QuitEvent<Player> {

  /**
   * the sender.
   */
  @NotNull
  @Getter
  private final ChatSender<Player> sender;
}
