package com.gmail.furkanaxx34.dlibrary.bukkit.element.types;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.PlaceType;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class PtSlots implements PlaceType {

  private final Collection<Integer> slots;

  @NotNull
  private static PtSlots create(@NotNull final Map<String, Object> objects) {
    return new PtSlots(PlaceType.getIntegerList(objects, "slots", new ArrayList<Integer>(){{
      this.add(0);
      this.add(1);
      this.add(2);
    }}));
  }

  @NotNull
  @Override
  public String getType() {
    return "slots";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    this.slots.forEach(slot -> contents.set(slot, icon));
  }

  @Override
  public void serialize(@NotNull final TransformedData transformedData) {
    PlaceType.super.serialize(transformedData);
    final TransformedData copy = transformedData.copy();
    copy.addAsCollection("slots", this.slots, int.class);
    transformedData.add("values", copy);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return transformedData.getAsMap("values", String.class, Object.class)
        .map(PtSlots::create);
    }
  }
}
