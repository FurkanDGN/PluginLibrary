package com.gmail.furkanaxx34.dlibrary.transformer.resolvers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformResolver;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.FieldDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * a class that represents in memory wrapped resolver.
 */
public final class InMemoryWrappedResolver extends WrappedTransformResolver {

  /**
   * the map.
   */
  @NotNull
  private final Map<String, Object> map;

  /**
   * ctor.
   *
   * @param resolver the resolver.
   * @param map the map.
   */
  public InMemoryWrappedResolver(@NotNull final TransformResolver resolver, @NotNull final Map<String, Object> map) {
    super(resolver);
    this.map = map;
  }

  @NotNull
  @Override
  public List<String> getAllKeys() {
    return new ArrayList(this.map.keySet());
  }

  @NotNull
  @Override
  public Optional<Object> getValue(@NotNull final String path) {
    return Optional.ofNullable(this.map.get(path));
  }

  @NotNull
  @Override
  public <T> Optional<T> getValue(@NotNull final String path, @NotNull final Class<T> cls,
                                  @Nullable final GenericDeclaration genericType, @Nullable final Object defaultValue) {
    return this.getValue(path)
      .map(value -> this.deserialize(value, GenericDeclaration.of(value), cls, genericType, defaultValue));
  }

  @Override
  public boolean pathExists(@NotNull final String path) {
    return this.map.containsKey(path);
  }

  @Override
  public void removeValue(@NotNull final String path, @Nullable final GenericDeclaration genericType,
                          @Nullable final FieldDeclaration field) {
    this.map.remove(path);
  }

  @Override
  public void setValue(@NotNull final String path, @Nullable final Object value,
                       @Nullable final GenericDeclaration genericType, @Nullable final FieldDeclaration field) {
    this.map.put(path, value);
  }
}
