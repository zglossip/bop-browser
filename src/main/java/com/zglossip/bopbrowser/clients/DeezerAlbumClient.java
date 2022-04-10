package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeezerAlbumClient extends AbstractClient {
  public static final String ALBUM_INFO_URI = "/album/%d";

  @Autowired
  public DeezerAlbumClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public AlbumDeezerAdaptor getAlbumInfo(final int id) {
    //TODO Fill out
    return null;
  }
}
