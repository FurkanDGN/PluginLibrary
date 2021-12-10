package com.gmail.furkanaxx34.dlibrary.bukkit.input.bukkit;

import com.gmail.furkanaxx34.dlibrary.input.ChatSender;
import com.gmail.furkanaxx34.dlibrary.input.event.QuitEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link QuitEvent}.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class BktQuitEvent implements QuitEvent<Player> {

    /**
     * the sender.
     */
    @NotNull
    @Getter
    private final ChatSender<Player> sender;
}

