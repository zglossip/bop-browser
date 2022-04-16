package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.Song;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SongDeezerAdaptor extends SongStubDeezerAdaptor implements Song {

  private SongDeezerAdaptor(final DeezerSong deezerSong) {
    super(deezerSong);
  }

  public SongDeezerAdaptor() {
    super();
  }

  public static SongDeezerAdaptor clone(final DeezerSong deezerSong) {
    return new SongDeezerAdaptor(deezerSong);
  }

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
  public List<? extends Genre> getAlbumGenres() {
    if (Objects.isNull(getAlbum()) || Objects.isNull(getAlbum().getGenreList())) {
      return Collections.emptyList();
    }

    return getAlbum().getGenreList();
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
