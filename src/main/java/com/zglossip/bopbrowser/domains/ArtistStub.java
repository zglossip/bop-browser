package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;

@JsonSerialize(as = ArtistStub.class)
public interface ArtistStub extends MusicCategory {
  int getId();

  String getName();

  URI getPictureUri();
}
