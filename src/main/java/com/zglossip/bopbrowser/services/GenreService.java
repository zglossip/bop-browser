package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerGenreClient;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.GenreDeezerAdaptor;
import com.zglossip.bopbrowser.exceptions.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GenreService extends AbstractService<Genre> {

  private final DeezerGenreClient deezerGenreClient;

  @Autowired
  public GenreService(final DeezerGenreClient deezerGenreClient) {
    this.deezerGenreClient = deezerGenreClient;
  }

  @Override
  public List<? extends Genre> search(final String query) {
    throw new NotImplementedException("No way to search genres yet.");
  }

  public void populateAlbumStubGenre(final List<AlbumStubDeezerAdaptor> albumList) {
    final Map<Integer, GenreDeezerAdaptor> genreMap = getGenreMapForAlbums(albumList.stream()
                                                                                    .filter(a -> Objects.isNull(a.getGenres()) ||
                                                                                                 Objects.isNull(a.getGenres().getData()))
                                                                                    .collect(Collectors.toList()));
    albumList.stream().filter(a -> Objects.isNull(a.getGenres()) || Objects.isNull(a.getGenres().getData()))
             .forEach(album -> album.setDownloadedGenres(getGenreList(genreMap, album.getGenreId())));
  }

  private List<GenreDeezerAdaptor> getGenreList(final Map<Integer, GenreDeezerAdaptor> genreMap, final Integer genreId) {
    final GenreDeezerAdaptor genre = genreMap.get(genreId);
    if (genre == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList(genre);
  }

  private Map<Integer, GenreDeezerAdaptor> getGenreMapForAlbums(final List<AlbumStubDeezerAdaptor> albumList) {
    return albumList.stream()
                    .map(AlbumStubDeezerAdaptor::getGenreId)
                    .filter(Objects::nonNull)
                    .distinct()
                    .map(deezerGenreClient::getGenre)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(GenreDeezerAdaptor::getId, Function.identity()));
  }
}
