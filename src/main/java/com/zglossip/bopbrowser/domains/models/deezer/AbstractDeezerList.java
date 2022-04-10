package com.zglossip.bopbrowser.domains.models.deezer;

import java.util.List;
import java.util.Objects;

public abstract class AbstractDeezerList<T> {
  private List<T> data;

  public List<T> getData() {
    return data;
  }

  public void setData(final List<T> data) {
    this.data = data;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AbstractDeezerList<?> that = (AbstractDeezerList<?>) o;
    return Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    return "AbstractDeezerList{" +
           "data=" + data +
           '}';
  }
}
