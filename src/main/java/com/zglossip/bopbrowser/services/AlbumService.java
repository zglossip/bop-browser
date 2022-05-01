package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.BasicClient;
import com.zglossip.bopbrowser.clients.DeezerAlbumClient;
import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlbumService extends AbstractService<AlbumStub> {

  private final DeezerAlbumClient deezerAlbumClient;
  private final DeezerSearchClient deezerSearchClient;
  private final SongContributorService songContributorService;
  private final BasicClient basicClient;
  private final GenreService genreService;

  @Autowired
  public AlbumService(final DeezerAlbumClient deezerAlbumClient, final DeezerSearchClient deezerSearchClient,
                      final SongContributorService songContributorService, final BasicClient basicClient, final GenreService genreService) {
    this.deezerAlbumClient = deezerAlbumClient;
    this.deezerSearchClient = deezerSearchClient;
    this.songContributorService = songContributorService;
    this.basicClient = basicClient;
    this.genreService = genreService;
  }

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

  public AlbumStub getAlbumStub(final int id) {
    return deezerAlbumClient.getAlbumStub(id);
  }

  @Override
  public List<? extends AlbumStub> search(final String query) {
    final List<DeezerAlbumToAlbumStubAdaptor> searchResults = deezerSearchClient.searchAlbums(query);
    genreService.populateAlbumStubGenre(searchResults);
    return searchResults;
  }
}
