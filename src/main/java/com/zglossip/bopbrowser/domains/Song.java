package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = Song.class)
public interface Song extends SongStub {
  String getAlbumTitle();

  List<? extends Genre> getAlbumGenres();
}
