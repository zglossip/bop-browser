package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.categories.Album;
import com.zglossip.bopbrowser.domains.categories.AlbumStub;

public interface AlbumService extends AbstractService<AlbumStub> {

  Album getAlbumInfo(final int id);

  public AlbumStub getAlbumStub(final int id);
}
