package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/song")
public class SongController extends AbstractController<SongService> {
  @Autowired
  SongController(final SongService service) {
    super(service);
  }
}
