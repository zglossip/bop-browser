package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.MusicCategory;

import java.util.List;

public interface AbstractService<T extends MusicCategory> {
  List<? extends T> search(String query, int index, int limit);
}
