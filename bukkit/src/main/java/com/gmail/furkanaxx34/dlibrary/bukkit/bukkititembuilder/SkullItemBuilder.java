package com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder;

import com.cryptomorin.xseries.SkullUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.Keys;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.ReflectionUtils;
import com.gmail.furkanaxx34.dlibrary.reflection.RefConstructed;
import com.gmail.furkanaxx34.dlibrary.reflection.clazz.ClassOf;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * a class that represents skull item builders.
 * <p>
 * serialization:
 * <pre>
 * texture: string (texture can be username, textures.minecraft.net url or base64) (for 8 and newer versions)
 * </pre>
 */
public final class SkullItemBuilder extends Builder<SkullItemBuilder, SkullMeta> {

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
  SkullItemBuilder(@NotNull final SkullMeta itemMeta, @NotNull final ItemStack itemStack) {
    super(itemMeta, itemStack);
  }

  /**
   * creates a new skull item builder instance.
   *
   * @param itemMeta the item meta to create.
   * @param itemStack the item stack to create.
   *
   * @return a newly created skull item builder instance.
   */
  @NotNull
  public static SkullItemBuilder from(@NotNull final SkullMeta itemMeta, @NotNull final ItemStack itemStack) {
    return new SkullItemBuilder(itemMeta, itemStack);
  }

  /**
   * creates skull item builder from serialized data.
   *
   * @param data the data to create.
   *
   * @return a newly created skull item builder instance.
   */
  @NotNull
  public static SkullItemBuilder from(@NotNull final TransformedData data) {
    return SkullItemBuilder.getDeserializer().apply(data).orElseThrow(() ->
      new IllegalArgumentException(String.format("The given data is incorrect!\n%s", data)));
  }

  /**
   * obtains the deserializer.
   *
   * @return deserializer.
   */
  @NotNull
  public static Deserializer getDeserializer() {
    return SkullItemBuilder.DESERIALIZER;
  }

  @NotNull
  @Override
  public SkullItemBuilder getSelf() {
    return this;
  }

  @Override
  public void serialize(@NotNull final TransformedData data) {
    super.serialize(data);
    data.add(Keys.SKULL_TEXTURE_KEY, SkullUtils.getSkinValue(this.getItemMeta()), String.class);
  }

  /**
   * removes owner of the skull.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public SkullItemBuilder removeOwner() {
    if (Builder.VERSION < 13) {
      this.getItemMeta().setOwner(null);
    } else {
      this.getItemMeta().setOwningPlayer(null);
    }
    return this.getSelf();
  }

  /**
   * sets owner of the skull.
   *
   * @param texture the texture to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public SkullItemBuilder setOwner(@NotNull final String texture) {
    SkullUtils.applySkin(this.getItemMeta(), texture);
    final ClassOf<SkullMeta> cls = new ClassOf<>(this.getItemMeta());
    final Object profile = cls.getField("profile")
      .flatMap(refField -> refField.of(this.getItemMeta()).getValue())
      .orElseThrow(null);
    final Object nbt = new ClassOf<>(Objects.requireNonNull(ReflectionUtils.getNMSClass("NBTTagCompound"))).getConstructor()
      .flatMap(RefConstructed::create)
      .orElseThrow(null);
    final Object serialized = new ClassOf<>(Objects.requireNonNull(ReflectionUtils.getNMSClass("GameProfileSerializer"))).getMethodByName("serialize")
      .flatMap(refMethod -> refMethod.call(nbt, profile))
      .orElseThrow(null);
    cls.getField("serializedProfile")
      .map(refField -> refField.of(this.getItemMeta()))
      .ifPresent(refFieldExecuted -> refFieldExecuted.setValue(serialized));
    return this.getSelf();
  }

  /**
   * a class that represents deserializer of {@link SkullMeta}.
   */
  public static final class Deserializer implements
    Function<@NotNull TransformedData, @NotNull Optional<SkullItemBuilder>> {

    @NotNull
    @Override
    public Optional<SkullItemBuilder> apply(@NotNull final TransformedData data) {
      final Optional<ItemStack> itemStack = Builder.getItemStackDeserializer().apply(data);
      if (!itemStack.isPresent()) {
        return Optional.empty();
      }
      final SkullItemBuilder builder = ItemStackBuilder.from(itemStack.get()).asSkull();
      data.get(Keys.SKULL_TEXTURE_KEY, String.class)
        .ifPresent(builder::setOwner);
      return Optional.of(Builder.getItemMetaDeserializer(builder).apply(data));
    }
  }
}
