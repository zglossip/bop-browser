package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.Song;

import java.util.List;
import java.util.stream.Collectors;

public class SongDeezerAdaptor extends SongStubDeezerAdaptor implements Song {
  @Override
  public String getAlbumTitle() {
    return getAlbum().getTitle();
  }

  @Override
  public int getAlbumId() {
    return getAlbum().getId();
  }

  @Override
  public List<Genre> getAlbumGenres() {
    return getAlbum().getGenres().stream().map(GenreDeezerAdaptor::new).collect(Collectors.toList());
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
