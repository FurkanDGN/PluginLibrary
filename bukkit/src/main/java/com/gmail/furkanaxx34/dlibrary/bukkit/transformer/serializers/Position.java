package com.gmail.furkanaxx34.dlibrary.bukkit.transformer.serializers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.transformer.ObjectSerializer;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedData;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;

import java.util.Objects;
import java.util.Optional;

/**
 * a class that represents positions.
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public final class Position {

  /**
   * the world name.
   */
  @NotNull
  private final String worldName;

  /**
   * the x.
   */
  private final double x;

  /**
   * the y.
   */
  private final double y;

  /**
   * the z.
   */
  private final double z;

  /**
   * the pitch.
   */
  private float pitch;

  /**
   * the yaw.
   */
  private float yaw;

  /**
   * ctor.
   *
   * @param worldName the world name.
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public Position(@NotNull final String worldName, final int x, final int y, final int z) {
    this(worldName, (double) x, y, z);
  }

  /**
   * ctor.
   *
   * @param worldName the world name.
   * @param x the x.
   * @param y the y.
   * @param z the z.
   * @param yaw the yaw.
   * @param pitch the pitch.
   */
  public Position(@NotNull final String worldName, final int x, final int y, final int z, final float yaw,
                  final float pitch) {
    this(worldName, (double) x, y, z, yaw, pitch);
  }

  /**
   * obtains the location.
   *
   * @return the location.
   */
  @NotNull
  public Optional<Location> getLocation() {
    return Optional.ofNullable(Bukkit.getWorld(this.worldName))
      .map(world ->
        new Location(world,
          this.x, this.y, this.z, this.yaw, this.pitch));
  }

  /**
   * obtains the location.
   *
   * @return the location.
   *
   * @throws NullPointerException if the currently running server has not a world called {@link #worldName} world.
   */
  @NotNull
  public Location getLocationThrown() {
    return new Location(Objects.requireNonNull(Bukkit.getWorld(this.worldName), "world"),
      this.x, this.y, this.z, this.yaw, this.pitch);
  }

  /**
   * a class that represents serializer of {@link Position}.
   */
  public static final class Serializer implements ObjectSerializer<Position> {

    @NotNull
    @Override
    public Optional<Position> deserialize(@NotNull final TransformedData transformedData,
                                          @Nullable final GenericDeclaration declaration) {
      return transformedData.get("world", String.class).map(world ->
        new Position(
          world,
          transformedData.get("x", double.class).orElse(0.0d),
          transformedData.get("y", double.class).orElse(0.0d),
          transformedData.get("z", double.class).orElse(0.0d),
          transformedData.get("yaw", float.class).orElse(0.0f),
          transformedData.get("pitch", float.class).orElse(0.0f)));
    }

    @NotNull
    @Override
    public Optional<Position> deserialize(@NotNull final Position field,
                                          @NotNull final TransformedData transformedData,
                                          @Nullable final GenericDeclaration declaration) {
      return this.deserialize(transformedData, declaration);
    }

    @Override
    public void serialize(@NotNull final Position position, @NotNull final TransformedData transformedData) {
      transformedData.add("world", position.getWorldName());
      transformedData.add("x", position.getX());
      transformedData.add("y", position.getY());
      transformedData.add("z", position.getZ());
      transformedData.add("yaw", position.getYaw());
      transformedData.add("pitch", position.getPitch());
    }

    @Override
    public boolean supports(@NotNull final Class<?> cls) {
      return cls == Position.class;
    }
  }
}
