package com.zglossip.bopbrowser.daos;

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor;
import com.zglossip.bopbrowser.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeezerAlbumDao extends AbstractDao {
  public static final String ALBUM_INFO_URI = "/album/%d";

  @Autowired
  public DeezerAlbumDao(final ApiUtil apiUtil) {
    super(apiUtil);
  }

  public AlbumDeezerAdaptor getAlbumInfo(final int id) {
    //TODO Fill out
    return null;
  }
}
