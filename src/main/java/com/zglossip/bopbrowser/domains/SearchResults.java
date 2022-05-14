package com.zglossip.bopbrowser.domains;

import com.zglossip.bopbrowser.domains.categories.MusicCategory;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SearchResults<K extends MusicCategory> implements Comparable<SearchResults<?>> {

  private final static Comparator<SearchResults<?>> comparator = Comparator.comparing(SearchResults::getTotal, Comparator.naturalOrder());

  private int total;
  private List<K> data;

  public SearchResults() {

  }

  public SearchResults(final List<K> data) {
    this.total = 0;
    this.data = data;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(final int total) {
    this.total = total;
  }

  public List<K> getData() {
    return data;
  }

  public void setData(final List<K> data) {
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
    final SearchResults<?> that = (SearchResults<?>) o;
    return total == that.total && Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, data);
  }

  @Override
  public String toString() {
    return "SearchResults{" +
           "total=" + total +
           ", data=" + data +
           '}';
  }

  @Override
  public int compareTo(final SearchResults that) {
    return comparator.compare(this, that);
  }
}
