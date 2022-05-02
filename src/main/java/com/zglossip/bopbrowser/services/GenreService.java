package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.clients.DeezerGenreClient;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerGenreToGenreAdaptor;
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

  public void populateAlbumStubGenre(final List<DeezerAlbumToAlbumStubAdaptor> albumList) {
    final Map<Integer, DeezerGenreToGenreAdaptor> genreMap = getGenreMapForAlbums(albumList.stream()
                                                                                           .filter(a -> Objects.isNull(a.getGenres()) ||
                                                                                                 Objects.isNull(a.getGenres().getData()))
                                                                                           .collect(Collectors.toList()));
    albumList.stream().filter(a -> Objects.isNull(a.getGenres()) || Objects.isNull(a.getGenres().getData()))
             .forEach(album -> album.setDownloadedGenres(getGenreList(genreMap, album.getGenreId())));
  }

  private List<DeezerGenreToGenreAdaptor> getGenreList(final Map<Integer, DeezerGenreToGenreAdaptor> genreMap, final Integer genreId) {
    final DeezerGenreToGenreAdaptor genre = genreMap.get(genreId);
    if (genre == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList(genre);
  }

  private Map<Integer, DeezerGenreToGenreAdaptor> getGenreMapForAlbums(final List<DeezerAlbumToAlbumStubAdaptor> albumList) {
    return albumList.stream()
                    .map(DeezerAlbumToAlbumStubAdaptor::getGenreId)
                    .filter(Objects::nonNull)
                    .distinct()
                    .map(deezerGenreClient::getGenre)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(DeezerGenreToGenreAdaptor::getId, Function.identity()));
  }
}
