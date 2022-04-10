package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbum;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlbumStubDeezerAdaptor extends DeezerAlbum implements AlbumStub {

  @Override
  public URI getPictureUri() {
    return getCover();
  }

  @Override
  public List<Genre> getGenreList() {
    if (Objects.isNull(getGenres())) {
      return Collections.emptyList();
    }

    return getGenres().stream().map(GenreDeezerAdaptor::new).collect(Collectors.toList());
  }

  @Override
  public Integer getArtistId() {
    if (Objects.isNull(getArtist()) || getArtist().getId() == 0) {
      return null;
    }
    return getArtist().getId();
  }

  @Override
  public String getArtistName() {
    return getArtist().getName();
  }
}
