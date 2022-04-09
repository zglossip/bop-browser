package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.SongStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

import java.net.URI;

public class SongStubDeezerAdaptor extends DeezerSong implements SongStub {

  @Override
  public URI getAlbumArtUri() {
    return getAlbum().getCover();
  }

  @Override
  public URI getPreviewUri() {
    return getPreview();
  }
}
