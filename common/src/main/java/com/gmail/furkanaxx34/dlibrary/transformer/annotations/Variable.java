package com.gmail.furkanaxx34.dlibrary.transformer.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * an annotation that replaces the value of the fields with JVM property or environment variable if available.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Variable {

  /**
   * obtains JVM property or environment.
   *
   * @return JVM property or environment.
   */
  @NotNull
  String value();
}
