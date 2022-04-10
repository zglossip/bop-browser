package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistAlbumsResult;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerRelatedArtistsResult;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

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

  public ArtistDeezerAdaptor getArtistInfo(final int id) {
    return getRequest(getArtistInfoUri(id), ArtistDeezerAdaptor.class);
  }

  public List<AlbumStubDeezerAdaptor> getTopAlbums(final int id) {
    final DeezerArtistAlbumsResult results = getRequest(getTopAlbumsUri(id), DeezerArtistAlbumsResult.class);

    if (results == null || results.getData() == null) {
      return Collections.emptyList();
    }

    return results.getData();
  }

  public List<ArtistStubDeezerAdaptor> getRelatedArtists(final int id) {
    final DeezerRelatedArtistsResult results = getRequest(getRelatedArtistsUri(id), DeezerRelatedArtistsResult.class);

    if (results == null || results.getData() == null) {
      return Collections.emptyList();
    }

    return results.getData();
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
