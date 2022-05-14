package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.SearchResults;
import com.zglossip.bopbrowser.domains.categories.MusicCategory;

public interface AbstractService<T extends MusicCategory> {
  SearchResults<? extends T> search(String query, int index, int limit);
}
