package com.gmail.furkanaxx34.dlibrary.bukkit.input;

import com.gmail.furkanaxx34.dlibrary.bukkit.input.bukkit.BukkitChatPlatform;
import com.gmail.furkanaxx34.dlibrary.bukkit.input.paper.PaperChatPlatform;
import com.gmail.furkanaxx34.dlibrary.input.ChatPlatform;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * an abstract class of {@link ChatPlatform}.
 */
@RequiredArgsConstructor
public abstract class ChatPlatformBuilder implements ChatPlatform<Player>, Listener {

    @NotNull
    @SuppressWarnings("all")
    public static ChatPlatformBuilder getBuilder() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            return PaperChatPlatform.getBuilder();
        }catch (ClassNotFoundException ignored) {
            return BukkitChatPlatform.getBuilder();
        }
    }

}
