package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.categories.Genre;
import com.zglossip.bopbrowser.domains.categories.Song;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeezerSongToSongAdaptor extends DeezerSongToSongStubAdaptor implements Song {

  private DeezerSongToSongAdaptor(final DeezerSong deezerSong) {
    super(deezerSong);
  }

  public DeezerSongToSongAdaptor() {
    super();
  }

  public static DeezerSongToSongAdaptor clone(final DeezerSong deezerSong) {
    return new DeezerSongToSongAdaptor(deezerSong);
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
}
