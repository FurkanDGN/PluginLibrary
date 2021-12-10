package com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder;

import lombok.var;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.Keys;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.*;
import java.util.function.Function;

/**
 * a class that represents banner item builders.
 */
public final class BannerItemBuilder extends Builder<BannerItemBuilder, BannerMeta> {

  /**
   * the deserializer.
   */
  private static final Deserializer DESERIALIZER = new Deserializer();

  /**
   * ctor.
   *
   * @param itemStack the item stack.
   * @param meta the meta.
   */
  BannerItemBuilder(@NotNull final BannerMeta meta, @NotNull final ItemStack itemStack) {
    super(meta, itemStack);
  }

  /**
   * creates a new banner item builder instance.
   *
   * @param itemMeta the item meta to create.
   * @param itemStack the item stack to create.
   *
   * @return a newly created banner item builder instance.
   */
  @NotNull
  public static BannerItemBuilder from(@NotNull final BannerMeta itemMeta, @NotNull final ItemStack itemStack) {
    return new BannerItemBuilder(itemMeta, itemStack);
  }

  /**
   * creates banner item builder from serialized data.
   *
   * @param data the data to create.
   *
   * @return a newly created banner item builder instance.
   */
  @NotNull
  public static BannerItemBuilder from(@NotNull final TransformedData data) {
    return BannerItemBuilder.getDeserializer().apply(data).orElseThrow(() ->
      new IllegalArgumentException(String.format("The given data is incorrect!\n%s", data)));
  }

  /**
   * obtains the deserializer.
   *
   * @return deserializer.
   */
  @NotNull
  public static Deserializer getDeserializer() {
    return BannerItemBuilder.DESERIALIZER;
  }

  /**
   * adds patterns to the banner.
   *
   * @param patterns the patterns to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BannerItemBuilder addPatterns(@NotNull final Pattern... patterns) {
    Arrays.stream(patterns).forEach(this.getItemMeta()::addPattern);
    return this.getSelf();
  }

  @NotNull
  @Override
  public BannerItemBuilder getSelf() {
    return this;
  }

  @Override
  public void serialize(@NotNull final TransformedData data) {
    super.serialize(data);
    final var patterns = new HashMap<String, String>();
    this.getItemMeta().getPatterns()
      .forEach(pattern -> patterns.put(pattern.getPattern().name(), pattern.getColor().name()));
    data.addAsMap(Keys.PATTERNS_KEY, patterns, String.class, String.class);
  }

  /**
   * removes patterns from the banner.
   *
   * @param index the index to remove.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BannerItemBuilder removePatterns(final int... index) {
    Arrays.stream(index).forEach(this.getItemMeta()::removePattern);
    return this.getSelf();
  }

  /**
   * sets base color of the banner.
   *
   * @param color the color to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  @Deprecated
  public BannerItemBuilder setBaseColor(@NotNull final DyeColor color) {
    this.getItemMeta().setBaseColor(color);
    return this.getSelf();
  }

  /**
   * sets pattern of the banner.
   *
   * @param index the index to set.
   * @param pattern the pattern to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BannerItemBuilder setPattern(final int index, @NotNull final Pattern pattern) {
    this.getItemMeta().setPattern(index, pattern);
    return this.getSelf();
  }

  /**
   * sets pattern of the banner.
   *
   * @param patterns the patterns to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BannerItemBuilder setPatterns(@NotNull final Pattern... patterns) {
    return this.setPatterns(new ArrayList<Pattern>(){{
      for (final Pattern pattern : patterns) {
        add(pattern);
      }
    }});
  }

  /**
   * sets pattern of the banner.
   *
   * @param patterns the patterns to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BannerItemBuilder setPatterns(@NotNull final List<Pattern> patterns) {
    this.getItemMeta().setPatterns(patterns);
    return this.getSelf();
  }

  /**
   * a class that represents deserializer of {@link BannerMeta}.
   */
  public static final class Deserializer implements
    Function<@NotNull TransformedData, @NotNull Optional<BannerItemBuilder>> {

    @NotNull
    @Override
    public Optional<BannerItemBuilder> apply(@NotNull final TransformedData data) {
      final var itemStack = Builder.getItemStackDeserializer().apply(data);
      if (!itemStack.isPresent()) {
        return Optional.empty();
      }
      final var builder = ItemStackBuilder.from(itemStack.get()).asBanner();
      data.getAsMap(Keys.PATTERNS_KEY, String.class, String.class)
        .ifPresent(patterns -> patterns.forEach((key, value) -> {
          var type = PatternType.getByIdentifier(key);
          if (type == null) {
            try {
              type = PatternType.valueOf(key.toUpperCase(Locale.ENGLISH));
            } catch (final Exception e) {
              type = PatternType.BASE;
            }
          }
          DyeColor color;
          try {
            color = DyeColor.valueOf(value.toUpperCase(Locale.ENGLISH));
          } catch (final Exception e) {
            color = DyeColor.WHITE;
          }
          builder.addPatterns(new Pattern(color, type));
        }));
      return Optional.of(Builder.getItemMetaDeserializer(builder).apply(data));
    }
  }
}
