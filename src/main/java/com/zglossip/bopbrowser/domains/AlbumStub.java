package com.zglossip.bopbrowser.domains;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

public interface AlbumStub {
  int getId();

  String getTitle();

  URI getPictureUri();

  LocalDate getReleaseDate();

  List<Genre> getGenreList();

  String getRecordType();

  int getArtistId();

  String getArtistName();

  Integer getDuration();
}
