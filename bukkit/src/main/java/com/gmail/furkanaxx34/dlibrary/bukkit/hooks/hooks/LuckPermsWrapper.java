package com.gmail.furkanaxx34.dlibrary.bukkit.hooks.hooks;

import lombok.RequiredArgsConstructor;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.ChatMetaNode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.hooks.Wrapped;
import com.gmail.furkanaxx34.dlibrary.bukkit.hooks.Groups;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class LuckPermsWrapper implements Wrapped {

  @NotNull
  private final LuckPerms luckPerms;

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
  public long getEffectiveLimitedPermission(@NotNull final String permission, @NotNull final Player player,
                                            final long defaultValue) {
    return this.getEffectiveLimitedPermission(permission, player.getUniqueId(), defaultValue);
  }

  /**
   * gets player's limit in the given permission.
   * <p>
   * permission pattern should be like 'xxx.yyy.zzz.'
   *
   * @param permission the permission to get.
   * @param uniqueId the unique id of player to get.
   * @param defaultValue the default value to get.
   *
   * @return player's limit in the permission.
   */
  public long getEffectiveLimitedPermission(@NotNull final String permission, @NotNull final UUID uniqueId,
                                            final long defaultValue) {
    final AtomicLong calculatedLimit = new AtomicLong(defaultValue);
    final User user = this.luckPerms.getUserManager().getUser(uniqueId);
    if (user == null) {
      return calculatedLimit.get();
    }
    final Map<String, Boolean> permissionMap = user.getCachedData().getPermissionData().getPermissionMap();
    final List<String> permissions = permissionMap.entrySet().stream()
      .filter(Map.Entry::getValue)
      .map(Map.Entry::getKey)
      .collect(Collectors.toList());
    if (permissions.isEmpty()) {
      return calculatedLimit.get();
    }
    Groups.calculatePermissionLimit(permission, permissions, calculatedLimit);
    return calculatedLimit.get();
  }

  @NotNull
  public Optional<String> getGroup(@NotNull final String world, @NotNull final Player player) {
    return Optional.ofNullable(this.luckPerms.getUserManager().getUser(player.getUniqueId()))
      .map(User::getPrimaryGroup);
  }

  @NotNull
  public Optional<List<String>> getGroupPrefix(@NotNull final String world, @NotNull final String group) {
    return Optional.ofNullable(this.luckPerms.getGroupManager().getGroup(group))
      .map(gr -> new ArrayList<>(gr.getNodes(NodeType.PREFIX)))
      .map(nodes -> nodes.stream().map(ChatMetaNode::getMetaValue).collect(Collectors.toList()));
  }

  @NotNull
  public Optional<List<String>> getGroupSuffix(@NotNull final String world, @NotNull final String group) {
    return Optional.ofNullable(this.luckPerms.getGroupManager().getGroup(group))
      .map(gr -> new ArrayList<>(gr.getNodes(NodeType.SUFFIX)))
      .map(nodes -> nodes.stream().map(ChatMetaNode::getMetaValue).collect(Collectors.toList()));
  }

  @NotNull
  public Optional<List<String>> getUserPrefix(@NotNull final Player player) {
    return Optional.ofNullable(this.luckPerms.getUserManager().getUser(player.getUniqueId()))
      .map(gr -> new ArrayList<>(gr.getNodes(NodeType.PREFIX)))
      .map(nodes -> nodes.stream().map(ChatMetaNode::getMetaValue).collect(Collectors.toList()));
  }

  @NotNull
  public Optional<List<String>> getUserSuffix(@NotNull final Player player) {
    return Optional.ofNullable(this.luckPerms.getUserManager().getUser(player.getUniqueId()))
      .map(gr -> new ArrayList<>(gr.getNodes(NodeType.SUFFIX)))
      .map(nodes -> nodes.stream().map(ChatMetaNode::getMetaValue).collect(Collectors.toList()));
  }
}
