package com.gmail.furkanaxx34.dlibrary.transformer.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * an annotation that sets custom path of the field.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomKey {

  /**
   * value of the path.
   *
   * @return path.
   */
  @NotNull
  String value();
}
