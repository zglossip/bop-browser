package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = Album.class)
public interface Album extends AlbumStub {
  List<? extends SongStub> getSongList();
}
