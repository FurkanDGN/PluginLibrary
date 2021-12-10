package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

/**
 * a class that represents transformers between {@link String} and {@link Float}.
 */
public final class TransformerStringToFloat extends Transformer.Base<String, Float> {

  /**
   * ctor.
   */
  public TransformerStringToFloat() {
    super(String.class, Float.class,
      Float::parseFloat);
  }
}
