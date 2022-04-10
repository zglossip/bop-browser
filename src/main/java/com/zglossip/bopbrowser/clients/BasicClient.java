package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicClient extends AbstractClient {

  @Autowired
  public BasicClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }
}
