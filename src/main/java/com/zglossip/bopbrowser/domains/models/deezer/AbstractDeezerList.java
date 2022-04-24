package com.zglossip.bopbrowser.domains.models.deezer;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public abstract class AbstractDeezerList<T> {
  private List<T> data;
  private URI prev;
  private URI next;
  private Integer total;

  public List<T> getData() {
    return data;
  }

  public void setData(final List<T> data) {
    this.data = data;
  }

  public URI getPrev() {
    return prev;
  }

  public void setPrev(final URI prev) {
    this.prev = prev;
  }

  public URI getNext() {
    return next;
  }

  public void setNext(final URI next) {
    this.next = next;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(final Integer total) {
    this.total = total;
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
