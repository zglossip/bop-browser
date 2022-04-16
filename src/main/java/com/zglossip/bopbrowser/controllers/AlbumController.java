package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController extends AbstractController<AlbumService> {

  @Autowired
  AlbumController(final AlbumService albumService) {
    super(albumService);
  }

  @GetMapping("/{id}")
  public Album getAlbumInfo(@PathVariable("id") final int id) {
    return service.getAlbumInfo(id);
  }
}
