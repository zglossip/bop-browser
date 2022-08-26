package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.categories.FullSong;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

public class DeezerSongToFullSongAdaptor extends DeezerSongToSongAdaptor implements FullSong {

  private DeezerSongToFullSongAdaptor(final DeezerSong deezerSong) {
    super(deezerSong);
  }

  public static DeezerSongToFullSongAdaptor clone(final DeezerSong song) {
    return new DeezerSongToFullSongAdaptor(song);
  }

  private String lyrics;

  @Override
  public String getLyrics() {
    return lyrics;
  }

  public void setLyrics(final String lyrics) {
    this.lyrics = lyrics;
  }
}
