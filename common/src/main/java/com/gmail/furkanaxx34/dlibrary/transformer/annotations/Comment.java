package com.gmail.furkanaxx34.dlibrary.transformer.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * an annotation that puts comments to the field's path.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {

  /**
   * obtains the comments.
   *
   * @return comments.
   */
  @NotNull
  String[] value();
}
