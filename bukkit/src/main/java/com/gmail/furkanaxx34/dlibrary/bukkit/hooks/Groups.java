package com.gmail.furkanaxx34.dlibrary.bukkit.hooks;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * a class that contains utility methods for group/permission plugins.
 */
@UtilityClass
public class Groups {

  /**
   * calculates the given permission's limit.
   *
   * @param permission the permission to calculate.
   * @param permissions the permissions to calculate.
   * @param calculatedLimit the calculated limit.
   */
  public void calculatePermissionLimit(@NotNull final String permission, @NotNull final List<String> permissions,
                                       @NotNull final AtomicLong calculatedLimit) {
    permissions.stream()
      .filter(perm -> !perm.startsWith("-"))
      .filter(perm -> perm.startsWith(permission))
      .map(perm -> perm.substring(perm.lastIndexOf(".") + 1))
      .filter(NumberUtil::isLong)
      .mapToLong(Integer::parseInt)
      .filter(limit -> limit > calculatedLimit.get())
      .forEach(calculatedLimit::set);
  }

  /**
   * checks if the given player has the given group.
   *
   * @param group the group to check.
   * @param player the player to check.
   *
   * @return {@code true} if the player has the group.
   */
  public boolean containsGroup(@NotNull final String group,
                               @NotNull final Player player) {
    if (Hooks.supportsLuckPerms()) {
      return Hooks.getLuckPermsOrThrow().getGroup(player.getWorld().getName(), player)
        .orElse("")
        .equalsIgnoreCase(group);
    }
    return false;
  }

  /**
   * gets player's limit in the given permission.
   * <p>
   * permission pattern should be like 'xxx.yyy.zzz.'
   *
   * @param permission the permission to get.
   * @param player the player to get.
   * @param defaultValue the default value to get.
   *
   * @return player's limit in the permission.
   */
  public long getEffectiveLimitedPermission(@NotNull final String permission,
                                            @NotNull final Player player, final long defaultValue) {
    if (Hooks.supportsLuckPerms()) {
      return Hooks.getLuckPermsOrThrow().getEffectiveLimitedPermission(permission, player, defaultValue);
    }
    final AtomicLong calculatedLimit = new AtomicLong(defaultValue);
    final List<String> permissions = player.getEffectivePermissions().stream()
      .filter(PermissionAttachmentInfo::getValue)
      .map(PermissionAttachmentInfo::getPermission)
      .collect(Collectors.toList());
    Groups.calculatePermissionLimit(permission, permissions, calculatedLimit);
    return calculatedLimit.get();
  }

  /**
   * gets the player's first group.
   *
   * @param player the player to get.
   *
   * @return player's first group.
   */
  @NotNull
  public Optional<String> getFirstGroup(@NotNull final Player player) {
    if (Hooks.supportsLuckPerms()) {
      return Hooks.getLuckPermsOrThrow().getGroup(player.getWorld().getName(), player);
    }
    return Optional.empty();
  }
}
