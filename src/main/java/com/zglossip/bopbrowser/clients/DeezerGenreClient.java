package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerGenreToGenreAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.DEEZER_BASE_URI;

@Service
public class DeezerGenreClient extends AbstractClient {

  public static final String GENRE_URI = "/genre/%d";

  @Autowired
  public DeezerGenreClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public DeezerGenreToGenreAdaptor getGenre(final int id) {
    return getRequest(getGenreUri(id), DeezerGenreToGenreAdaptor.class);
  }

  private URI getGenreUri(final int id) {
    return generateUri(DEEZER_BASE_URI + String.format(GENRE_URI, id),
                       String.format("There was an issue getting the artist info URI for artist ID %d", id));
  }
}
