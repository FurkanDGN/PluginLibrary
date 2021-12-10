package com.gmail.furkanaxx34.dlibrary.input;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * an interface to determine the input's sender.
 *
 * @param <T> the abstract type of the sender.
 */
public interface ChatSender<T> extends ChatWrap<T> {

  /**
   * obtains sender's unique id.
   *
   * @return the unique id of the sender.
   */
  @NotNull
  UUID getUniqueId();

  /**
   * sends the given message to the input's sender.
   *
   * @param message the message to send.
   */
  void sendMessage(@NotNull String message);
}
