package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.services.AbstractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class AbstractController<K extends AbstractService> {
  public K service;

  AbstractController(final K service) {
    this.service = service;
  }

  @GetMapping("/search")
  public List<?> search(@RequestParam(defaultValue = "") final String query) {
    return service.search(query);
  }
}
