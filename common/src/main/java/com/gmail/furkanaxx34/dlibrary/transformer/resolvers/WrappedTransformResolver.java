package com.gmail.furkanaxx34.dlibrary.transformer.resolvers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformPack;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformRegistry;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformResolver;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.FieldDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.TransformedObjectDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.exceptions.TransformException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * an abstract class that represents wrapped transform resolvers.
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WrappedTransformResolver extends TransformResolver {

  /**
   * the delegate.
   */
  @NotNull
  private final TransformResolver delegate;

  @NotNull
  @Override
  public TransformRegistry getRegistry() {
    return this.delegate.getRegistry();
  }

  @Nullable
  @Override
  public <T> T deserialize(@Nullable final Object object, @Nullable final GenericDeclaration genericSource,
                           @NotNull final Class<T> targetClass, @Nullable final GenericDeclaration genericTarget,
                           @Nullable final Object defaultValue)
    throws TransformException {
    return this.delegate.deserialize(object, genericSource, targetClass, genericTarget, defaultValue);
  }

  @NotNull
  @Override
  public List<String> getAllKeys() {
    return this.delegate.getAllKeys();
  }

  @NotNull
  @Override
  public Optional<Object> getValue(@NotNull final String path) {
    return this.delegate.getValue(path);
  }

  @NotNull
  @Override
  public <T> Optional<T> getValue(@NotNull final String path, @NotNull final Class<T> cls,
                                  @Nullable final GenericDeclaration genericType, @Nullable final Object defaultValue) {
    return this.delegate.getValue(path, cls, genericType, defaultValue);
  }

  @Override
  public boolean isToListObject(@NotNull final Object object, @Nullable final GenericDeclaration declaration) {
    return this.delegate.isToListObject(object, declaration);
  }

  @Override
  public boolean isToStringObject(@NotNull final Object object, @Nullable final GenericDeclaration declaration) {
    return this.delegate.isToStringObject(object, declaration);
  }

  @Override
  public boolean isValid(@NotNull final FieldDeclaration declaration, @Nullable final Object value) {
    return this.delegate.isValid(declaration, value);
  }

  @Override
  public void load(@NotNull final InputStream inputStream, @NotNull final TransformedObjectDeclaration declaration)
    throws Exception {
    this.delegate.load(inputStream, declaration);
  }

  @Override
  public boolean pathExists(@NotNull final String path) {
    return this.delegate.pathExists(path);
  }

  @Override
  public void removeValue(@NotNull final String path, @Nullable final GenericDeclaration genericType,
                          @Nullable final FieldDeclaration field) {
    this.delegate.removeValue(path, genericType, field);
  }

  @Nullable
  @Override
  public Object serialize(@Nullable final Object value, @Nullable final GenericDeclaration genericType,
                          final boolean conservative) throws TransformException {
    return this.delegate.serialize(value, genericType, conservative);
  }

  @NotNull
  @Override
  public Collection<?> serializeCollection(@NotNull final Collection<?> value,
                                           @Nullable final GenericDeclaration genericType, final boolean conservative)
    throws TransformException {
    return this.delegate.serializeCollection(value, genericType, conservative);
  }

  @NotNull
  @Override
  public Map<?, ?> serializeMap(@NotNull final Map<?, ?> value, @Nullable final GenericDeclaration genericType,
                                final boolean conservative)
    throws TransformException {
    return this.delegate.serializeMap(value, genericType, conservative);
  }

  @Override
  public void setValue(@NotNull final String path, @Nullable final Object value,
                       @Nullable final GenericDeclaration genericType, @Nullable final FieldDeclaration field) {
    this.delegate.setValue(path, value, genericType, field);
  }

  @NotNull
  @Override
  public TransformResolver withRegistry(@NotNull final TransformRegistry registry) {
    return this.delegate.withRegistry(registry);
  }

  @NotNull
  @Override
  public TransformResolver withTransformerPacks(@NotNull final TransformPack... packs) {
    return this.delegate.withTransformerPacks(packs);
  }

  @Override
  public void write(@NotNull final OutputStream outputStream, @NotNull final TransformedObjectDeclaration declaration)
    throws Exception {
    this.delegate.write(outputStream, declaration);
  }
}
