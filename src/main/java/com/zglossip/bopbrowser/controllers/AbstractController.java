package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.domains.SearchResults;
import com.zglossip.bopbrowser.services.AbstractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class AbstractController<K extends AbstractService> {
  public K service;

  AbstractController(final K service) {
    this.service = service;
  }

  @GetMapping("/search")
  public SearchResults<?> search(@RequestParam(defaultValue = "") final String query, @RequestParam(defaultValue = "0") final int index,
                                 @RequestParam(defaultValue = "25") final int limit) {
    return service.search(query, index, limit);
  }
}
