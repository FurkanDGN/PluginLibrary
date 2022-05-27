package com.gmail.furkanaxx34.dlibrary.bukkit.utils;

import java.util.HashMap;

import lombok.var;
import org.bukkit.entity.Player;

@SuppressWarnings("SpellCheckingInspection")
public class CooldownUtil {

  private static final HashMap<String, Long> countdown = new HashMap<>();

  public static boolean check(Player player, String action, long delay) {
    final var key = String.format("%s-%s", player.getUniqueId(), action);
    final var now = System.currentTimeMillis();
    if (!countdown.containsKey(key)) {
      countdown.put(key, now + delay);
      return true;
    }
    final var timeMillis = countdown.get(key);
    if (now >= timeMillis) {
      countdown.put(key, now + delay);
      return true;
    }
    return false;
  }
}