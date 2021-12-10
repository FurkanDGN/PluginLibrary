package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.transformer.Transformer;

import java.util.Locale;

/**
 * a class that represents transformers between {@link String} and {@link Locale}.
 */
public final class TransformerStringToLocale extends Transformer.Base<String, Locale> {

  /**
   * ctor.
   */
  public TransformerStringToLocale() {
    super(String.class, Locale.class,
      TransformerStringToLocale::toLocale);
  }

  @Nullable
  private static Locale toLocale(@NotNull final String s) {
    final String trim = s.trim();
    if (trim.isEmpty()) {
      return Locale.ROOT;
    }
    final String[] strings = trim.split("_");
    if (trim.contains("_") && strings.length != 2) {
      return Locale.ROOT;
    }
    if (strings.length != 2) {
      return null;
    }
    return new Locale(strings[0], strings[1]);
  }
}
