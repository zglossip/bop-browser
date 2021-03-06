package com.zglossip.bopbrowser.domains.categories;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;

@JsonSerialize(as = ArtistStub.class)
public interface ArtistStub extends MusicCategory {
  int getId();

  String getName();

  URI getPictureUri();

  URI getBigPictureUri();
}
