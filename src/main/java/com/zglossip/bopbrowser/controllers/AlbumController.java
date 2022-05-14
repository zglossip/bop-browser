package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.domains.categories.Album;
import com.zglossip.bopbrowser.services.AlbumService;
import com.zglossip.bopbrowser.services.deezer.AlbumServiceDeezerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController extends AbstractController<AlbumService> {

  @Autowired
  AlbumController(final AlbumServiceDeezerImpl albumServiceDeezerImpl) {
    super(albumServiceDeezerImpl);
  }

  @GetMapping("/{id}")
  public Album getAlbumInfo(@PathVariable("id") final int id) {
    return service.getAlbumInfo(id);
  }
}
