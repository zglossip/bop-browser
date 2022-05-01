package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Song;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService extends AbstractService<Song> {

  private final DeezerSearchClient deezerSearchClient;
  private final AlbumService albumService;
  private final SongContributorService songContributorService;

  @Autowired
  public SongService(final DeezerSearchClient deezerSearchClient, final AlbumService albumService,
                     final SongContributorService songContributorService) {
    this.deezerSearchClient = deezerSearchClient;
    this.albumService = albumService;
    this.songContributorService = songContributorService;
  }

  @Override
  public List<? extends Song> search(final String query) {
    final List<DeezerSongToSongAdaptor> results = deezerSearchClient.searchSongs(query);
    final List<DeezerSongToSongAdaptor> filledResults = songContributorService.getSongListWithContributors(results);
    filledResults.forEach(song -> {
      if (song.getAlbum() != null) {
        final AlbumStub album = albumService.getAlbumStub(song.getAlbumId());
        song.setAlbum((DeezerAlbumToAlbumStubAdaptor) album);
      }
    });
    return filledResults;
  }
}
