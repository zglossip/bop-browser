package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.categories.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenre;

public class DeezerGenreToGenreAdaptor extends DeezerGenre implements Genre {
  public DeezerGenreToGenreAdaptor(final DeezerGenre genre) {
    super(genre);
  }

  public DeezerGenreToGenreAdaptor() {
    super();
  }
}
