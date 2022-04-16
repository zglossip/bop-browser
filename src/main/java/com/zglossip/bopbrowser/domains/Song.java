package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.List;

@JsonSerialize(as = Song.class)
public interface Song extends SongStub {
  String getAlbumTitle();

  Integer getAlbumId();

  List<Genre> getAlbumGenres();

  Integer getArtistId();

  String getArtistName();

  LocalDate getReleaseDate();
}
