package com.zglossip.bopbrowser.clients;

import com.zglossip.bopbrowser.domains.models.lyricsovh.LyricsOvhLyrics;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.zglossip.bopbrowser.util.ApiUtil.generateUri;
import static com.zglossip.bopbrowser.util.MiscConstants.LYRICS_OVH_BASE_URL;

@Service
public class LyricsOvhClient extends AbstractClient {

  public final String LYRICS_URL = "/%s/%s";

  @Autowired
  public LyricsOvhClient(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public LyricsOvhLyrics getLyrics(final String artist, final String song) {
    return getRequest(getLyricsUri(artist, song), LyricsOvhLyrics.class);
  }

  private URI getLyricsUri(final String artist, final String song) {
    return generateUri(LYRICS_OVH_BASE_URL + String.format(LYRICS_URL,
                                                           URLEncoder.encode(artist, StandardCharsets.UTF_8),
                                                           URLEncoder.encode(song, StandardCharsets.UTF_8)),
                       String.format("There was an issue getting the lyrics for song %s - %s", artist, song));
  }
}
