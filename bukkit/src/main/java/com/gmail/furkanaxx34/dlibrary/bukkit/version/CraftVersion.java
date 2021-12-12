package com.gmail.furkanaxx34.dlibrary.bukkit.version;

import com.gmail.furkanaxx34.dlibrary.bukkit.smartinventory.util.ReflectionUtils;
import com.gmail.furkanaxx34.dlibrary.reflection.RefMethod;
import com.gmail.furkanaxx34.dlibrary.reflection.clazz.ClassOf;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * gets minecraft version from package version of the server.
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class CraftVersion {

    // SharedConstants.getGameVersion().getName();
    @NotNull
    private static final Pattern PATTERN =
            Pattern.compile("(?<major>[0-9]+)[.](?<minor>[0-9]+)(?:[.](?<micro>[0-9]+))?+");

    /**
     * server version text.
     */
    @NotNull
    private final String version;

    /**
     * ctor.
     */
    public CraftVersion() {
        this(
                new ClassOf<>(
                        Objects.requireNonNull(ReflectionUtils.getCraftClass("CraftServer")))
                        .getField("console")
                        .map(refField -> refField.of(Bukkit.getServer()).getValue()
                                .map(o -> new ClassOf<>(o.getClass()).getMethod("getVersion")
                                        .map(refMethod1 -> refMethod1.of(o).call()
                                                .map(o1 -> (String) o1).orElse(""))
                                        .orElse(""))
                                .orElse(""))
                        .orElse("")
        );
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
        final Matcher matcher = CraftVersion.PATTERN.matcher(this.version);
        return matcher.matches() ? Integer.parseInt(matcher.group(key)) : 0;
    }
}
