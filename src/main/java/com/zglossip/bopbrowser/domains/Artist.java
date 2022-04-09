package com.zglossip.bopbrowser.domains;

import java.util.List;

public interface Artist extends ArtistStub {
  List<SongStub> getTopSongList();

  List<AlbumStub> getTopAlbumList();
}
