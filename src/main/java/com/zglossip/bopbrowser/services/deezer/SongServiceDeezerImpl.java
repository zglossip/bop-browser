package com.zglossip.bopbrowser.services.deezer;

import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.SearchResults;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import com.zglossip.bopbrowser.domains.categories.AlbumStub;
import com.zglossip.bopbrowser.domains.categories.Song;
import com.zglossip.bopbrowser.services.AlbumService;
import com.zglossip.bopbrowser.services.SongContributorService;
import com.zglossip.bopbrowser.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceDeezerImpl implements SongService {

  private final DeezerSearchClient deezerSearchClient;
  private final AlbumService albumService;
  private final SongContributorService songContributorService;

  @Autowired
  public SongServiceDeezerImpl(final DeezerSearchClient deezerSearchClient, final AlbumServiceDeezerImpl albumServiceDeezerImpl,
                               final SongContributorService songContributorService) {
    this.deezerSearchClient = deezerSearchClient;
    this.albumService = albumServiceDeezerImpl;
    this.songContributorService = songContributorService;
  }

  @Override
  public SearchResults<? extends Song> search(final String query, final int index, final int limit) {
    final SearchResults<DeezerSongToSongAdaptor> results = deezerSearchClient.searchSongs(query, index, limit);
    final List<DeezerSongToSongAdaptor> filledResults = songContributorService.getSongListWithContributors(results.getData());
    filledResults.forEach(song -> {
      if (song.getAlbum() != null) {
        final AlbumStub album = albumService.getAlbumStub(song.getAlbumId());
        song.setAlbum((DeezerAlbumToAlbumStubAdaptor) album);
      }
    });
    results.setData(filledResults);
    return results;
  }
}
