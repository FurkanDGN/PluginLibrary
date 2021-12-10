package com.gmail.furkanaxx34.dlibrary.bukkit.element.types;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.PlaceType;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.Pattern;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.*;

public final class PtFillRepeatingPatternStart implements PlaceType {

  private final int endColumn;

  private final int endRow;

  @NotNull
  private final List<String> pattern;

  private final int startColumn;

  private final int startRow;

  private final boolean wrapAround;

  public PtFillRepeatingPatternStart(final boolean wrapAround, @NotNull final List<String> pattern, final int startRow,
                                     final int startColumn, final int endRow, final int endColumn) {
    this.wrapAround = wrapAround;
    this.pattern = Collections.unmodifiableList(pattern);
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.endRow = endRow;
    this.endColumn = endColumn;
  }

  @NotNull
  private static PtFillRepeatingPatternStart create(@NotNull final Map<String, Object> objects) {
    return new PtFillRepeatingPatternStart(
      PlaceType.getBoolean(objects, "wrap-around", false),
      PlaceType.getStringList(objects, "pattern", new ArrayList<String>(){{
        add("xxx");
        add("yyy");
        add("zzz");
      }}),
      PlaceType.getInteger(objects, "start-row", 0),
      PlaceType.getInteger(objects, "start-column", 0),
      PlaceType.getInteger(objects, "end-row", 0),
      PlaceType.getInteger(objects, "end-column", 1));
  }

  @NotNull
  @Override
  public String getType() {
    return "fill-repeating-pattern-start";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    contents.fillPatternRepeating(new Pattern<>(this.wrapAround, this.pattern.stream().toArray(String[]::new)),
      this.startRow, this.startColumn, this.endRow, this.endColumn);
  }

  @Override
  public void serialize(@NotNull final TransformedData transformedData) {
    PlaceType.super.serialize(transformedData);
    final TransformedData copy = transformedData.copy();
    copy.add("wrap-around", this.wrapAround, boolean.class);
    copy.addAsCollection("pattern", this.pattern, String.class);
    copy.add("start-row", this.startRow, int.class);
    copy.add("start-column", this.startColumn, int.class);
    copy.add("end-row", this.endRow, int.class);
    copy.add("end-column", this.endColumn, int.class);
    transformedData.add("values", copy);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return transformedData.getAsMap("values", String.class, Object.class)
        .map(PtFillRepeatingPatternStart::create);
    }
  }
}
