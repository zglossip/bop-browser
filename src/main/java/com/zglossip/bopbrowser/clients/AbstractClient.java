package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.util.ApiUtil;

import java.net.URI;

public abstract class AbstractClient {
  private final ApiUtil apiUtil;

  public AbstractClient(final ApiUtil apiUtil) {
    this.apiUtil = apiUtil;
  }

  public <T> T getRequest(final URI uri, final Class<T> clazz) {
    return apiUtil.getRequest(uri, clazz);
  }
}
