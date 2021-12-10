package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

/**
 * a class that represents transformers between {@link String} and {@link Double}.
 */
public final class TransformerStringToDouble extends Transformer.Base<String, Double> {

  /**
   * ctor.
   */
  public TransformerStringToDouble() {
    super(String.class, Double.class,
      Double::parseDouble);
  }
}
