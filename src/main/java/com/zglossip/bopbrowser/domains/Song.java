package com.zglossip.bopbrowser.domains;

import java.time.LocalDate;
import java.util.List;

public interface Song extends SongStub {
  String getAlbumTitle();

  int getAlbumId();

  List<Genre> getAlbumGenres();

  int getArtistId();

  String getArtistName();

  LocalDate getReleaseDate();
}
