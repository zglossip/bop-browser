package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.util.List;

@JsonSerialize(as = SongStub.class)
public interface SongStub extends MusicCategory {
  int getId();

  String getTitle();

  Integer getDuration();

  URI getAlbumArtUri();

  URI getBigAlbumArtUri();

  Integer getAlbumId();

  URI getPreviewUri();

  Integer getTrackPosition();

  List<? extends ArtistStub> getFeaturingList();
}
