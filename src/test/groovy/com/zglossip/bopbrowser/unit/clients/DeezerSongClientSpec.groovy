package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerSongClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

class DeezerSongClientSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerSongClient songClient

  def setup() {
    apiUtil = Mock(ApiUtil)
    songClient = new DeezerSongClient(apiUtil)
  }

  def 'Get song info'() {
    given:
    DeezerSongToSongStubAdaptor expected = new DeezerSongToSongAdaptor(id: id)

    when:
    DeezerSongToSongAdaptor result = songClient.getSongInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(songClient.TRACK_INFO_URI, id)), DeezerSongToSongAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 1234
  }

  def 'Get song info (null result)'() {
    given:
    DeezerSongToSongStubAdaptor expected = null

    when:
    DeezerSongToSongStubAdaptor result = songClient.getSongInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(songClient.TRACK_INFO_URI, id)), DeezerSongToSongAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 38758
  }

}
