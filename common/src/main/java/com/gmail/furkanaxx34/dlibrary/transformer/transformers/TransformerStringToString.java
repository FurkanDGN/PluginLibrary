package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.util.function.Function;

/**
 * a class that represents transformers between {@link String} and {@link String}.
 */
public final class TransformerStringToString extends Transformer.Base<String, String> {

  /**
   * ctor.
   */
  public TransformerStringToString() {
    super(String.class, String.class,
      Function.identity());
  }
}
