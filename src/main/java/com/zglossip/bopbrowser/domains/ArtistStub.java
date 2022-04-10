package com.zglossip.bopbrowser.domains;

import java.net.URI;
import java.util.List;

public interface ArtistStub extends MusicCategory {
  int getId();

  String getName();

  URI getPictureUri();

  List<? extends Genre> getGenreList();

  void setGenreList(List<? extends Genre> genreList);
}
