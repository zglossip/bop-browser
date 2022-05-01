package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI;

@Service
public class DeezerSongClient extends AbstractClient {

  public static final String TRACK_INFO_URI = "/track/%d";

  @Autowired
  public DeezerSongClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public DeezerSongToSongAdaptor getSongInfo(final int id) {
    return getRequest(getSongInfoUri(id), DeezerSongToSongAdaptor.class);
  }

  private URI getSongInfoUri(final int id) {
    return generateUri(BASE_URI + String.format(TRACK_INFO_URI, id),
                       String.format("There was an issue getting the track info for ID %d", id));
  }
}
