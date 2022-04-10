package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class ArtistStubDeezerAdaptor extends DeezerArtist implements ArtistStub {
  private List<? extends Genre> genreList;

  @Override
  public URI getPictureUri() {
    return getPicture();
  }

  @Override
  public List<? extends Genre> getGenreList() {
    return genreList;
  }

  @Override
  public void setGenreList(final List<? extends Genre> genreList) {
    this.genreList = genreList;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    final ArtistStubDeezerAdaptor that = (ArtistStubDeezerAdaptor) o;
    return Objects.equals(genreList, that.genreList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), genreList);
  }
}
