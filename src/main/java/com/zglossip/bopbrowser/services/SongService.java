package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService extends AbstractService<Song> {

  private final DeezerSearchClient deezerSearchClient;

  @Autowired
  public SongService(final DeezerSearchClient deezerSearchClient) {
    this.deezerSearchClient = deezerSearchClient;
  }

  @Override
  public List<? extends Song> search(final String query) {
    return deezerSearchClient.searchSongs(query);
  }
}
