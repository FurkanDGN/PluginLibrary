package com.gmail.furkanaxx34.dlibrary.bukkit.element.types;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.PlaceType;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Icon;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryContents;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.Pattern;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.*;

public final class PtFillPattern implements PlaceType {

  @NotNull
  private final List<String> pattern;

  private final boolean wrapAround;

  public PtFillPattern(final boolean wrapAround, @NotNull final List<String> pattern) {
    this.wrapAround = wrapAround;
    this.pattern = Collections.unmodifiableList(pattern);
  }

  @NotNull
  private static PtFillPattern create(@NotNull final Map<String, Object> objects) {
    return new PtFillPattern(
      PlaceType.getBoolean(objects, "wrap-around", false),
      PlaceType.getStringList(objects, "pattern", new ArrayList<String>(){{
        add("xxx");
        add("yyy");
        add("zzz");
      }}));
  }

  @NotNull
  @Override
  public String getType() {
    return "fill-pattern";
  }

  @Override
  public void place(@NotNull final Icon icon, @NotNull final InventoryContents contents) {
    contents.fillPattern(new Pattern<>(this.wrapAround, this.pattern.stream().toArray(String[]::new)));
  }

  @Override
  public void serialize(@NotNull final TransformedData transformedData) {
    PlaceType.super.serialize(transformedData);
    final TransformedData copy = transformedData.copy();
    copy.add("wrap-around", this.wrapAround, boolean.class);
    copy.addAsCollection("pattern", this.pattern, String.class);
    transformedData.add("values", copy);
  }

  public static final class Deserializer implements PlaceType.Deserializer {

    public static final Deserializer INSTANCE = new Deserializer();

    @NotNull
    @Override
    public Optional<PlaceType> deserialize(@NotNull final TransformedData transformedData) {
      return transformedData.getAsMap("values", String.class, Object.class)
        .map(PtFillPattern::create);
    }
  }
}
