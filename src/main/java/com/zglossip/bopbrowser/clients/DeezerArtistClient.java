package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbumList;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistList;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI;

@Service
public class DeezerArtistClient extends AbstractClient {

  public static final String ARTIST_INFO_URI = "/artist/%d";
  public static final String TOP_ALBUMS_URI = ARTIST_INFO_URI + "/albums";
  public static final String RELATED_ARTISTS_URI = ARTIST_INFO_URI + "/related";

  @Autowired
  public DeezerArtistClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public DeezerArtistToArtistAdaptor getArtistInfo(final int id) {
    return getRequest(getArtistInfoUri(id), DeezerArtistToArtistAdaptor.class);
  }

  public List<DeezerAlbumToAlbumStubAdaptor> getTopAlbums(final int id) {
    final List<DeezerAlbumToAlbumStubAdaptor> stubList = new ArrayList<>();

    DeezerAlbumList results = getRequest(getTopAlbumsUri(id), DeezerAlbumList.class);
    stubList.addAll(getAlbumStubs(results));

    while (results != null && results.getNext() != null) {
      results = getRequest(results.getNext(), DeezerAlbumList.class);
      stubList.addAll(getAlbumStubs(results));
    }

    return stubList;
  }

  private List<DeezerAlbumToAlbumStubAdaptor> getAlbumStubs(final DeezerAlbumList results) {
    if (results == null || results.getData() == null) {
      return Collections.emptyList();
    }

    return results.getData().stream().map(DeezerAlbumToAlbumStubAdaptor::clone).collect(Collectors.toList());
  }

  public List<DeezerArtistToArtistStubAdaptor> getRelatedArtists(final int id) {
    final DeezerArtistList results = getRequest(getRelatedArtistsUri(id), DeezerArtistList.class);

    if (results == null || results.getData() == null) {
      return Collections.emptyList();
    }

    return results.getData().stream().map(DeezerArtistToArtistStubAdaptor::clone).collect(Collectors.toList());
  }

  private URI getArtistInfoUri(final int id) {
    return generateUri(BASE_URI + String.format(ARTIST_INFO_URI, id),
                       String.format("There was an issue getting the artist info URI for artist ID %d", id));
  }

  private URI getTopAlbumsUri(final int id) {
    return generateUri(BASE_URI + String.format(TOP_ALBUMS_URI, id),
                       String.format("There was an issue getting the top albums URI for artist ID %d", id));
  }

  private URI getRelatedArtistsUri(final int id) {
    return generateUri(BASE_URI + String.format(RELATED_ARTISTS_URI, id),
                       String.format("There was an issue getting the related artists URI for artist ID %d", id));
  }
}
