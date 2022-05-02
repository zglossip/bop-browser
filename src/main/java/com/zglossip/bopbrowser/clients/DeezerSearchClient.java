package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbumList;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistList;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList;
import com.zglossip.bopbrowser.exceptions.BadQueryException;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI;

@Service
public class DeezerSearchClient extends AbstractClient {

  private final static String SEARCH_URI = "/search";
  public final static String SEARCH_ARTISTS_URL = SEARCH_URI + "/artist?q=%s";
  public final static String SEARCH_ALBUMS_URL = SEARCH_URI + "/album?q=%s";
  public final static String SEARCH_SONGS_URL = SEARCH_URI + "/track?q=%s";

  @Autowired
  public DeezerSearchClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public List<DeezerArtistToArtistStubAdaptor> searchArtists(final String query) {
    final DeezerArtistList result = getRequest(getSearchArtistsUri(query), DeezerArtistList.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData().stream().map(DeezerArtistToArtistStubAdaptor::clone).collect(Collectors.toList());
  }

  public List<DeezerAlbumToAlbumStubAdaptor> searchAlbums(final String query) {
    final DeezerAlbumList result = getRequest(getSearchAlbumsUri(query), DeezerAlbumList.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData().stream().map(DeezerAlbumToAlbumStubAdaptor::clone).collect(Collectors.toList());
  }

  public List<DeezerSongToSongAdaptor> searchSongs(final String query) {
    final DeezerSongList result = getRequest(getSearchSongsUri(query), DeezerSongList.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData().stream().map(DeezerSongToSongAdaptor::clone).collect(Collectors.toList());
  }

  private URI getSearchArtistsUri(final String query) {
    return generateUriWithQuery(query, SEARCH_ARTISTS_URL, String.format("There was an issue searching artists for query %s", query));
  }

  private URI getSearchAlbumsUri(final String query) {
    return generateUriWithQuery(query, SEARCH_ALBUMS_URL, String.format("There was an issue searching albums for query %s", query));
  }

  private URI getSearchSongsUri(final String query) {
    return generateUriWithQuery(query, SEARCH_SONGS_URL, String.format("There was an issue searching songs for query %s", query));
  }

  private URI generateUriWithQuery(final String query, final String uri, final String errorMessage) {
    try {
      return generateUri(BASE_URI + String.format(uri, URLEncoder.encode(query == null ? "" : query, StandardCharsets.UTF_8.toString())),
                         errorMessage);
    } catch (final UnsupportedEncodingException e) {
      throw new BadQueryException(String.format("There was an issue encoding query %s", query), e);
    }
  }
}
