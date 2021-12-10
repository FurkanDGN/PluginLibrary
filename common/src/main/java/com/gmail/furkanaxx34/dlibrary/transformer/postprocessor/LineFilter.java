package com.gmail.furkanaxx34.dlibrary.transformer.postprocessor;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * a functional interface that filters lines.
 */
@FunctionalInterface
public interface LineFilter extends Predicate<@NotNull String> {

}
