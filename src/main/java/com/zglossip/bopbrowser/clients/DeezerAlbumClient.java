package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI;

@Service
public class DeezerAlbumClient extends AbstractClient {
  public static final String ALBUM_INFO_URI = "/album/%d";

  @Autowired
  public DeezerAlbumClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public AlbumDeezerAdaptor getAlbumInfo(final int id) {
    return getRequest(getAlbumInfoUri(id), AlbumDeezerAdaptor.class);
  }

  public AlbumStubDeezerAdaptor getAlbumStub(final int id) {
    return getRequest(getAlbumInfoUri(id), AlbumStubDeezerAdaptor.class);
  }

  private URI getAlbumInfoUri(final int id) {
    return generateUri(BASE_URI + String.format(ALBUM_INFO_URI, id),
                       String.format("There was an issue generating the URI for album %d", id));
  }
}
