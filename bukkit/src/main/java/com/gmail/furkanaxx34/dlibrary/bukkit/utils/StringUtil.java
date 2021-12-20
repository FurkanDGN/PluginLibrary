package com.gmail.furkanaxx34.dlibrary.bukkit.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class StringUtil {

    @NotNull
    public String subString32(@NotNull final String str) {
        final int length = str.length();
        return str.substring(0, length >= 32 ? 31 : length);
    }
}
