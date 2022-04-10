package com.zglossip.bopbrowser.daos;

import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicDao extends AbstractDao {

  @Autowired
  public BasicDao(final ApiUtil apiUtil) {
    super(apiUtil);
  }
}
