package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.math.BigDecimal;

/**
 * a class that represents transformers between {@link String} and {@link Integer}.
 */
public final class TransformerStringToInteger extends Transformer.Base<String, Integer> {

  /**
   * ctor.
   */
  public TransformerStringToInteger() {
    super(String.class, Integer.class,
      s -> new BigDecimal(s).intValueExact());
  }
}
