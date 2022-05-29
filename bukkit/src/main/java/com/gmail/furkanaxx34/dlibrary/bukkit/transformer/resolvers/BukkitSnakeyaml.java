package com.gmail.furkanaxx34.dlibrary.bukkit.transformer.resolvers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformResolver;
import com.gmail.furkanaxx34.dlibrary.transformer.TransformedObject;
import com.gmail.furkanaxx34.dlibrary.transformer.annotations.Comment;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.FieldDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.GenericDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.declarations.TransformedObjectDeclaration;
import com.gmail.furkanaxx34.dlibrary.transformer.exceptions.TransformException;
import com.gmail.furkanaxx34.dlibrary.transformer.postprocessor.LineInfo;
import com.gmail.furkanaxx34.dlibrary.transformer.postprocessor.PostProcessor;
import com.gmail.furkanaxx34.dlibrary.transformer.postprocessor.SectionSeparator;
import com.gmail.furkanaxx34.dlibrary.transformer.postprocessor.walkers.YamlSectionWalker;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * a class that represents yaml file configuration.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BukkitSnakeyaml extends TransformResolver {

  /**
   * the comment prefix.
   */
  @NotNull
  private final String commentPrefix;

  /**
   * the config.
   */
  @NotNull
  private final YamlConfiguration config;

  /**
   * the section separator.
   */
  @NotNull
  private final String sectionSeparator;

  /**
   * ctor.
   *
   * @param config the config.
   * @param commentPrefix the comment prefix.
   * @param sectionSeparator the section separator.
   */
  public BukkitSnakeyaml(@NotNull final YamlConfiguration config, @NotNull final String commentPrefix,
                         @NotNull final String sectionSeparator) {
    this(commentPrefix, config, sectionSeparator);
  }

  /**
   * ctor.
   *
   * @param commentPrefix the comment prefix.
   * @param sectionSeparator the section separator.
   */
  public BukkitSnakeyaml(@NotNull final String commentPrefix, @NotNull final String sectionSeparator) {
    this(commentPrefix, new YamlConfiguration(), sectionSeparator);
  }

  /**
   * ctor.
   *
   * @param config the config.
   */
  public BukkitSnakeyaml(@NotNull final YamlConfiguration config) {
    this("# ", config, SectionSeparator.NONE);
  }

  /**
   * ctor.
   *
   * @param sectionSeparator the section separator.
   */
  public BukkitSnakeyaml(@NotNull final String sectionSeparator) {
    this("# ", sectionSeparator);
  }

  /**
   * ctor.
   */
  public BukkitSnakeyaml() {
    this(new YamlConfiguration());
  }

  @Nullable
  @Override
  public <T> T deserialize(@Nullable final Object object, @Nullable final GenericDeclaration genericSource,
                           @NotNull final Class<T> targetClass, @Nullable final GenericDeclaration genericTarget,
                           @Nullable final Object defaultValue)
    throws TransformException {
    if (object instanceof ConfigurationSection) {
      final Map<String, Object> values = this.getMapValues((ConfigurationSection) object, false);
      return super.deserialize(values, GenericDeclaration.of(values), targetClass, genericTarget, defaultValue);
    }
    return super.deserialize(object, genericSource, targetClass, genericTarget, defaultValue);
  }

  @NotNull
  @Override
  public List<String> getAllKeys() {
    return new ArrayList<>(this.config.getKeys(false));
  }

  @NotNull
  @Override
  public Optional<Object> getValue(@NotNull final String path) {
    return Optional.ofNullable(this.config.get(path));
  }

  @Override
  public void load(@NotNull final InputStream inputStream, @NotNull final TransformedObjectDeclaration declaration)
    throws Exception {
    this.config.loadFromString(PostProcessor.of(inputStream).getContext());
  }

  @Override
  public boolean pathExists(@NotNull final String path) {
    return this.config.getKeys(false).contains(path);
  }

  @Override
  public void removeValue(@NotNull final String path, @Nullable final GenericDeclaration genericType,
                          @Nullable final FieldDeclaration field) {
    this.config.set(path, null);
  }

  @Nullable
  @Override
  public Object serialize(@Nullable final Object value, @Nullable final GenericDeclaration genericType,
                          final boolean conservative) throws TransformException {
    if (value instanceof ConfigurationSection) {
      return this.getMapValues((ConfigurationSection) value, false);
    }
    return super.serialize(value, genericType, conservative);
  }

  @Override
  public void setValue(@NotNull final String path, @Nullable final Object value,
                       @Nullable final GenericDeclaration genericType, @Nullable final FieldDeclaration field) {
    this.config.set(path, this.serialize(value, genericType, true));
  }

  @Override
  public void write(@NotNull final OutputStream outputStream, @NotNull final TransformedObjectDeclaration declaration) {
    final String contents = this.config.saveToString();
    final PostProcessor processor = PostProcessor.of(contents)
      .removeLines(line -> line.startsWith(this.commentPrefix.trim()))
      .updateLinesPaths(new YamlSectionWalker() {
        @NotNull
        @Override
        public String update(@NotNull final String line, @NotNull final LineInfo lineInfo,
                             @NotNull final List<LineInfo> path) {
          TransformedObjectDeclaration currentDeclaration = declaration;
          for (int i = 0; i < path.size() - 1; i++) {
            final LineInfo pathElement = path.get(i);
            final FieldDeclaration field = currentDeclaration.getNonMigratedFields().get(pathElement.getName());
            if (field == null) {
              return line;
            }
            final GenericDeclaration fieldType = field.getGenericDeclaration();
            final Class<?> type = fieldType.getType();
            if (type == null) {
              continue;
            }
            if (!TransformedObject.class.isAssignableFrom(type)) {
              return line;
            }
            currentDeclaration = TransformedObjectDeclaration.of(type);
          }
          final FieldDeclaration lineDeclaration = currentDeclaration.getNonMigratedFields().get(lineInfo.getName());
          if (lineDeclaration == null) {
            return line;
          }
          final Comment fieldComment = lineDeclaration.getComment();
          if (fieldComment == null) {
            return line;
          }
          return '\n' + PostProcessor.addIndent(
            PostProcessor.createComment(BukkitSnakeyaml.this.commentPrefix, fieldComment.value()),
            lineInfo.getIndent()) + line;
        }
      });
    final Comment header = declaration.getHeader();
    if (header != null) {
      processor.prependContextComment(this.commentPrefix, this.sectionSeparator, header.value());
    }
    processor.write(outputStream);
  }

  /**
   * gets the section value with primitive objects.
   *
   * @param section the section to get.
   * @param deep the deep to get.
   *
   * @return map values.
   */
  @NotNull
  private Map<String, Object> getMapValues(@NotNull final ConfigurationSection section, final boolean deep) {
    return section.getValues(deep).entrySet().stream()
      .map(entry -> {
        final String key = entry.getKey();
        final Object value = entry.getValue();
        if (value instanceof ConfigurationSection) {
          return new AbstractMap.SimpleEntry<String, Object>(key, this.getMapValues((ConfigurationSection) value, deep));
        }
        return new AbstractMap.SimpleEntry<String, Object>(key, value);
      })
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
