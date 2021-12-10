package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.util.UUID;

/**
 * a class that represents transformers between {@link String} and {@link UUID}.
 */
public final class TransformerStringToUniqueId extends Transformer.Base<String, UUID> {

  /**
   * ctor.
   */
  public TransformerStringToUniqueId() {
    super(String.class, UUID.class,
      TransformerStringToUniqueId::toUniqueId);
  }

  /**
   * converts the given string into {@link UUID}.
   *
   * @param uniqueId the unique id to convert.
   *
   * @return converted {@link UUID} instance.
   */
  @Nullable
  private static UUID toUniqueId(@NotNull final String uniqueId) {
    try {
      return UUID.fromString(uniqueId);
    } catch (final IllegalArgumentException ignored) {
    }
    return null;
  }
}
