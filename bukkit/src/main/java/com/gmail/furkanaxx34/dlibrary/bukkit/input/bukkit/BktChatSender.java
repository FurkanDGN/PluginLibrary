package com.gmail.furkanaxx34.dlibrary.bukkit.input.bukkit;

import com.gmail.furkanaxx34.dlibrary.input.ChatSender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * an implementation for {@link ChatSender}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class BktChatSender implements ChatSender<Player> {

    /**
     * the wrapped.
     */
    @NotNull
    @Getter
    private final Player wrapped;

    @NotNull
    @Override
    public UUID getUniqueId() {
        return this.wrapped.getUniqueId();
    }

    @Override
    public void sendMessage(@NotNull final String message) {
        this.wrapped.sendMessage(message);
    }
}
