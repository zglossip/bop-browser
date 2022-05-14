package com.zglossip.bopbrowser.domains.categories;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = Album.class)
public interface Album extends AlbumStub {
  List<? extends SongStub> getSongList();
}
