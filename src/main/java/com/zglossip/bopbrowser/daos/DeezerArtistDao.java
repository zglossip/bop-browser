package com.zglossip.bopbrowser.daos;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor;
import com.zglossip.bopbrowser.exceptions.InvalidInputException;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI;

@Service
public class DeezerArtistDao extends AbstractDao {

  public static final String ARTIST_INFO_URI = "/artist/%d";
  public static final String TOP_ALBUMS_URI = ARTIST_INFO_URI + "/albums";
  public static final String RELATED_ARTISTS_URI = ARTIST_INFO_URI + "/related";

  @Autowired
  public DeezerArtistDao(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public ArtistDeezerAdaptor getArtistInfo(final int id) {
    return getRequest(getArtistInfoUrl(id), ArtistDeezerAdaptor.class);
  }

  public List<AlbumStubDeezerAdaptor> getTopAlbums(final int id) {
    //TODO Fill out
    return null;
  }

  public List<ArtistStubDeezerAdaptor> getRelatedArtists(final int id) {
    //TODO Fill out
    return null;
  }

  private URI getArtistInfoUrl(final int id) {
    try {
      return new URI(BASE_URI + String.format(ARTIST_INFO_URI, id));
    } catch (final URISyntaxException e) {
      throw new InvalidInputException(String.format("There was an issue getting the artist info for artist ID %d", id), e);
    }
  }
}
