package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //TODO Fill out
    return null;
  }

  public List<AlbumStubDeezerAdaptor> searchAlbums(final String query) {
    //TODO Fill out
    return null;
  }

  public List<SongDeezerAdaptor> searchSongs(final String query) {
    //TODO Fill out
    return null;
  }
}
