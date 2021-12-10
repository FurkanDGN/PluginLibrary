package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.replaceable.RpBase;
import com.gmail.furkanaxx34.dlibrary.replaceable.RpString;
import com.gmail.furkanaxx34.dlibrary.transformer.TwoSideTransformer;

/**
 * a class that represents transformers between {@link String} and {@link RpString}.
 */
public final class TransformerStringToRpString extends TwoSideTransformer.Base<String, RpString> {

  /**
   * ctor.
   */
  public TransformerStringToRpString() {
    super(String.class, RpString.class,
      RpBase::getValue,
      RpString::from,
      (s, rpString) -> rpString.value(s));
  }
}
