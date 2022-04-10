package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

  private final DeezerSearchClient deezerSearchClient;

  @Autowired
  public SongService(final DeezerSearchClient deezerSearchClient) {
    this.deezerSearchClient = deezerSearchClient;
  }

  public List<? extends Song> searchSongs(final String query) {
    return deezerSearchClient.searchSongs(query);
  }
}
