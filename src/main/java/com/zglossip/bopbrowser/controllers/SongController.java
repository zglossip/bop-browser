package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/song")
public class SongController extends AbstractController<SongService> {
  @Autowired
  SongController(final SongService service) {
    super(service);
  }
}
