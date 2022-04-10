package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerAlbumClient;
import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.domains.AlbumStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService extends AbstractService<AlbumStub> {

  private final DeezerAlbumClient deezerAlbumClient;
  private final DeezerSearchClient deezerSearchClient;

  @Autowired
  public AlbumService(final DeezerAlbumClient deezerAlbumClient, final DeezerSearchClient deezerSearchClient) {
    this.deezerAlbumClient = deezerAlbumClient;
    this.deezerSearchClient = deezerSearchClient;
  }

  public Album getAlbumInfo(final int id) {
    return deezerAlbumClient.getAlbumInfo(id);
  }

  @Override
  public List<? extends AlbumStub> search(final String query) {
    return deezerSearchClient.searchAlbums(query);
  }
}
