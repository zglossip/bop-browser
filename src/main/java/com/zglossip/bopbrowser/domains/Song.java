package com.zglossip.bopbrowser.domains;

import java.time.LocalDate;
import java.util.List;

public interface Song extends SongStub {
  String getAlbumTitle();

  Integer getAlbumId();

  List<Genre> getAlbumGenres();

  Integer getArtistId();

  String getArtistName();

  LocalDate getReleaseDate();
}