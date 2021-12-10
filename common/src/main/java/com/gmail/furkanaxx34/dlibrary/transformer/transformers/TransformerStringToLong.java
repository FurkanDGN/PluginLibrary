package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.math.BigDecimal;

/**
 * a class that represents transformers between {@link String} and {@link Long}.
 */
public final class TransformerStringToLong extends Transformer.Base<String, Long> {

  /**
   * ctor.
   */
  public TransformerStringToLong() {
    super(String.class, Long.class,
      s -> new BigDecimal(s).longValueExact());
  }
}
