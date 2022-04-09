package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbum;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumStubDeezerAdaptor extends DeezerAlbum implements AlbumStub {

  @Override
  public URI getPictureUri() {
    return getCover();
  }

  @Override
  public List<Genre> getGenreList() {
    return getGenres().stream().map(GenreDeezerAdaptor::new).collect(Collectors.toList());
  }

  @Override
  public int getArtistId() {
    return getArtist().getId();
  }

  @Override
  public String getArtistName() {
    return getArtist().getName();
  }
}
