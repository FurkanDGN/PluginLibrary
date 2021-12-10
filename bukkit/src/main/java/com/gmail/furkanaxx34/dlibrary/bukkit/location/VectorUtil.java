package com.gmail.furkanaxx34.dlibrary.bukkit.location;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for {@link Vector}.
 */
public final class VectorUtil {

  /**
   * ctor.
   */
  private VectorUtil() {
  }

  /**
   * rotates the given vector around x axis for the given angle.
   *
   * @param vector the vector to rotate.
   * @param angle the angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateAroundAxisX(@NotNull final Vector vector, final double angle) {
    final double cos = StrictMath.cos(angle);
    final double sin = StrictMath.sin(angle);
    return vector
      .setY(vector.getY() * cos - vector.getZ() * sin)
      .setZ(vector.getY() * sin + vector.getZ() * cos);
  }

  /**
   * rotates the given vector around y axis for the given angle.
   *
   * @param vector the vector to rotate.
   * @param angle the angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateAroundAxisY(@NotNull final Vector vector, final double angle) {
    final double cos = StrictMath.cos(angle);
    final double sin = StrictMath.sin(angle);
    return vector
      .setX(vector.getX() * cos + vector.getZ() * sin)
      .setZ(vector.getX() * -sin + vector.getZ() * cos);
  }

  /**
   * rotates the given vector around z axis for the given angle.
   *
   * @param vector the vector to rotate.
   * @param angle the angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateAroundAxisZ(@NotNull final Vector vector, final double angle) {
    final double cos = StrictMath.cos(angle);
    final double sin = StrictMath.sin(angle);
    return vector
      .setX(vector.getX() * cos - vector.getY() * sin)
      .setY(vector.getX() * sin + vector.getY() * cos);
  }

  /**
   * rotates the given vector for the given x, y and z angle.
   *
   * @param vector the vector to rotate.
   * @param angleX the x angle to rotate.
   * @param angleY the y angle to rotate.
   * @param angleZ the z angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateVector(@NotNull final Vector vector, final double angleX, final double angleY,
                                    final double angleZ) {
    return VectorUtil.rotateAroundAxisZ(
      VectorUtil.rotateAroundAxisY(
        VectorUtil.rotateAroundAxisX(vector, angleX),
        angleY),
      angleZ);
  }

  /**
   * rotates the given vector around the given location.
   *
   * @param vector the vector to rotate.
   * @param location the location to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateVector(@NotNull final Vector vector, @NotNull final Location location) {
    return VectorUtil.rotateVector(vector, location.getYaw(), location.getPitch());
  }

  /**
   * rotates the given vector around the given yaw and pitch degrees.
   *
   * @param vector the vector to rotate.
   * @param yawDegrees the yaw degree to rotate.
   * @param pitchDegrees the pitch degree to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateVector(@NotNull final Vector vector, final double yawDegrees, final double pitchDegrees) {
    final double yaw = Math.toRadians(-1.0d * (yawDegrees + 90.0d));
    final double pitch = Math.toRadians(-pitchDegrees);
    final double cosYaw = StrictMath.cos(yaw);
    final double cosPitch = StrictMath.cos(pitch);
    final double sinYaw = StrictMath.sin(yaw);
    final double sinPitch = StrictMath.sin(pitch);
    final double initialX = vector.getX() * cosPitch - vector.getY() * sinPitch;
    final double initialY = vector.getY();
    final double initialZ = vector.getZ();
    final double x = initialX * cosPitch - initialY * sinPitch;
    return new Vector(
      initialZ * sinYaw + x * cosYaw,
      initialX * sinPitch + initialY * cosPitch,
      initialZ * cosYaw - x * sinYaw);
  }
}
