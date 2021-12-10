package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.math.BigDecimal;

/**
 * a class that represents transformers between {@link String} and {@link Short}.
 */
public final class TransformerStringToShort extends Transformer.Base<String, Short> {

  /**
   * ctor.
   */
  public TransformerStringToShort() {
    super(String.class, Short.class,
      s -> new BigDecimal(s).shortValueExact());
  }
}
