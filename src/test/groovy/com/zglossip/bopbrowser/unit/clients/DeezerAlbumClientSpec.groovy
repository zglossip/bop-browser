package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

class DeezerAlbumClientSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerAlbumClient albumClient

  def setup() {
    apiUtil = Mock(ApiUtil)
    albumClient = new DeezerAlbumClient(apiUtil)
  }

  def 'Get album info'() {
    given:
    AlbumDeezerAdaptor expected = new AlbumDeezerAdaptor(id: id)

    when:
    AlbumDeezerAdaptor result = albumClient.getAlbumInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), AlbumDeezerAdaptor.class) >> expected
    result == expected

    where:
    id = 123
  }

  def 'Get album info (null result)'() {
    given:
    AlbumDeezerAdaptor expected = null

    when:
    AlbumDeezerAdaptor result = albumClient.getAlbumInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), AlbumDeezerAdaptor.class) >> expected
    result == expected

    where:
    id = 123
  }

}
