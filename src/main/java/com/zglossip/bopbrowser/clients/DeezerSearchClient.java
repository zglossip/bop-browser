package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSearchAlbumResult;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSearchArtistResult;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSearchSongResult;
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

  public List<ArtistStubDeezerAdaptor> searchArtists(final String query) {
    final DeezerSearchArtistResult result = getRequest(getSearchArtistsUri(query), DeezerSearchArtistResult.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData();
  }

  public List<AlbumStubDeezerAdaptor> searchAlbums(final String query) {
    final DeezerSearchAlbumResult result = getRequest(getSearchAlbumsUri(query), DeezerSearchAlbumResult.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData();
  }

  public List<SongDeezerAdaptor> searchSongs(final String query) {
    final DeezerSearchSongResult result = getRequest(getSearchSongsUri(query), DeezerSearchSongResult.class);

    if (result == null || result.getData() == null) {
      return Collections.emptyList();
    }

    return result.getData();
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
      return generateUri(BASE_URI + String.format(uri, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())), errorMessage);
    } catch (final UnsupportedEncodingException e) {
      throw new BadQueryException(String.format("There was an issue encoding query %s", query), e);
    }
  }
}
