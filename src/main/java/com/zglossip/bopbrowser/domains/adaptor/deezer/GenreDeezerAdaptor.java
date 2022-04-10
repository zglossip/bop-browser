package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenre;

public class GenreDeezerAdaptor extends DeezerGenre implements Genre {
  public GenreDeezerAdaptor(final DeezerGenre genre) {
    super(genre);
  }

  public GenreDeezerAdaptor() {
    super();
  }
}
