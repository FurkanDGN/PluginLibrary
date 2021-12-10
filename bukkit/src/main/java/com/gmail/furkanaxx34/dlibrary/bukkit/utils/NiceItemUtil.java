package com.gmail.furkanaxx34.dlibrary.bukkit.utils;

import com.cryptomorin.xseries.XMaterial;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.bukkititembuilder.ItemStackBuilder;

import java.util.Objects;

@UtilityClass
public class NiceItemUtil {

  @NotNull
  public ItemStack getGreenItemStack() {
    if (Versions.MAJOR < 13) {
      return Objects.requireNonNull(XMaterial.GREEN_WOOL.parseItem(), "green wool");
    }
    return ItemStackBuilder.from(XMaterial.GREEN_CONCRETE).getItemStack();
  }

  @NotNull
  public ItemStack getRedItemStack() {
    if (Versions.MAJOR < 13) {
      return Objects.requireNonNull(XMaterial.RED_WOOL.parseItem(), "red wool");
    }
    return ItemStackBuilder.from(XMaterial.RED_CONCRETE).getItemStack();
  }

  @NotNull
  public ItemStack getWhiteItemStack() {
    if (Versions.MAJOR < 13) {
      return Objects.requireNonNull(XMaterial.WHITE_WOOL.parseItem(), "white wool");
    }
    return ItemStackBuilder.from(XMaterial.WHITE_CONCRETE).getItemStack();
  }

  @NotNull
  public ItemStack getYellowItemStack() {
    if (Versions.MAJOR < 13) {
      return Objects.requireNonNull(XMaterial.YELLOW_WOOL.parseItem(), "yello wool");
    }
    return ItemStackBuilder.from(XMaterial.YELLOW_CONCRETE).getItemStack();
  }
}
