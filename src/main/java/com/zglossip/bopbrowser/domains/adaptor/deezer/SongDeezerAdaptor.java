package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.Song;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SongDeezerAdaptor extends SongStubDeezerAdaptor implements Song {
  @Override
  public String getAlbumTitle() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getTitle();
  }

  @Override
  public Integer getAlbumId() {
    if (Objects.isNull(getAlbum()) || getAlbum().getId() == 0) {
      return null;
    }

    return getAlbum().getId();
  }

  @Override
  public List<Genre> getAlbumGenres() {
    if (Objects.isNull(getAlbum()) || Objects.isNull(getAlbum().getGenres())) {
      return Collections.emptyList();
    }

    return getAlbum().getGenres().stream().map(GenreDeezerAdaptor::new).collect(Collectors.toList());
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
    if (Objects.isNull(getArtist())) {
      return null;
    }

    return getArtist().getName();
  }
}
