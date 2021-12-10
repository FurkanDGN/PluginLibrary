package com.gmail.furkanaxx34.dlibrary.bukkit.transformer.serializers;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.ItemStackBuilder;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.util.ItemStackUtil;
import com.gmail.furkanaxx34.dlibrary.transformer.ObjectSerializer;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;

import java.util.Optional;

/**
 * a class that represents serializer of {@link ItemStackBuilder}.
 */
public final class ItemStackSerializer implements ObjectSerializer<ItemStack> {

  @NotNull
  @Override
  public Optional<ItemStack> deserialize(@NotNull final TransformedData transformedData,
                                         @Nullable final GenericDeclaration declaration) {
    return ItemStackUtil.deserialize(transformedData);
  }

  @NotNull
  @Override
  public Optional<ItemStack> deserialize(@NotNull final ItemStack field,
                                         @NotNull final TransformedData transformedData,
                                         @Nullable final GenericDeclaration declaration) {
    return this.deserialize(transformedData, declaration);
  }

  @Override
  public void serialize(@NotNull final ItemStack itemStack, @NotNull final TransformedData transformedData) {
    ItemStackUtil.serialize(itemStack, transformedData);
  }

  @Override
  public boolean supports(@NotNull final Class<?> cls) {
    return cls == ItemStack.class;
  }
}
