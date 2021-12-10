package com.gmail.furkanaxx34.dlibrary.bukkit.hooks;

import com.gmail.furkanaxx34.dlibrary.bukkit.files.BukkitConfig;
import com.gmail.furkanaxx34.dlibrary.bukkit.hooks.hooks.*;
import com.gmail.furkanaxx34.dlibrary.hooks.Hook;
import com.gmail.furkanaxx34.dlibrary.hooks.Wrapped;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * a class that contains utility methods for hooks.
 */
@UtilityClass
public class Hooks {

  /**
   * the default hooks.
   */
  private static final Set<Hook<?>> DEFAULT_HOOKS = new HashSet<Hook<?>>(){{
    this.addAll(Arrays.asList(new LuckPermsHook(),
      new PlaceholderApiHook(),
      new VaultHook(),
      new HolographicDisplaysHook()
    ));
  }};

  /**
   * the wrappers.
   */
  private final Map<String, Wrapped> WRAPPERS = new ConcurrentHashMap<>();

  /**
   * adds the given hook to {@link #WRAPPERS}.
   *
   * @param hook the hook to add.
   */
  public void addHook(@NotNull final Hook<?> hook) {
    if (!hook.initiate()) {
      return;
    }
    final String id = hook.id();
    Hooks.WRAPPERS.put(id, hook.create());
    Bukkit.getConsoleSender().sendMessage(BukkitConfig.hookMessage
      .build("%hook%", () -> id));
  }

  /**
   * obtains the holographic displays wrapper.
   *
   * @return holographic displays wrapper.
   */
  @NotNull
  public Optional<HolographicDisplaysWrapper> getHolographicDisplays() {
    return Hooks.getWrapper(HolographicDisplaysHook.HOLOGRAPHIC_DISPLAYS_ID);
  }

  /**
   * obtains the holographic displays wrapper.
   *
   * @return holographic displays wrapper.
   */
  @NotNull
  public HolographicDisplaysWrapper getHolographicDisplaysOrThrow() {
    return Hooks.getHolographicDisplays().orElseThrow(null);
  }

  /**
   * obtains the luck perms wrapper.
   *
   * @return luck perms wrapper.
   */
  @NotNull
  public Optional<LuckPermsWrapper> getLuckPerms() {
    return Hooks.getWrapper(LuckPermsHook.LUCK_PERMS_ID);
  }

  /**
   * obtains the luck perms wrapper.
   *
   * @return luck perms wrapper.
   */
  @NotNull
  public LuckPermsWrapper getLuckPermsOrThrow() {
    return Hooks.getLuckPerms().orElseThrow(null);
  }

  /**
   * obtains the placeholder api wrapper.
   *
   * @return placeholder api wrapper.
   */
  @NotNull
  public Optional<PlaceholderAPIWrapper> getPlaceholderApi() {
    return Hooks.getWrapper(PlaceholderApiHook.PLACEHOLDER_API_ID);
  }

  /**
   * obtains the placeholder api wrapper.
   *
   * @return placeholder api wrapper.
   */
  @NotNull
  public PlaceholderAPIWrapper getPlaceholderApiOrThrow() {
    return Hooks.getPlaceholderApi().orElseThrow(null);
  }

  /**
   * obtains the vault wrapper.
   *
   * @return vault wrapper.
   */
  @NotNull
  public Optional<VaultWrapper> getVault() {
    return Hooks.getWrapper(VaultHook.VAULT_ID);
  }

  /**
   * obtains the vault wrapper.
   *
   * @return vault wrapper.
   */
  @NotNull
  public VaultWrapper getVaultOrThrow() {
    return Hooks.getVault().orElseThrow(null);
  }

  /**
   * loads the default hooks.
   */
  public void loadHooks() {
    Hooks.DEFAULT_HOOKS.forEach(Hooks::addHook);
  }

  /**
   * checks if the holographic displays is supported.
   *
   * @return {@code true} if holographic displays is supported.
   */
  public boolean supportsHolographicDisplays() {
    return Hooks.getHolographicDisplays().isPresent();
  }

  /**
   * checks if the luck perms is supported.
   *
   * @return {@code true} if luck perms is supported.
   */
  public boolean supportsLuckPerms() {
    return Hooks.getLuckPerms().isPresent();
  }

  /**
   * checks if the placeholder api is supported.
   *
   * @return {@code true} if placeholder api is supported.
   */
  public boolean supportsPlaceholderApi() {
    return Hooks.getPlaceholderApi().isPresent();
  }

  /**
   * checks if the vault is supported.
   *
   * @return {@code true} if vault is supported.
   */
  public boolean supportsVault() {
    return Hooks.getVault().isPresent();
  }

  /**
   * gets the wrapper implementation.
   *
   * @param wrappedId the wrapper id to obtain.
   * @param <T> type of the wrapper.
   *
   * @return wrapper instance.
   */
  @NotNull
  @SuppressWarnings("unchecked")
  private <T extends Wrapped> Optional<T> getWrapper(@NotNull final String wrappedId) {
    return Optional.ofNullable(Hooks.WRAPPERS.get(wrappedId))
      .map(o -> (T) o);
  }
}
