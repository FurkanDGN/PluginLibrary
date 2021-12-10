package com.gmail.furkanaxx34.dlibrary.transformer.postprocessor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents line infos.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class LineInfo {

  /**
   * the change.
   */
  private final int change;

  /**
   * the indent.
   */
  private final int indent;

  /**
   * the name.
   */
  @NotNull
  private final String name;

  /**
   * creates a line info instance.
   *
   * @param name the name to create.
   * @param change the change to create.
   * @param indent the index to create.
   *
   * @return a newly created line info instance.
   */
  @NotNull
  public static LineInfo of(@NotNull final String name, final int change, final int indent) {
    return new LineInfo(change, indent, name);
  }
}
