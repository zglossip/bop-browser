package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerAlbumClient;
import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.domains.AlbumStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

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

  public List<? extends AlbumStub> searchAlbums(final String query) {
    return deezerSearchClient.searchAlbums(query);
  }
}
