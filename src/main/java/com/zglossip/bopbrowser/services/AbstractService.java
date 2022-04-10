package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.MusicCategory;

import java.util.List;

public abstract class AbstractService<T extends MusicCategory> {
  public abstract List<? extends T> search(String query);
}
