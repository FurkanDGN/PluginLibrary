package com.gmail.furkanaxx34.dlibrary.bukkit.input.paper;

import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.input.ChatSender;
import com.gmail.furkanaxx34.dlibrary.input.event.ChatEvent;

/**
 * an implementation for {@link ChatEvent}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class PprChatEvent implements ChatEvent<Player> {

  /**
   * the event.
   */
  @NotNull
  private final AsyncChatEvent event;

  /**
   * the sender.
   */
  @NotNull
  @Getter
  private final ChatSender<Player> sender;

  @Override
  public void cancel() {
    this.event.setCancelled(true);
  }

  @NotNull
  @Override
  public String getMessage() {
    return ((TextComponent) this.event.message()).content();
  }
}
