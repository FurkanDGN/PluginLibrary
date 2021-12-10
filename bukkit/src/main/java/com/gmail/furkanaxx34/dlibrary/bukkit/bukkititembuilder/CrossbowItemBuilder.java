package com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder;

import lombok.var;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.ItemStackUtil;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.Keys;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * a class that represents crossbow item builders.
 * <p>
 * serialization
 * <pre>
 * projectiles: (main section)
 *   0: (item section)
 *     material: DIAMOND
 * </pre>
 */
public final class CrossbowItemBuilder extends Builder<CrossbowItemBuilder, CrossbowMeta> {

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
  CrossbowItemBuilder(@NotNull final CrossbowMeta itemMeta, @NotNull final ItemStack itemStack) {
    super(itemMeta, itemStack);
  }

  /**
   * creates a new crossbow item builder instance.
   *
   * @param itemMeta the item meta to create.
   * @param itemStack the item stack to create.
   *
   * @return a newly created crossbow item builder instance.
   */
  @NotNull
  public static CrossbowItemBuilder from(@NotNull final CrossbowMeta itemMeta, @NotNull final ItemStack itemStack) {
    return new CrossbowItemBuilder(itemMeta, itemStack);
  }

  /**
   * creates crossbow item builder from serialized data.
   *
   * @param data the data to create.
   *
   * @return a newly created crossbow item builder instance.
   */
  @NotNull
  public static CrossbowItemBuilder from(@NotNull final TransformedData data) {
    return CrossbowItemBuilder.getDeserializer().apply(data).orElseThrow(() ->
      new IllegalArgumentException(String.format("The given data is incorrect!\n%s", data)));
  }

  /**
   * obtains the deserializer.
   *
   * @return deserializer.
   */
  @NotNull
  public static Deserializer getDeserializer() {
    return CrossbowItemBuilder.DESERIALIZER;
  }

  /**
   * adds charged projectile to the crossbow.
   *
   * @param projectiles the projectileS to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CrossbowItemBuilder addChargedProjectile(@NotNull final ItemStack... projectiles) {
    final var itemMeta = this.getItemMeta();
    for (final var projectile : projectiles) {
      itemMeta.addChargedProjectile(projectile);
    }
    return this.getSelf();
  }

  @NotNull
  @Override
  public CrossbowItemBuilder getSelf() {
    return this;
  }

  @Override
  public void serialize(@NotNull final TransformedData data) {
    super.serialize(data);
    final var projectiles = data.copy();
    final var chargedProjectiles = this.getItemMeta().getChargedProjectiles();
    IntStream.range(0, chargedProjectiles.size()).forEach(index -> {
      final var projectile = chargedProjectiles.get(index);
      final var section = data.copy();
      ItemStackUtil.serialize(projectile, section);
      projectiles.add(String.valueOf(index), section);
    });
    data.add(Keys.PROJECTILES_KEY, projectiles);
  }

  /**
   * sets charged projectile of the item.
   *
   * @param projectiles the projectiles to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CrossbowItemBuilder setChargedProjectiles(@Nullable final ItemStack... projectiles) {
    return this.setChargedProjectiles(new ArrayList<ItemStack>(){{
      for (final ItemStack projectile : projectiles) {
        add(projectile);
      }
    }});
  }

  /**
   * sets charged projectile of the item.
   *
   * @param projectiles the projectiles to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public CrossbowItemBuilder setChargedProjectiles(@Nullable final List<ItemStack> projectiles) {
    this.getItemMeta().setChargedProjectiles(projectiles);
    return this.getSelf();
  }

  /**
   * a class that represents deserializer of {@link CrossbowMeta}.
   */
  public static final class Deserializer implements
    Function<@NotNull TransformedData, @NotNull Optional<CrossbowItemBuilder>> {

    @NotNull
    @Override
    public Optional<CrossbowItemBuilder> apply(@NotNull final TransformedData data) {
      final var itemStack = Builder.getItemStackDeserializer().apply(data);
      if (!itemStack.isPresent()) {
        return Optional.empty();
      }
      final var builder = ItemStackBuilder.from(itemStack.get()).asCrossbow();
      final var projectiles = new ArrayList<ItemStack>();
      final var indexes = data.getAsMap(Keys.PROJECTILES_KEY, String.class, ItemStack.class);
      indexes.ifPresent(index -> index.forEach((key, value) -> projectiles.add(value)));
      builder.setChargedProjectiles(projectiles);
      return Optional.of(Builder.getItemMetaDeserializer(builder).apply(data));
    }
  }
}
