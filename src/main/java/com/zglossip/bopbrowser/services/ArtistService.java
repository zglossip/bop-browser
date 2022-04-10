package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.BasicClient;
import com.zglossip.bopbrowser.clients.DeezerArtistClient;
import com.zglossip.bopbrowser.clients.DeezerSearchClient;
import com.zglossip.bopbrowser.domains.*;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerTopSongsResult;
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
public class ArtistService extends AbstractService<ArtistStub> {

  private static final int SEARCH_PAGE_GENRE_COUNT = 3;

  private final DeezerArtistClient deezerArtistClient;
  private final DeezerSearchClient deezerSearchClient;
  private final BasicClient basicClient;

  @Autowired
  public ArtistService(final DeezerArtistClient deezerArtistClient, final DeezerSearchClient deezerSearchClient,
                       final BasicClient basicClient) {
    this.deezerArtistClient = deezerArtistClient;
    this.deezerSearchClient = deezerSearchClient;
    this.basicClient = basicClient;
  }

  public Artist getArtistInfo(final int id, final int numberOfGenres, final int numberOfTracks, final int numberOfAlbums,
                              final int numberOfArtists) {
    final Artist artist = deezerArtistClient.getArtistInfo(id);

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

  public List<? extends AlbumStub> getArtistAlbums(final int id) {
    return deezerArtistClient.getTopAlbums(id);
  }

  public List<? extends ArtistStub> getRelatedArtists(final int id) {
    return deezerArtistClient.getRelatedArtists(id);
  }

  @Override
  public List<? extends ArtistStub> search(final String query) {
    final List<? extends ArtistStub> artists = deezerSearchClient.searchArtists(query);
    artists.forEach(artist -> {
      final List<? extends AlbumStub> topAlbums = getArtistAlbums(artist.getId());
      artist.setGenreList(getTopGenres(topAlbums, SEARCH_PAGE_GENRE_COUNT));
    });
    return artists;
  }

  private List<? extends SongStub> getTopSongs(final URI uri) {
    final DeezerTopSongsResult result = basicClient.getRequest(uri, DeezerTopSongsResult.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData();
  }

  private List<? extends Genre> getTopGenres(final List<? extends AlbumStub> topAlbums, final int numberOfGenres) {
    if (topAlbums == null) {
      return Collections.emptyList();
    }

    final List<Genre> sorted = topAlbums.stream()
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
