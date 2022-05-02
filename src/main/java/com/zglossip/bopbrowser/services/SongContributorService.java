package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerSongClient;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SongContributorService {

  private final DeezerSongClient deezerSongClient;

  @Autowired
  public SongContributorService(final DeezerSongClient deezerSongClient) {
    this.deezerSongClient = deezerSongClient;
  }

  public List<DeezerSongToSongAdaptor> getSongListWithContributors(final List<DeezerSongToSongAdaptor> songList) {
    if (Objects.isNull(songList)) {
      return Collections.emptyList();
    }

    return songList.stream().map(deezerSong -> {
      if (Objects.nonNull(deezerSong.getContributors())) {
        return DeezerSongToSongAdaptor.clone(deezerSong);
      }

      return deezerSongClient.getSongInfo(deezerSong.getId());
    }).collect(Collectors.toList());
  }
}
