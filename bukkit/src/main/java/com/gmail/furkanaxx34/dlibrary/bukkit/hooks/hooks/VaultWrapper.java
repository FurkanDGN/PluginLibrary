package com.gmail.furkanaxx34.dlibrary.bukkit.hooks.hooks;

import lombok.RequiredArgsConstructor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.hooks.Wrapped;

@RequiredArgsConstructor
public final class VaultWrapper implements Wrapped {

  @NotNull
  private final Economy economy;

  public void addMoney(@NotNull final Player player, final double money) {
    this.economy.depositPlayer(player, money);
  }

  public double getMoney(@NotNull final Player player) {
    return this.economy.getBalance(player);
  }

  public void removeMoney(@NotNull final Player player, final double money) {
    if (this.getMoney(player) < money) {
      return;
    }
    this.economy.withdrawPlayer(player, money);
  }
}
