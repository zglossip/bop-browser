package com.zglossip.bopbrowser.controllers;

import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Artist;
import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/artist")
public class ArtistController extends AbstractController<ArtistService> {
  @Autowired
  public ArtistController(final ArtistService artistService) {
    super(artistService);
  }

  @GetMapping("/{id}")
  public Artist getArtistInfo(@PathVariable("id") final int id,
                              @RequestParam(defaultValue = "5", value = "songs") final int nbSongs,
                              @RequestParam(defaultValue = "3", value = "genres") final int nbGenres,
                              @RequestParam(defaultValue = "4", value = "albums") final int nbAlbums,
                              @RequestParam(defaultValue = "4", value = "artists") final int nbArtists) {
    return service.getArtistInfo(id, nbGenres, nbSongs, nbAlbums, nbArtists);
  }

  @GetMapping("/{id}/albums")
  public List<? extends AlbumStub> getArtistAlbums(@PathVariable("id") final int id) {
    return service.getArtistAlbums(id);
  }

  @GetMapping("/{id}/related")
  public List<? extends ArtistStub> getRelatedArtists(@PathVariable("id") final int id) {
    return service.getRelatedArtists(id);
  }
}
