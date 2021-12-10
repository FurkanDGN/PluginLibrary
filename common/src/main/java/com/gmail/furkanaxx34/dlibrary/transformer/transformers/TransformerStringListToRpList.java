package com.gmail.furkanaxx34.dlibrary.transformer.transformers;

import com.gmail.furkanaxx34.dlibrary.replaceable.RpBase;
import com.gmail.furkanaxx34.dlibrary.replaceable.RpList;
import com.gmail.furkanaxx34.dlibrary.transformer.TwoSideTransformer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * a class that represents transformers between {@link String} {@link List} and {@link RpList}.
 */
@SuppressWarnings("rawtypes")
public final class TransformerStringListToRpList extends TwoSideTransformer.Base<List, RpList> {

  /**
   * ctor.
   */
  public TransformerStringListToRpList() {
    super(List.class, RpList.class,
      RpBase::getValue,
      RpList::fromObjects,
      (s, rpList) -> rpList.value(((List<?>) s).stream().map(Objects::toString).collect(Collectors.toList())));
  }
}
