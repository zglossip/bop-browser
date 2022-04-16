package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = Genre.class)
public interface Genre extends MusicCategory {
  String getName();
}
