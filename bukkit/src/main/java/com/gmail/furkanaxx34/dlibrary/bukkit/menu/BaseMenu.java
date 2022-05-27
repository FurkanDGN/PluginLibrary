package com.gmail.furkanaxx34.dlibrary.bukkit.menu;

import com.gmail.furkanaxx34.dlibrary.bukkit.DLibrary;
import com.gmail.furkanaxx34.dlibrary.bukkit.element.FileElement;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.InventoryProvider;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.Page;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.CloseEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.InitEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.event.abs.TickEvent;
import com.gmail.furkanaxx34.dlibrary.bukkit.transformer.resolvers.BukkitSnakeyaml;
import com.gmail.furkanaxx34.dlibrary.bukkit.transformer.serializers.ItemStackSerializer;
import com.gmail.furkanaxx34.dlibrary.bukkit.transformer.serializers.SentTitle;
import com.gmail.furkanaxx34.dlibrary.bukkit.utils.StringUtil;
import com.gmail.furkanaxx34.dlibrary.bukkit.utils.Versions;
import com.gmail.furkanaxx34.dlibrary.replaceable.RpString;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedObject;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformerPool;
import com.gmail.furkanaxx34.dlibrary.transformer.annotations.Exclude;
import com.gmail.furkanaxx34.dlibrary.transformer.annotations.Names;
import lombok.var;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.function.Consumer;

public abstract class BaseMenu extends TransformedObject {

    @Nullable
    @Exclude
    private TransformedObject instance;

    public final void load(Plugin plugin) {
        if (this.instance == null) {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }
            this.instance = TransformerPool.create(this)
              .withFile(new File(
                plugin.getDataFolder(),
                String.format(
                  "%s.yml",
                  Names.Strategy.HYPHEN_CASE.apply(this.getClass().getSimpleName()).toLowerCase(Locale.ROOT)))
              )
              .withResolver(new BukkitSnakeyaml())
              .withTransformPack(registry -> registry
                .withSerializers(
                  new SentTitle.Serializer(),
                  new FileElement.Serializer(),
                  new ItemStackSerializer()
                ));
        }
        this.instance.initiate();
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final RpString title) {
        this.openPage(player, row, title, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final RpString title,
                               boolean async) {
        this.openPage(player, row, Names.Strategy.HYPHEN_CASE.apply(this.getClass().getSimpleName()).toLowerCase(Locale.ROOT), title.build(), async);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final RpString title,
                               @Nullable final InventoryProvider provider) {
        this.openPage(player, row, title, provider, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final RpString title,
                               @Nullable final InventoryProvider provider,
                               boolean async) {
        this.openPage(player, row, Names.Strategy.HYPHEN_CASE.apply(this.getClass().getSimpleName()).toLowerCase(Locale.ROOT), title.build(), null, null, null, provider, async);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title) {
        this.openPage(player, row, id, title, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               boolean async) {
        this.openPage(player, row, id, title.build(), async);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               @Nullable final InventoryProvider provider) {
        this.openPage(player, row, id, title, provider, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               @Nullable final InventoryProvider provider,
                               boolean async) {
        this.openPage(player, row, id, title.build(), null, null, null, provider, async);
    }

    public void openPage(final Player player, final int row, final String id, final String title) {
        this.openPage(player, row, id, title, false);
    }

    public void openPage(final Player player, final int row, final String id, final String title, boolean async) {
        this.openPage(player, row, id, title, null, null, null, null, async);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               @Nullable final Consumer<InitEvent> initConsumer) {
        this.openPage(player, row, id, title, initConsumer, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               @Nullable final Consumer<InitEvent> initConsumer,
                               boolean async) {
        this.openPage(player, row, id, title.build(), initConsumer, null, null, null, async);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               @Nullable final Consumer<InitEvent> initConsumer,
                               @Nullable final Consumer<TickEvent> tickConsumer) {
        this.openPage(player, row, id, title, initConsumer, tickConsumer, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final RpString title,
                               @Nullable final Consumer<InitEvent> initConsumer,
                               @Nullable final Consumer<TickEvent> tickConsumer,
                               boolean async) {
        this.openPage(player, row, id, title.build(), initConsumer, tickConsumer, null, null, async);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final String title,
                               @Nullable final Consumer<InitEvent> initConsumer,
                               @Nullable final Consumer<TickEvent> tickConsumer,
                               @Nullable final Consumer<CloseEvent> closeConsumer,
                               @Nullable final InventoryProvider provider) {
        this.openPage(player, row, id, title, initConsumer, tickConsumer, closeConsumer, provider, false);
    }

    public final void openPage(@NotNull final Player player,
                               final int row,
                               @NotNull final String id,
                               @NotNull final String title,
                               @Nullable final Consumer<InitEvent> initConsumer,
                               @Nullable final Consumer<TickEvent> tickConsumer,
                               @Nullable final Consumer<CloseEvent> closeConsumer,
                               @Nullable final InventoryProvider provider,
                               boolean async) {
        final Page page = Page.build(DLibrary.getInventory())
          .title(Versions.MINOR <= 8 ? StringUtil.subString32(title) : title)
          .row(row)
          .id(id)
          .async(async);

        if (provider != null)
            page.provider(provider);
        else {
            if (tickConsumer != null) {
                page.whenTick(tickConsumer);
            }
            if (initConsumer != null) {
                page.whenInit(initConsumer);
            }
        }
        if (closeConsumer != null) {
            page.whenClose(closeConsumer);
        }
        this.preparePage(page);
        page.open(player);
    }

    private void placeElements(Page page, Field[] fields) {
        try {
            for (final Field field : fields) {
                if (field.getType().isAssignableFrom(FileElement.class)) {
                    FileElement element = (FileElement) field.get(FileElement.class);
                    page.whenInit(element::place);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void preparePage(final Page page) {
        this.placeElements(page, this.getClass().getFields());
        page.whenEmptyClick(event -> {
            var clicked = event.getEvent().getClickedInventory();
            var playerInv = event.getEvent().getWhoClicked().getInventory();
            if (clicked != null && !clicked.equals(playerInv)) {
                event.cancel();
            }
        });
    }
}