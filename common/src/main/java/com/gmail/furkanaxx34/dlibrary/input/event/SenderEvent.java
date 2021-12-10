package com.gmail.furkanaxx34.dlibrary.input.event;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.input.ChatSender;

/**
 * an interface to determine events that have the sender in it.
 *
 * @param <P> the sender type.
 */
public interface SenderEvent<P> {

  /**
   * obtains the sender instance.
   *
   * @return the sender instance.
   */
  @NotNull
  ChatSender<P> getSender();
}
