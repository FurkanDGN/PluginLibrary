package com.gmail.furkanaxx34.dlibrary.transformer.postprocessor;

import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

/**
 * a functional interface that manipulates inputs.
 */
@FunctionalInterface
public interface Manipulator extends UnaryOperator<@NotNull String> {

}
