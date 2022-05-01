package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist;

import java.net.URI;

public class ArtistStubDeezerAdaptor extends DeezerArtist implements ArtistStub {

  private ArtistStubDeezerAdaptor(final DeezerArtist deezerArtist) {
    super(deezerArtist);
  }

  public ArtistStubDeezerAdaptor() {
    super();
  }

  public static ArtistStubDeezerAdaptor clone(final DeezerArtist deezerArtist) {
    return new ArtistStubDeezerAdaptor(deezerArtist);
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
