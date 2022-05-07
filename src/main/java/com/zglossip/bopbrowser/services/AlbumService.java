package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.domains.AlbumStub;

public interface AlbumService extends AbstractService<AlbumStub> {

  Album getAlbumInfo(final int id);

  public AlbumStub getAlbumStub(final int id);
}
