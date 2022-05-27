package com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder;

import com.cryptomorin.xseries.XPotion;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.Keys;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * a class that represents potion item builders.
 * <p>
 * serialization:
 * <pre>
 * color: integer (as rgb) (for 11 and newer versions)
 *
 * custom-effects: (string list) (for 9 and newer versions)
 *   - 'effect type name as string, effect duration as integer, effect amplifier as integer'
 *
 * base-effect: 'potion type name as string, potion has extended as boolean, potion is upgraded as boolean' (for 9 and newer versions)
 *
 * level: integer (potion level) (for 8 and older versions)
 *
 * base-effect: 'potion type name as string, potion has extended as boolean, potion is splash as boolean' (for 8 and older versions)
 * </pre>
 */
public final class PotionItemBuilder extends Builder<PotionItemBuilder, PotionMeta> {

  /**
   * the deserializer.
   */
  private static final Deserializer DESERIALIZER = new Deserializer();

  /**
   * ctor.
   *
   * @param itemMeta the item meta.
   * @param itemStack the item stack.
   */
  PotionItemBuilder(@NotNull final PotionMeta itemMeta, @NotNull final ItemStack itemStack) {
    super(itemMeta, itemStack);
  }

  /**
   * creates a new potion item builder instance.
   *
   * @param itemMeta the item meta to create.
   * @param itemStack the item stack to create.
   *
   * @return a newly created potion item builder instance.
   */
  @NotNull
  public static PotionItemBuilder from(@NotNull final PotionMeta itemMeta, @NotNull final ItemStack itemStack) {
    return new PotionItemBuilder(itemMeta, itemStack);
  }

  /**
   * creates potion item builder from serialized data.
   *
   * @param data the data to create.
   *
   * @return a newly created potion item builder instance.
   */
  @NotNull
  public static PotionItemBuilder from(@NotNull final TransformedData data) {
    return PotionItemBuilder.getDeserializer().apply(data).orElseThrow(() ->
      new IllegalArgumentException(String.format("The given data is incorrect!\n%s", data)));
  }

  /**
   * obtains the deserializer.
   *
   * @return deserializer.
   */
  @NotNull
  public static Deserializer getDeserializer() {
    return PotionItemBuilder.DESERIALIZER;
  }

  /**
   * adds a custom potion effect to this potion.
   *
   * @param effect the effect to add.
   * @param overwrite true if any existing effect of the same type should be overwritten.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder addCustomEffect(@NotNull final PotionEffect effect, final boolean overwrite) {
    if (Builder.VERSION >= 9) {
      this.getItemMeta().addCustomEffect(effect, overwrite);
    }
    return this.getSelf();
  }

  /**
   * adds a custom potion effect to this potion.
   *
   * @param effects the effects to add.
   * @param overwrite true if any existing effect of the same type should be overwritten.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder addCustomEffects(@NotNull final Collection<String> effects, final boolean overwrite) {
    if (Builder.VERSION >= 9) {
      effects.stream()
        .map(XPotion::parseEffect)
        .filter(Objects::nonNull)
        .map(XPotion.Effect::getEffect)
        .filter(Objects::nonNull)
        .forEach(effect -> this.addCustomEffect(effect, overwrite));
    }
    return this.getSelf();
  }

  /**
   * removes all custom potion effects from this potion.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder clearCustomEffects() {
    this.getItemMeta().clearCustomEffects();
    return this.getSelf();
  }

  @NotNull
  @Override
  public PotionItemBuilder getSelf() {
    return this;
  }

  @Override
  public void serialize(@NotNull final TransformedData data) {
    super.serialize(data);
    final ItemStack itemStack = this.getItemStack(false);
    final PotionMeta itemMeta = this.getItemMeta();
    if (Builder.VERSION >= 9) {
      final List<PotionEffect> customEffects = itemMeta.getCustomEffects();
      final ArrayList<String> effects = customEffects.stream()
        .map(effect ->
          String.format("%s, %d, %d", effect.getType().getName(), effect.getDuration(), effect.getAmplifier()))
        .collect(Collectors.toCollection(() -> new ArrayList<>(customEffects.size())));
      data.addAsCollection(Keys.CUSTOM_EFFECTS_KEY, effects, String.class);
      final PotionData potionData = itemMeta.getBasePotionData();
      data.add(Keys.BASE_EFFECT_KEY, String.format("%s, %s, %s",
        potionData.getType().name(), potionData.isExtended(), potionData.isUpgraded()), String.class);
      if (Builder.VERSION >= 11) {
        final Color color = itemMeta.getColor();
        if (itemMeta.hasColor() && color != null) {
          data.add(Keys.COLOR_KEY, color.asRGB(), int.class);
        }
      }
    } else if (itemStack.getDurability() != 0) {
      final Potion potion = Potion.fromItemStack(itemStack);
      data.add(Keys.LEVEL_KEY, potion.getLevel(), int.class);
      data.add(Keys.BASE_EFFECT_KEY, String.format("%s, %s, %s",
        potion.getType().name(), potion.hasExtendedDuration(), potion.isSplash()), String.class);
    }
  }

  /**
   * Removes a custom potion effect from this potion.
   *
   * @param type the potion effect type to remove
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder removeCustomEffect(@NotNull final PotionEffectType type) {
    this.getItemMeta().removeCustomEffect(type);
    return this.getSelf();
  }

  /**
   * sets the underlying potion data.
   *
   * @param data the data to set the base potion state to.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder setBasePotionData(@NotNull final PotionData data) {
    if (Builder.VERSION >= 9) {
      this.getItemMeta().setBasePotionData(data);
    }
    return this.getSelf();
  }

  /**
   * sets the underlying potion data.
   *
   * @param data the data to set the base potion state to.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder setBasePotionData(@NotNull final String data) {
    if (Builder.VERSION < 9) {
      return this.getSelf();
    }
    if (data.isEmpty()) {
      return this.getSelf();
    }
    final String[] split = data.split(",");
    PotionType type;
    try {
      type = PotionType.valueOf(split[0].trim().toUpperCase(Locale.ROOT));
    } catch (final Exception e) {
      type = PotionType.UNCRAFTABLE;
    }
    final boolean extended = split.length != 1 && Boolean.parseBoolean(split[1].trim());
    final boolean upgraded = split.length > 2 && Boolean.parseBoolean(split[2].trim());
    final PotionData potionData = new PotionData(type, extended, upgraded);
    return this.setBasePotionData(potionData);
  }

  /**
   * sets the underlying potion data.
   *
   * @param data the data to set the base potion state to.
   * @param level the level to set the base potion state to.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder setBasePotionData(@NotNull final String data, final int level) {
    if (Builder.VERSION >= 9) {
      return this.setBasePotionData(data);
    }
    if (data.isEmpty()) {
      return this.getSelf();
    }
    final String[] split = data.split(",");
    PotionType type;
    try {
      type = PotionType.valueOf(split[0].trim().toUpperCase(Locale.ROOT));
    } catch (final Exception e) {
      type = PotionType.SLOWNESS;
    }
    final boolean extended = split.length != 1 && Boolean.parseBoolean(split[1].trim());
    final boolean splash = split.length > 2 && Boolean.parseBoolean(split[2].trim());
    return this.setItemStack(new Potion(type, level, splash, extended).toItemStack(1));
  }

  /**
   * sets the potion color. A custom potion color will alter the display of the potion in an inventory slot.
   *
   * @param color the color to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder setColor(@Nullable final Color color) {
    if (Builder.VERSION >= 11) {
      this.getItemMeta().setColor(color);
    }
    return this.getSelf();
  }

  /**
   * sets the potion color. A custom potion color will alter the display of the potion in an inventory slot.
   *
   * @param color the color to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public PotionItemBuilder setColor(final int color) {
    return this.setColor(Color.fromRGB(color));
  }

  /**
   * moves a potion effect to the top of the potion effect list.
   * <p>
   * this causes the client to display the potion effect in the potion's name.
   *
   * @param type the type to move.
   *
   * @return {@code this} for builder chain.
   *
   * @deprecated since version 1.9, use {@link #setBasePotionData(PotionData)}.
   */
  @Deprecated
  @NotNull
  public PotionItemBuilder setMainEffect(@NotNull final PotionEffectType type) {
    this.getItemMeta().setMainEffect(type);
    return this.getSelf();
  }

  /**
   * a class that represents deserializer of {@link PotionMeta}.
   */
  public static final class Deserializer implements
    Function<@NotNull TransformedData, @NotNull Optional<PotionItemBuilder>> {

    @NotNull
    @Override
    public Optional<PotionItemBuilder> apply(@NotNull final TransformedData data) {
      final Optional<ItemStack> itemStack = Builder.getItemStackDeserializer().apply(data);
      if (!itemStack.isPresent()) {
        return Optional.empty();
      }
      final PotionItemBuilder builder = ItemStackBuilder.from(itemStack.get()).asPotion();
      final int level = data.get(Keys.LEVEL_KEY, int.class)
        .orElse(1);
      final Optional<String> baseEffect = data.get(Keys.BASE_EFFECT_KEY, String.class);
      final Optional<Integer> color = data.get(Keys.COLOR_KEY, int.class);
      final List<String> customEffects = data.getAsCollection(Keys.CUSTOM_EFFECTS_KEY, String.class)
        .orElse(Collections.emptyList());
      color.ifPresent(builder::setColor);
      builder.addCustomEffects(customEffects, true);
      baseEffect.ifPresent(s -> builder.setBasePotionData(s, level));
      return Optional.of(Builder.getItemMetaDeserializer(builder).apply(data));
    }
  }
}
