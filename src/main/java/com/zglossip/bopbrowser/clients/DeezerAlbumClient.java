package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.DEEZER_BASE_URI;

@Service
public class DeezerAlbumClient extends AbstractClient {
  public static final String ALBUM_INFO_URI = "/album/%d";

  @Autowired
  public DeezerAlbumClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public DeezerAlbumToAlbumAdaptor getAlbumInfo(final int id) {
    return getRequest(getAlbumInfoUri(id), DeezerAlbumToAlbumAdaptor.class);
  }

  public DeezerAlbumToAlbumStubAdaptor getAlbumStub(final int id) {
    return getRequest(getAlbumInfoUri(id), DeezerAlbumToAlbumStubAdaptor.class);
  }

  private URI getAlbumInfoUri(final int id) {
    return generateUri(DEEZER_BASE_URI + String.format(ALBUM_INFO_URI, id),
                       String.format("There was an issue generating the URI for album %d", id));
  }
}
