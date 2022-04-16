package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.util.List;

@JsonSerialize(as = AlbumStub.class)
public interface AlbumStub extends MusicCategory {
  int getId();

  String getTitle();

  URI getPictureUri();

  List<? extends Genre> getGenreList();

  String getRecordType();

  Integer getArtistId();

  String getArtistName();
}
