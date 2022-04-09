package com.zglossip.bopbrowser.domains;

import java.util.List;

public interface Album extends AlbumStub {
  List<SongStub> getSongList();
}
