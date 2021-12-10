package com.gmail.furkanaxx34.dlibrary.transformer.resolvers;

import com.gmail.furkanaxx34.dlibrary.transformer.TransformResolver;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.FieldDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.TransformedObjectDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * a class that represents in memory resolver.
 */
public final class InMemoryResolver extends TransformResolver {

  /**
   * the map.
   */
  @NotNull
  private final Map<String, Object> map = new LinkedHashMap<>();

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

  @Override
  public void load(@NotNull final InputStream inputStream, @NotNull final TransformedObjectDeclaration declaration) {
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

  @Override
  public void write(@NotNull final OutputStream outputStream, @NotNull final TransformedObjectDeclaration declaration) {
  }
}
