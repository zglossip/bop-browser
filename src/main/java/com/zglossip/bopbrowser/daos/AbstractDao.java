package com.zglossip.bopbrowser.daos;

import com.zglossip.bopbrowser.util.ApiUtil;

import java.net.URI;

public abstract class AbstractDao {
  private final ApiUtil apiUtil;

  public AbstractDao(final ApiUtil apiUtil) {
    this.apiUtil = apiUtil;
  }

  public <T> T getRequest(final URI uri, final Class<T> clazz) {
    return apiUtil.getRequest(uri, clazz);
  }
}
