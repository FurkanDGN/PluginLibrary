package com.gmail.furkanaxx34.dlibrary.transformer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.transformer.transformers.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;

/**
 * an interface to determine transform packs.
 */
public interface TransformPack extends Consumer<@NotNull TransformRegistry> {

  /**
   * the default serializers.
   */
  Collection<ObjectSerializer<?>> DEFAULT_SERIALIZERS = new HashSet<ObjectSerializer<?>>();

  /**
   * the default transformers.
   */
  Collection<Transformer<?, ?>> DEFAULT_TRANSFORMERS = new HashSet<Transformer<?, ?>>() {{
    add(new TransformerObjectToString());
    add(new TransformerStringToString());
    add(new TransformerStringToAddress());
    add(new TransformerStringToRpString());
    add(new TransformerStringListToRpList());
  }};

  /**
   * the default transformers reversed to string.
   */
  Collection<Transformer<?, ?>> DEFAULT_TRANSFORMERS_REVERSED_TO_STRING = new HashSet<Transformer<?, ?>>(){{
    add(new TransformerStringToBigDecimal());
    add(new TransformerStringToBigInteger());
    add(new TransformerStringToBoolean());
    add(new TransformerStringToByte());
    add(new TransformerStringToCharacter());
    add(new TransformerStringToDouble());
    add(new TransformerStringToFloat());
    add(new TransformerStringToInteger());
    add(new TransformerStringToLocale());
    add(new TransformerStringToLong());
    add(new TransformerStringToShort());
    add(new TransformerStringToUniqueId());
  }};

  /**
   * the default transform pack.
   */
  TransformPack DEFAULT = TransformPack.create(registry -> registry
    .withTransformers(TransformPack.DEFAULT_TRANSFORMERS)
    .withTransformersReversedToString(TransformPack.DEFAULT_TRANSFORMERS_REVERSED_TO_STRING)
    .withSerializers(TransformPack.DEFAULT_SERIALIZERS));

  /**
   * creates a simple transform pack instance.
   *
   * @param consumer the consumer to create.
   *
   * @return a newly created transform pack.
   */
  @NotNull
  static TransformPack create(@NotNull final Consumer<@NotNull TransformRegistry> consumer) {
    return new Impl(consumer);
  }

  /**
   * a simple implementation of {@link TransformPack}.
   */
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Impl implements TransformPack {

    /**
     * the delegation.
     */
    @NotNull
    @Delegate
    private final Consumer<@NotNull TransformRegistry> delegation;
  }
}
