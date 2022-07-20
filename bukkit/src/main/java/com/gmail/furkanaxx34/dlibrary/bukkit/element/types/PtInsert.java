package com.gmail.furkanaxx34.dlibrary.bukkit.element.types;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.PlaceType;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.SlotPos;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.Map;
import java.util.Optional;

public final class PtInsert implements PlaceType {

  private final int column;

  private final int row;

  public PtInsert(final int row, final int column) {
    this.row = row;
    this.column = column;
  }

  @NotNull
  private static PtInsert create(@NotNull final Map<String, Object> objects) {
    return new PtInsert(
      PlaceType.getInteger(objects, "row", 0),
      PlaceType.getInteger(objects, "column", 0));
  }

  @NotNull
  @Override
  public String getType() {
    return "insert";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    contents.set(SlotPos.of(this.row, this.column), icon);
  }

  @Override
  public void serialize(@NotNull final TransformedData transformedData) {
    PlaceType.super.serialize(transformedData);
    final TransformedData copy = transformedData.copy();
    copy.add("row", this.row, int.class);
    copy.add("column", this.column, int.class);
    transformedData.add("values", copy);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return transformedData.getAsMap("values", String.class, Object.class)
        .map(PtInsert::create);
    }
  }

  @Override
  public String toString() {
    return "PtInsert{" +
      "column=" + this.column +
      ", row=" + this.row +
      '}';
  }
}
