package com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder;

import com.cryptomorin.xseries.XItemStack;
import lombok.var;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.Keys;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * a class that represents crossbow item builders.
 * <p>
 * serialization:
 * <pre>
 * power: integer (firework's power) (for 8 and newer versions)
 *
 * firework: (main section)
 *   0:
 *     type: string (the effect's type) (for 8 and newer versions)
 *
 *     flicker: boolean (the effect flick or not) (for 8 and newer versions)
 *
 *     trail: boolean (the effect has trail or not) (for 8 and newer versions)
 *
 *     colors: (colors section)
 *       base: string list (the effect's base colors) (for 8 and newer versions)
 *         - 'red, green, blue'
 *
 *       fade: string list (the effect's fade colors) (for 8 and newer versions)
 *         - 'red, green, blue'
 * </pre>
 */
public final class FireworkItemBuilder extends Builder<FireworkItemBuilder, FireworkMeta> {

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
  FireworkItemBuilder(@NotNull final FireworkMeta itemMeta, @NotNull final ItemStack itemStack) {
    super(itemMeta, itemStack);
  }

  /**
   * creates a new firework item builder instance.
   *
   * @param itemMeta the item meta to create.
   * @param itemStack the item stack to create.
   *
   * @return a newly created firework item builder instance.
   */
  @NotNull
  public static FireworkItemBuilder from(@NotNull final FireworkMeta itemMeta, @NotNull final ItemStack itemStack) {
    return new FireworkItemBuilder(itemMeta, itemStack);
  }

  /**
   * creates firework item builder from serialized data.
   *
   * @param data the data to create.
   *
   * @return a newly created firework item builder instance.
   */
  @NotNull
  public static FireworkItemBuilder from(@NotNull final TransformedData data) {
    return FireworkItemBuilder.getDeserializer().apply(data).orElseThrow(() ->
      new IllegalArgumentException(String.format("The given data is incorrect!\n%s", data)));
  }

  /**
   * obtains the deserializer.
   *
   * @return deserializer.
   */
  @NotNull
  public static Deserializer getDeserializer() {
    return FireworkItemBuilder.DESERIALIZER;
  }

  /**
   * adds effect to the firework.
   *
   * @param effect the effect to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public FireworkItemBuilder addEffect(@NotNull final FireworkEffect effect) {
    this.getItemMeta().addEffect(effect);
    return this.getSelf();
  }

  /**
   * adds effect to the firework.
   *
   * @param effects the effects to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public FireworkItemBuilder addEffects(@NotNull final FireworkEffect... effects) {
    this.getItemMeta().addEffects(effects);
    return this.getSelf();
  }

  /**
   * adds effect to the firework.
   *
   * @param effects the effects to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public FireworkItemBuilder addEffects(@NotNull final Iterable<FireworkEffect> effects) {
    this.getItemMeta().addEffects(effects);
    return this.getSelf();
  }

  /**
   * clears effects of the firework.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public FireworkItemBuilder clearEffects() {
    this.getItemMeta().clearEffects();
    return this.getSelf();
  }

  @NotNull
  @Override
  public FireworkItemBuilder getSelf() {
    return this;
  }

  @Override
  public void serialize(@NotNull final TransformedData data) {
    super.serialize(data);
    final var itemMeta = this.getItemMeta();
    final var copy = data.copy();
    data.add(Keys.POWER_KEY, itemMeta.getPower(), int.class);
    final var effects = itemMeta.getEffects();
    IntStream.range(0, effects.size()).forEach(index -> {
      final var effect = effects.get(index);
      final var copyCopy = copy.copy();
      copyCopy.add(Keys.TYPE_KEY, effect.getType().name(), String.class);
      copyCopy.add(Keys.FLICKER_KEY, effect.hasFlicker(), boolean.class);
      copyCopy.add(Keys.TRAIL_KEY, effect.hasTrail(), boolean.class);
      final var fwBaseColors = effect.getColors();
      final var fwFadeColors = effect.getFadeColors();
      final var copyCopyCopy = copyCopy.copy();
      final var baseColors = fwBaseColors.stream()
        .map(color -> String.format("%d, %d, %d", color.getRed(), color.getGreen(), color.getBlue()))
        .collect(Collectors.toCollection(() -> new ArrayList<>(fwBaseColors.size())));
      final var fadeColors = fwFadeColors.stream()
        .map(color -> String.format("%d, %d, %d", color.getRed(), color.getGreen(), color.getBlue()))
        .collect(Collectors.toCollection(() -> new ArrayList<>(fwFadeColors.size())));
      copyCopyCopy.addAsCollection(Keys.BASE_KEY, baseColors, String.class);
      copyCopyCopy.addAsCollection(Keys.FADE_KEY, fadeColors, String.class);
      copyCopy.add(Keys.COLORS_KEY, copyCopyCopy);
      copy.add(String.valueOf(index), copyCopy);
    });
    data.add(Keys.FIREWORK_KEY, copy);
  }

  /**
   * removes effect from the firework.
   *
   * @param effectId the effect id to remove.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public FireworkItemBuilder removeEffect(final int effectId) {
    this.getItemMeta().removeEffect(effectId);
    return this.getSelf();
  }

  /**
   * sets power of the firework.
   *
   * @param power the power to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public FireworkItemBuilder setPower(final int power) {
    this.getItemMeta().setPower(power);
    return this.getSelf();
  }

  /**
   * a class that represents deserializer of {@link FireworkMeta}.
   */
  public static final class Deserializer implements
    Function<@NotNull TransformedData, @NotNull Optional<FireworkItemBuilder>> {

    @NotNull
    @Override
    public Optional<FireworkItemBuilder> apply(@NotNull final TransformedData data) {
      final var itemStack = Builder.getItemStackDeserializer().apply(data);
      if (!itemStack.isPresent()) {
        return Optional.empty();
      }
      final var builder = ItemStackBuilder.from(itemStack.get()).asFirework();
      final var power = data.get(Keys.POWER_KEY, int.class)
        .orElse(1);
      builder.setPower(power);
      final var fireworkSection = data.<String, Map>getAsMap(Keys.FIREWORK_KEY,
        GenericDeclaration.of(String.class),
        GenericDeclaration.of(Map.class, String.class, Object.class));
      fireworkSection.ifPresent(firework -> {
        final var fireworkBuilder = FireworkEffect.builder();
        firework.forEach((key, value) -> {
          final var copy = data.copy(value);
          final var flicker = copy.get(Keys.FLICKER_KEY, boolean.class)
            .orElse(false);
          final var trail = copy.get(Keys.TRAIL_KEY, boolean.class)
            .orElse(false);
          final var type = copy.get(Keys.TYPE_KEY, String.class)
            .map(s -> s.toUpperCase(Locale.ROOT));
          FireworkEffect.Type effectType;
          try {
            effectType = type.map(FireworkEffect.Type::valueOf)
              .orElse(FireworkEffect.Type.STAR);
          } catch (final Exception e) {
            effectType = FireworkEffect.Type.STAR;
          }
          fireworkBuilder.flicker(flicker)
            .trail(trail)
            .with(effectType);
          copy.getAsMap(Keys.COLORS_KEY, String.class, Object.class)
            .ifPresent(colorSection -> {
              final var copyCopy = copy.copy(colorSection);
              final var baseColors = copyCopy.getAsCollection(Keys.BASE_KEY, String.class)
                .map(strings -> strings.stream()
                  .map(XItemStack::parseColor)
                  .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
              final var fadeColors = copyCopy.getAsCollection(Keys.FADE_KEY, String.class)
                .map(strings -> strings.stream()
                  .map(XItemStack::parseColor)
                  .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
              fireworkBuilder.withColor(baseColors).withFade(fadeColors);
            });
          builder.addEffect(fireworkBuilder.build());
        });
      });
      return Optional.of(Builder.getItemMetaDeserializer(builder).apply(data));
    }
  }
}
