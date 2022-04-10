package com.zglossip.bopbrowser.unit.daos

import com.zglossip.bopbrowser.daos.DeezerAlbumDao
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbum
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

class DeezerAlbumDaoSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerAlbumDao albumDao

  def setup() {
    apiUtil = Mock(ApiUtil)
    albumDao = new DeezerAlbumDao(apiUtil)
  }

  def 'Get album info'() {
    given:
    AlbumDeezerAdaptor expected = new DeezerAlbum(id: id)

    when:
    AlbumDeezerAdaptor result = albumDao.getAlbumInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(albumDao.ALBUM_INFO_URI, id)), AlbumDeezerAdaptor.class) >> expected
    result == expected

    where:
    id = 123
  }

  def 'Get album info (null result)'() {
    given:
    AlbumDeezerAdaptor expected = null

    when:
    AlbumDeezerAdaptor result = albumDao.getAlbumInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(albumDao.ALBUM_INFO_URI, id)), AlbumDeezerAdaptor.class) >> expected
    result == expected

    where:
    id = 123
  }

}
