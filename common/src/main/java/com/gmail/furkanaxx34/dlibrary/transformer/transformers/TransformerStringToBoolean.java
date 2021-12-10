package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

/**
 * a class that represents transformers between {@link String} and {@link Boolean}.
 */
public final class TransformerStringToBoolean extends Transformer.Base<String, Boolean> {

  /**
   * ctor.
   */
  public TransformerStringToBoolean() {
    super(String.class, Boolean.class,
      Boolean::parseBoolean);
  }
}
