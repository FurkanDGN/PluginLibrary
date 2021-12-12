package com.gmail.furkanaxx34.dlibrary.bukkit.utils;

import com.gmail.furkanaxx34.dlibrary.bukkit.version.BukkitVersion;
import com.gmail.furkanaxx34.dlibrary.bukkit.version.CraftVersion;

/**
 * a class that represents versions.
 */
public final class Versions {

  /**
   * the server version but NMS way.
   */
  public static final BukkitVersion FULL = new BukkitVersion();

  /**
   * the server version but CB way.
   */
  public static final CraftVersion CRAFT_VERSION = new CraftVersion();

  /**
   * the server version as major.
   */
  public static final int MAJOR = Versions.FULL.getMajor();

  /**
   * the server version as micro.
   */
  public static final int MICRO = Versions.FULL.getMicro();

  /**
   * the server version as minor.
   */
  public static final int MINOR = Versions.FULL.getMinor();

  /**
   * the server version as major.
   */
  public static final int MAJOR_CRAFT = Versions.CRAFT_VERSION.getMajor();

  /**
   * the server version as micro.
   */
  public static final int MICRO_CRAFT = Versions.CRAFT_VERSION.getMicro();

  /**
   * the server version as minor.
   */
  public static final int MINOR_CRAFT = Versions.CRAFT_VERSION.getMinor();

  /**
   * ctor.
   */
  private Versions() {
  }
}
