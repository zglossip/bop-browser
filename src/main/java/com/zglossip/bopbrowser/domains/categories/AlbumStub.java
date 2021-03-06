package com.zglossip.bopbrowser.domains.categories;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.util.Date;
import java.util.List;

@JsonSerialize(as = AlbumStub.class)
public interface AlbumStub extends MusicCategory {
  int getId();

  String getTitle();

  URI getPictureUri();

  URI getBigPictureUri();

  List<? extends Genre> getGenreList();

  String getRecordType();

  Integer getArtistId();

  String getArtistName();

  Date getReleaseDate();

  List<? extends ArtistStub> getFeaturingList();
}
