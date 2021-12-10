package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.math.BigDecimal;

/**
 * a class that represents transformers between {@link String} and {@link Byte}.
 */
public final class TransformerStringToByte extends Transformer.Base<String, Byte> {

  /**
   * ctor.
   */
  public TransformerStringToByte() {
    super(String.class, Byte.class,
      s -> new BigDecimal(s).byteValueExact());
  }
}
