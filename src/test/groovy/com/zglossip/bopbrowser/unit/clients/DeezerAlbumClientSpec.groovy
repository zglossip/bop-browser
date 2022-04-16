package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
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
    result.equals(expected)

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
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get album stub'() {
    given:
    AlbumStubDeezerAdaptor expected = new AlbumStubDeezerAdaptor(id: id)

    when:
    AlbumStubDeezerAdaptor result = albumClient.getAlbumStub(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), AlbumStubDeezerAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get album stub (null)'() {
    given:
    AlbumStubDeezerAdaptor expected = null

    when:
    AlbumStubDeezerAdaptor result = albumClient.getAlbumStub(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(albumClient.ALBUM_INFO_URI, id)), AlbumStubDeezerAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

}
