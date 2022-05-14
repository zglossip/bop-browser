package com.zglossip.bopbrowser.services.deezer;

import com.zglossip.bopbrowser.clients.BasicClient;
import com.zglossip.bopbrowser.clients.DeezerArtistClient;
import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.*;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList;
import com.zglossip.bopbrowser.services.ArtistService;
import com.zglossip.bopbrowser.services.DeezerGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArtistServiceDeezerImpl implements ArtistService {

  private final DeezerArtistClient deezerArtistClient;
  private final DeezerSearchClient deezerSearchClient;
  private final BasicClient basicClient;
  private final DeezerGenreService deezerGenreService;

  @Autowired
  public ArtistServiceDeezerImpl(final DeezerArtistClient deezerArtistClient, final DeezerSearchClient deezerSearchClient,
                                 final BasicClient basicClient, final DeezerGenreService deezerGenreService) {
    this.deezerArtistClient = deezerArtistClient;
    this.deezerSearchClient = deezerSearchClient;
    this.basicClient = basicClient;
    this.deezerGenreService = deezerGenreService;
  }

  @Override
  public Artist getArtistInfo(final int id, final int numberOfGenres, final int numberOfTracks, final int numberOfAlbums,
                              final int numberOfArtists) {
    final DeezerArtistToArtistAdaptor artist = deezerArtistClient.getArtistInfo(id);

    if (artist == null) {
      return null;
    }

    final List<? extends AlbumStub> topAlbums = getArtistAlbums(id);
    artist.setTopAlbumList(topAlbums.stream().limit(numberOfAlbums).collect(Collectors.toList()));
    artist.setGenreList(getTopGenres(topAlbums, numberOfGenres));
    artist.setTopSongList(getTopSongs(artist.getTracklist()).stream().limit(numberOfTracks).collect(Collectors.toList()));
    artist.setRelatedArtistList(getRelatedArtists(id).stream().limit(numberOfArtists).collect(Collectors.toList()));
    return artist;
  }

  @Override
  public List<? extends AlbumStub> getArtistAlbums(final int id) {
    return deezerArtistClient.getTopAlbums(id);
  }

  @Override
  public List<? extends ArtistStub> getRelatedArtists(final int id) {
    return deezerArtistClient.getRelatedArtists(id);
  }

  @Override
  public List<? extends ArtistStub> search(final String query, final int index, final int limit) {
    return deezerSearchClient.searchArtists(query, index, limit);
  }

  private List<? extends SongStub> getTopSongs(final URI uri) {
    final DeezerSongList result = basicClient.getRequest(uri, DeezerSongList.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData().stream().map(DeezerSongToSongStubAdaptor::clone).collect(Collectors.toList());
  }

  private List<? extends Genre> getTopGenres(final List<? extends AlbumStub> topAlbums, final int numberOfGenres) {
    if (topAlbums == null) {
      return Collections.emptyList();
    }

    final List<DeezerAlbumToAlbumStubAdaptor> convertedList =
            topAlbums.stream().map(a -> (DeezerAlbumToAlbumStubAdaptor) a).collect(Collectors.toList());

    deezerGenreService.populateAlbumStubGenre(convertedList);

    final List<Genre> sorted = convertedList.stream()
                                            .map(AlbumStub::getGenreList)
                                            .flatMap(List::stream)
                                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                            .entrySet()
                                            .stream()
                                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                            .map(Map.Entry::getKey)
                                            .collect(Collectors.toList());
    return sorted.stream().limit(numberOfGenres).collect(Collectors.toList());
  }
}
