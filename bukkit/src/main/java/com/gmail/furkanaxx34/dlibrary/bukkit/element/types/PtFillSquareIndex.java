package com.gmail.furkanaxx34.dlibrary.bukkit.element.types;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.PlaceType;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class PtFillSquareIndex implements PlaceType {

  private final int fromIndex;

  private final int toIndex;

  @NotNull
  private static PtFillSquareIndex create(@NotNull final Map<String, Object> objects) {
    return new PtFillSquareIndex(
      PlaceType.getInteger(objects, "from-index", 0),
      PlaceType.getInteger(objects, "to-index", 0));
  }

  @NotNull
  @Override
  public String getType() {
    return "fill-square-index";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    contents.fillSquare(this.fromIndex, this.toIndex, icon);
  }

  @Override
  public void serialize(@NotNull final TransformedData transformedData) {
    PlaceType.super.serialize(transformedData);
    final TransformedData copy = transformedData.copy();
    copy.add("from-index", this.fromIndex, int.class);
    copy.add("to-index", this.toIndex, int.class);
    transformedData.add("values", copy);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return transformedData.getAsMap("values", String.class, Object.class)
        .map(PtFillSquareIndex::create);
    }
  }
}
