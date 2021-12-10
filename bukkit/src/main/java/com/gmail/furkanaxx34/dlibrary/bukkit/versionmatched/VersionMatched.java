package com.gmail.furkanaxx34.dlibrary.bukkit.versionmatched;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.bukkit.version.BukkitVersion;
import com.gmail.furkanaxx34.dlibrary.reflection.RefConstructed;
import com.gmail.furkanaxx34.dlibrary.reflection.clazz.ClassOf;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * matches classes with your server version and choose the right class for instantiating instead of you.
 *
 * @param <T> the interface of classes.
 */
@RequiredArgsConstructor
public final class VersionMatched<T> {

  /**
   * version of the server, pattern must be like that 1_14_R1 1_13_R2.
   */
  @NotNull
  private final BukkitVersion version;

  /**
   * classes that match.
   */
  @NotNull
  private final Collection<VersionClass<T>> versionClasses;

  /**
   * ctor.
   *
   * @param version the version.
   * @param versionClasses the version classes.
   */
  public VersionMatched(@NotNull final String version, @NotNull final Collection<VersionClass<T>> versionClasses) {
    this(new BukkitVersion(version), versionClasses);
  }

  /**
   * ctor.
   *
   * @param version the version.
   * @param versionClasses the version classes.
   */
  @SafeVarargs
  public VersionMatched(@NotNull final String version, @NotNull final Class<? extends T>... versionClasses) {
    this(version,
      Arrays.stream(versionClasses)
        .map((Function<Class<? extends T>, VersionClass<T>>) VersionClass::new)
        .collect(Collectors.toList()));
  }

  /**
   * ctor.
   *
   * @param versionClasses the version classes.
   */
  @SafeVarargs
  public VersionMatched(@NotNull final Class<? extends T>... versionClasses) {
    this(new BukkitVersion(),
      Arrays.stream(versionClasses)
        .map((Function<Class<? extends T>, VersionClass<T>>) VersionClass::new)
        .collect(Collectors.toList()));
  }

  /**
   * gets instantiated class.
   *
   * @param types the types to get.
   *
   * @return constructor of class of {@link T}.
   */
  @Nullable
  public RefConstructed<T> of(@NotNull final Object... types) {
    try {
      final Class match = this.match();
      // noinspection unchecked
      return (RefConstructed<T>) new ClassOf<>(match).getConstructor(types)
        .orElseThrow(() -> new IllegalStateException(String.format("match() -> Couldn't find any constructor on \"%s\" version!",
          match.getSimpleName())));
    }catch (Throwable ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * matches classes.
   *
   * @return class that match or throw exception.
   */
  @NotNull
  private Class<? extends T> match() {
    return this.versionClasses.stream()
      .filter(versionClass -> versionClass.match(this.version))
      .map(VersionClass::getVersionClass)
      .findFirst()
      .orElseThrow(() ->
        new IllegalStateException(String.format("match() -> Couldn't find any matched class on \"%s\" version!",
          this.version.getVersion())));
  }
}
