package com.zglossip.bopbrowser.domains;

import java.net.URI;

public interface SongStub extends MusicCategory {
  int getId();

  Integer getTrackPosition();

  String getTitle();

  Integer getDuration();

  URI getAlbumArtUri();

  URI getPreviewUri();
}
