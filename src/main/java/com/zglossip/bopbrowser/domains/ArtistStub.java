package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.util.List;

@JsonSerialize(as = ArtistStub.class)
public interface ArtistStub extends MusicCategory {
  int getId();

  String getName();

  URI getPictureUri();

  List<? extends Genre> getGenreList();

  void setGenreList(List<? extends Genre> genreList);
}
