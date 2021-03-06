package com.zglossip.bopbrowser.services.deezer;

import com.zglossip.bopbrowser.clients.BasicClient;
import com.zglossip.bopbrowser.clients.DeezerAlbumClient;
import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.SearchResults;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import com.zglossip.bopbrowser.domains.categories.Album;
import com.zglossip.bopbrowser.domains.categories.AlbumStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList;
import com.zglossip.bopbrowser.services.AlbumService;
import com.zglossip.bopbrowser.services.DeezerGenreService;
import com.zglossip.bopbrowser.services.SongContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlbumServiceDeezerImpl implements AlbumService {

  private final DeezerAlbumClient deezerAlbumClient;
  private final DeezerSearchClient deezerSearchClient;
  private final SongContributorService songContributorService;
  private final BasicClient basicClient;
  private final DeezerGenreService deezerGenreService;

  @Autowired
  public AlbumServiceDeezerImpl(final DeezerAlbumClient deezerAlbumClient, final DeezerSearchClient deezerSearchClient,
                                final SongContributorService songContributorService, final BasicClient basicClient,
                                final DeezerGenreService deezerGenreService) {
    this.deezerAlbumClient = deezerAlbumClient;
    this.deezerSearchClient = deezerSearchClient;
    this.songContributorService = songContributorService;
    this.basicClient = basicClient;
    this.deezerGenreService = deezerGenreService;
  }

  @Override
  public Album getAlbumInfo(final int id) {
    final DeezerAlbumToAlbumAdaptor album = deezerAlbumClient.getAlbumInfo(id);

    if (Objects.isNull(album)) {
      return null;
    }

    final DeezerSongList songList = basicClient.getRequest(album.getTracklist(), DeezerSongList.class);

    if (Objects.nonNull(songList) && Objects.nonNull(songList.getData())) {
      album.setSongList(songContributorService.getSongListWithContributors(songList.getData().stream()
                                                                                   .map(DeezerSongToSongAdaptor::clone)
                                                                                   .collect(Collectors.toList())));
    }

    return album;
  }

  @Override
  public AlbumStub getAlbumStub(final int id) {
    return deezerAlbumClient.getAlbumStub(id);
  }

  @Override
  public SearchResults<? extends AlbumStub> search(final String query, final int index, final int limit) {
    final SearchResults<DeezerAlbumToAlbumStubAdaptor> searchResults = deezerSearchClient.searchAlbums(query, index, limit);
    deezerGenreService.populateAlbumStubGenre(searchResults.getData());
    return searchResults;
  }
}
