package com.gmail.furkanaxx34.dlibrary.bukkit.element.types;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.PlaceType;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.Optional;

public final class PtFillBorders implements PlaceType {

  public static final PtFillBorders INSTANCE = new PtFillBorders();

  @NotNull
  @Override
  public String getType() {
    return "fill-borders";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    contents.fillEmpties(icon);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return Optional.of(PtFillBorders.INSTANCE);
    }
  }
}
