package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.SongStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

import java.net.URI;
import java.util.Objects;

public class SongStubDeezerAdaptor extends DeezerSong implements SongStub {

  @Override
  public URI getAlbumArtUri() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getCover();
  }

  @Override
  public URI getPreviewUri() {
    return getPreview();
  }
}
