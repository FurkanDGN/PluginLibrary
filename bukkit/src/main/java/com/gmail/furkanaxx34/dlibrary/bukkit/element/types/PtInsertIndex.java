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
public final class PtInsertIndex implements PlaceType {

  private final int index;

  @NotNull
  private static PtInsertIndex create(@NotNull final Map<String, Object> objects) {
    return new PtInsertIndex(PlaceType.getInteger(objects, "index", 0));
  }

  @NotNull
  @Override
  public String getType() {
    return "insert-index";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    contents.set(this.index, icon);
  }

  @Override
  public void serialize(@NotNull final TransformedData transformedData) {
    PlaceType.super.serialize(transformedData);
    final TransformedData copy = transformedData.copy();
    copy.add("index", this.index, int.class);
    transformedData.add("values", copy);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return transformedData.getAsMap("values", String.class, Object.class)
        .map(PtInsertIndex::create);
    }
  }
}
