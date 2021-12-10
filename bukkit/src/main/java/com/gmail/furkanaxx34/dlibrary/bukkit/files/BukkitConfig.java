package com.gmail.furkanaxx34.dlibrary.bukkit.files;

import org.jetbrains.annotations.NotNull;
import com.gmail.furkanaxx34.dlibrary.bukkit.color.XColor;
import com.gmail.furkanaxx34.dlibrary.bukkit.transformer.resolvers.BukkitSnakeyaml;
import com.gmail.furkanaxx34.dlibrary.replaceable.RpString;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedObject;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformerPool;
import com.gmail.furkanaxx34.dlibrary.transformer.annotations.Comment;
import com.gmail.furkanaxx34.dlibrary.transformer.annotations.Names;

import java.io.File;

/**
 * a class that represents Infumia Lib's config.
 */
@Names(strategy = Names.Strategy.HYPHEN_CASE, modifier = Names.Modifier.TO_LOWER_CASE)
public final class BukkitConfig extends TransformedObject {

  /**
   * the hook message.
   */
  @Comment("Hooking message for each plugin/library.")
  public static RpString hookMessage = RpString.from("%hook% is hooking.")
    .regex("%hook%")
    .map(XColor::colorize);

  /**
   * ctor.
   */
  private BukkitConfig() {
  }

  /**
   * loads the config.
   *
   * @param folder the folder to load.
   */
  public static void loadConfig(@NotNull final File folder) {
    TransformerPool.create(new BukkitConfig())
      .withFile(new File(folder, "bukkit.yml"))
      .withResolver(new BukkitSnakeyaml())
      .initiate();
  }
}
