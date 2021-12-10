package com.gmail.furkanaxx34.dlibrary.bukkit;

import com.gmail.furkanaxx34.dlibrary.bukkit.color.CustomColors;
import com.gmail.furkanaxx34.dlibrary.bukkit.files.BukkitConfig;
import com.gmail.furkanaxx34.dlibrary.bukkit.hooks.Hooks;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.SmartInventory;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.manager.BasicSmartInventory;
import com.gmail.furkanaxx34.dlibrary.bukkit.utils.TaskUtilities;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Objects;

public class DLibrary {

    /**
     * the instance.
     */
    @Nullable
    private static DLibrary instance;

    /**
     * the inventory.
     */
    @Nullable
    private final SmartInventory inventory;

    DLibrary(@NotNull Plugin plugin) {
        this.inventory = new BasicSmartInventory(plugin);
        this.inventory.init();
        BukkitConfig.loadConfig(new File(plugin.getDataFolder().getParentFile() + File.separator + "DLibrary"));
    }

    public static void initialize(Plugin plugin) {
        instance = new DLibrary(plugin);
        CustomColors.registerAll();
        TaskUtilities.init(plugin);
        Hooks.loadHooks();
    }

    /**
     * obtains the instance.
     *
     * @return instance.
     */
    @NotNull
    public static DLibrary getInstance() {
        return Objects.requireNonNull(DLibrary.instance, "not initiated");
    }

    /**
     * obtains the inventory.
     *
     * @return inventory.
     */
    @NotNull
    public static SmartInventory getInventory() {
        return Objects.requireNonNull(DLibrary.getInstance().inventory, "not initiated");
    }


}
