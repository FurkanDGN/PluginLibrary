package com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.*;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.Optional;

/**
 * a class that contains utility methods for {@link ItemStack}.
 */
public final class ItemStackUtil {

  /**
   * ctor.
   */
  private ItemStackUtil() {
  }

  /**
   * deserializes the given data into item stack.
   *
   * @param data the data to serialize.
   *
   * @return serialized object.
   */
  @NotNull
  public static Optional<ItemStack> deserialize(@NotNull final TransformedData data) {
    final Optional<ItemStackBuilder> builderOptional = Builder.getSimpleItemStackDeserializer().apply(data);
    if (!builderOptional.isPresent()) {
      return Optional.empty();
    }
    final ItemStackBuilder builder = builderOptional.get();
    if (builder.isFirework()) {
      return FireworkItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isLeatherArmor()) {
      return LeatherArmorItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isMap()) {
      return MapItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isPotion()) {
      return PotionItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isBanner()) {
      return BannerItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isBook()) {
      return BookItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isCrossbow()) {
      return CrossbowItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isSkull()) {
      return SkullItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    } else if (builder.isSpawnEgg()) {
      return SpawnEggItemBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
    }
    return ItemStackBuilder.getDeserializer().apply(data).map(Buildable::getItemStack);
  }

  /**
   * parses the given material string into a new material.
   *
   * @param materialString the material string to parse.
   *
   * @return parsed material.
   */
  @NotNull
  public static Optional<Material> parseMaterial(@NotNull final String materialString) {
    if (Builder.VERSION <= 7) {
      return Optional.ofNullable(Material.getMaterial(materialString));
    }
    final Optional<XMaterial> xMaterial = XMaterial.matchXMaterial(materialString);
    if (!xMaterial.isPresent()) {
      return Optional.empty();
    }
    final Optional<Material> material = Optional.ofNullable(xMaterial.get().parseMaterial());
    if (!material.isPresent() ) {
      return Optional.empty();
    }
    return material;
  }

  /**
   * serializes the given item stack into map.
   *
   * @param itemStack the item stack to serialize.
   * @param data the data to serialize.
   */
  public static void serialize(@NotNull final ItemStack itemStack, @NotNull final TransformedData data) {
    ItemStackUtil.serialize(ItemStackBuilder.from(itemStack), data);
  }

  /**
   * serializes the given item stack into map.
   *
   * @param builder the item stack to builder.
   * @param data the data to serialize.
   */
  public static void serialize(@NotNull final Builder<?, ?> builder, @NotNull final TransformedData data) {
    if (builder.isFirework()) {
      builder.asFirework().serialize(data);
    } else if (builder.isLeatherArmor()) {
      builder.asLeatherArmor().serialize(data);
    } else if (builder.isMap()) {
      builder.asMap().serialize(data);
    } else if (builder.isPotion()) {
      builder.asPotion().serialize(data);
    } else if (builder.isBanner()) {
      builder.asBanner().serialize(data);
    } else if (builder.isBook()) {
      builder.asBook().serialize(data);
    } else if (builder.isCrossbow()) {
      builder.asCrossbow().serialize(data);
    } else if (builder.isSkull()) {
      builder.asSkull().serialize(data);
    } else if (builder.isSpawnEgg()) {
      builder.asSpawnEgg().serialize(data);
    } else {
      builder.serialize(data);
    }
  }
}
