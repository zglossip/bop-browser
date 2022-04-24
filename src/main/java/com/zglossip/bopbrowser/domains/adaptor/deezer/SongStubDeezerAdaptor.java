package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.SongStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

import java.net.URI;
import java.util.Objects;

public class SongStubDeezerAdaptor extends DeezerSong implements SongStub {

  protected SongStubDeezerAdaptor(final DeezerSong deezerSong) {
    super(deezerSong);
  }

  public SongStubDeezerAdaptor() {
    super();
  }

  public static SongStubDeezerAdaptor clone(final DeezerSong deezerSong) {
    return new SongStubDeezerAdaptor(deezerSong);
  }

  @Override
  public URI getAlbumArtUri() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getCoverMedium();
  }

  @Override
  public Integer getAlbumId() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getId();
  }

  @Override
  public URI getPreviewUri() {
    return getPreview();
  }
}
