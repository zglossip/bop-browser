package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.domains.categories.FullSong;
import com.zglossip.bopbrowser.services.SongService;
import com.zglossip.bopbrowser.services.deezer.SongServiceDeezerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/song")
public class SongController extends AbstractController<SongService> {
  @Autowired
  SongController(final SongServiceDeezerImpl service) {
    super(service);
  }

  @GetMapping("/{id}")
  public FullSong getFullSong(@PathVariable("id") final int id) {
    return service.getFullSong(id);
  }
}
