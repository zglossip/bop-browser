package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.DEEZER_BASE_URI

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
    DeezerAlbumToAlbumAdaptor expected = new DeezerAlbumToAlbumAdaptor(id: id)

    when:
    DeezerAlbumToAlbumAdaptor result = albumClient.getAlbumInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), DeezerAlbumToAlbumAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get album info (null result)'() {
    given:
    DeezerAlbumToAlbumAdaptor expected = null

    when:
    DeezerAlbumToAlbumAdaptor result = albumClient.getAlbumInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), DeezerAlbumToAlbumAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get album stub'() {
    given:
    DeezerAlbumToAlbumStubAdaptor expected = new DeezerAlbumToAlbumStubAdaptor(id: id)

    when:
    DeezerAlbumToAlbumStubAdaptor result = albumClient.getAlbumStub(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), DeezerAlbumToAlbumStubAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get album stub (null)'() {
    given:
    DeezerAlbumToAlbumStubAdaptor expected = null

    when:
    DeezerAlbumToAlbumStubAdaptor result = albumClient.getAlbumStub(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), DeezerAlbumToAlbumStubAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

}
