package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.util.Objects;

/**
 * a class that represents transformers between {@link Object} and {@link String}.
 */
public final class TransformerObjectToString extends Transformer.Base<Object, String> {

  /**
   * ctor.
   */
  public TransformerObjectToString() {
    super(Object.class, String.class,
      Objects::toString);
  }
}
