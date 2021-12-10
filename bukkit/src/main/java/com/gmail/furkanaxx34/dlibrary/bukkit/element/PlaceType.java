package com.gmail.furkanaxx34.dlibrary.bukkit.element;

import lombok.var;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.types.*;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.*;
import java.util.stream.Collectors;

public interface PlaceType {

  static boolean getBoolean(@NotNull final Map<String, Object> map, @NotNull final String key,
                            final boolean defaultValue) {
    var value = defaultValue;
    if (!map.containsKey(key)) {
      return value;
    }
    try {
      value = Boolean.parseBoolean(String.valueOf(map.get(key)));
    } catch (final Exception ignored) {
    }
    return value;
  }

  @NotNull
  static Optional<Deserializer> getByType(@NotNull final String type) {
    final var replaced = type.replace("_", "-").toLowerCase(Locale.ROOT).trim();
    if (replaced.equalsIgnoreCase("fill")) {
      return Optional.of(PtFill.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-borders")) {
      return Optional.of(PtFillBorders.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-column")) {
      return Optional.of(PtFillColumn.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-empties")) {
      return Optional.of(PtFillEmpties.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-pattern")) {
      return Optional.of(PtFillPattern.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-pattern-start")) {
      return Optional.of(PtFillPatternStart.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-pattern-start-index")) {
      return Optional.of(PtFillPatternStartIndex.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-rect-from-to")) {
      return Optional.of(PtFillRectFromTo.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-rect-index")) {
      return Optional.of(PtFillRectIndex.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-repeating-pattern")) {
      return Optional.of(PtFillRepeatingPattern.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-repeating-pattern-start")) {
      return Optional.of(PtFillRepeatingPatternStart.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-repeating-pattern-start-index")) {
      return Optional.of(PtFillRepeatingPatternStartIndex.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-row")) {
      return Optional.of(PtFillRow.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-square-from-to")) {
      return Optional.of(PtFillSquareFromTo.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("fill-square-index")) {
      return Optional.of(PtFillSquareIndex.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("insert")) {
      return Optional.of(PtInsert.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("insert-index")) {
      return Optional.of(PtInsertIndex.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("none")) {
      return Optional.of(PtNone.Deserializer.INSTANCE);
    } else if (replaced.equalsIgnoreCase("slots")) {
      return Optional.of(PtSlots.Deserializer.INSTANCE);
    }
    return Optional.empty();
  }

  static int getInteger(@NotNull final Map<String, Object> map, @NotNull final String key,
                        final int defaultValue) {
    var value = defaultValue;
    if (!map.containsKey(key)) {
      return value;
    }
    try {
      value = Integer.parseInt(String.valueOf(map.get(key)));
    } catch (final Exception ignored) {
    }
    return value;
  }

  static List<Integer> getIntegerList(@NotNull final Map<String, Object> map, @NotNull final String key,
                                      @NotNull final List<Integer> defaultValue) {
    var value = defaultValue;
    if (!map.containsKey(key)) {
      return value;
    }
    try {
      value = ((Collection<?>) map.get(key)).stream()
        .map(String::valueOf)
        .map(s -> {
          try {
            return Integer.parseInt(s);
          } catch (final Exception ignored) {
          }
          return 0;
        })
        .collect(Collectors.toList());
    } catch (final Exception ignored) {
    }
    return value;
  }

  static List<String> getStringList(@NotNull final Map<String, Object> map, @NotNull final String key,
                                    @NotNull final List<String> defaultValue) {
    var value = defaultValue;
    if (!map.containsKey(key)) {
      return value;
    }
    try {
      value = ((Collection<?>) map.get(key)).stream()
        .map(String::valueOf)
        .collect(Collectors.toList());
    } catch (final Exception ignored) {
    }
    return value;
  }

  @NotNull
  String getType();

  void place(@NotNull Icon icon, @NotNull InventoryContents contents);

  default void serialize(@NotNull final TransformedData transformedData) {
    transformedData.add("type", this.getType(), String.class);
  }

  interface Deserializer {

    @NotNull
    Optional<PlaceType> deserialize(@NotNull TransformedData transformedData);
  }
}
