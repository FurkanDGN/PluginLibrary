package com.gmail.furkanaxx34.dlibrary.bukkit.version;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * gets minecraft version from package version of the server.
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class BukkitVersion {

  /**
   * pattern of the server text. the pattern looks like (major)_(minor)_R(micro).
   */
  @NotNull
  private static final Pattern PATTERN =
    Pattern.compile("v?(?<major>[0-9]+)[._](?<minor>[0-9]+)(?:[._]R(?<micro>[0-9]+))?(?<sub>.*)");

  /**
   * server version text.
   */
  @NotNull
  private final String version;

  /**
   * ctor.
   */
  public BukkitVersion() {
    this(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1));
  }

  /**
   * gets major part of the version.
   *
   * @return major part.
   */
  public int getMajor() {
    return this.get("major");
  }

  /**
   * gets micro part of the version.
   *
   * @return micro part.
   */
  public int getMicro() {
    return this.get("micro");
  }

  /**
   * gets minor part of the version.
   *
   * @return minor part.
   */
  public int getMinor() {
    return this.get("minor");
  }

  /**
   * obtains the raw version.
   *
   * @return raw version.
   */
  @NotNull
  public String getVersion() {
    return this.version;
  }

  /**
   * gets the part from the given key.
   *
   * @param key the key to get.
   *
   * @return the part of the given key.
   */
  private int get(@NotNull final String key) {
    final Matcher matcher = BukkitVersion.PATTERN.matcher(this.version);
    return matcher.matches() ? Integer.parseInt(matcher.group(key)) : 0;
  }
}
