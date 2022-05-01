package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist;

import java.net.URI;

public class DeezerArtistToArtistStubAdaptor extends DeezerArtist implements ArtistStub {

  private DeezerArtistToArtistStubAdaptor(final DeezerArtist deezerArtist) {
    super(deezerArtist);
  }

  public DeezerArtistToArtistStubAdaptor() {
    super();
  }

  public static DeezerArtistToArtistStubAdaptor clone(final DeezerArtist deezerArtist) {
    return new DeezerArtistToArtistStubAdaptor(deezerArtist);
  }

  @Override
  public URI getPictureUri() {
    return getPictureBig();
  }

  @Override
  public URI getBigPictureUri() {
    return getPictureXl();
  }
}
