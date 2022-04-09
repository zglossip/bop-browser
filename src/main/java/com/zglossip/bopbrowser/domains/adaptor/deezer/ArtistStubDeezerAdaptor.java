package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist;

import java.net.URI;
import java.util.List;

public class ArtistStubDeezerAdaptor extends DeezerArtist implements ArtistStub {
  private List<Genre> genreList;

  @Override
  public URI getPictureUri() {
    return getPicture();
  }

  @Override
  public List<Genre> getGenreList() {
    return genreList;
  }

  public void setGenreList(final List<Genre> genreList) {
    this.genreList = genreList;
  }
}
