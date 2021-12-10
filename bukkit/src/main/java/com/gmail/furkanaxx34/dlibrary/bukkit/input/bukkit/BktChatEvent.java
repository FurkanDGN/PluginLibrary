package com.gmail.furkanaxx34.dlibrary.bukkit.input.bukkit;

import com.gmail.furkanaxx34.dlibrary.input.ChatSender;
import com.gmail.furkanaxx34.dlibrary.input.event.ChatEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link ChatEvent}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class BktChatEvent implements ChatEvent<Player> {

    /**
     * the event.
     */
    @NotNull
    private final AsyncPlayerChatEvent event;

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
        return this.event.getMessage();
    }
}
