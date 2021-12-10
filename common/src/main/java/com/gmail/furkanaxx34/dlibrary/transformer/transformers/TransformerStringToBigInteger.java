package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.math.BigInteger;

/**
 * a class that represents transformers between {@link String} and {@link BigInteger}.
 */
public final class TransformerStringToBigInteger extends Transformer.Base<String, BigInteger> {

  /**
   * ctor.
   */
  public TransformerStringToBigInteger() {
    super(String.class, BigInteger.class,
      BigInteger::new);
  }
}
