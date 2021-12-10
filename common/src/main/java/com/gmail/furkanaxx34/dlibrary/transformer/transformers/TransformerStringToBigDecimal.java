package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.math.BigDecimal;

/**
 * a class that represents transformers between {@link String} and {@link BigDecimal}.
 */
public final class TransformerStringToBigDecimal extends Transformer.Base<String, BigDecimal> {

  /**
   * ctor.
   */
  public TransformerStringToBigDecimal() {
    super(String.class, BigDecimal.class,
      BigDecimal::new);
  }
}
