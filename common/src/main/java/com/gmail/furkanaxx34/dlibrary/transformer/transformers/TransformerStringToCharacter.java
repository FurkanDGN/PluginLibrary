package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

/**
 * a class that represents transformers between {@link String} and {@link Character}.
 */
public final class TransformerStringToCharacter extends Transformer.Base<String, Character> {

  /**
   * ctor.
   */
  public TransformerStringToCharacter() {
    super(String.class, Character.class,
      s -> {
        assert s.length() <= 1 : String.format("Character '%s' too long: %d>1", s, s.length());
        return s.charAt(0);
      });
  }
}
