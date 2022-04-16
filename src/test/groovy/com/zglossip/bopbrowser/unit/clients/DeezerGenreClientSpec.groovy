package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerGenreClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.GenreDeezerAdaptor
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

class DeezerGenreClientSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerGenreClient client

  def setup() {
    apiUtil = Mock(ApiUtil)
    client = new DeezerGenreClient(apiUtil)
  }

  def 'Get artist info'() {
    given:
    GenreDeezerAdaptor expected = new GenreDeezerAdaptor(id: id)

    when:
    GenreDeezerAdaptor result = client.getGenre(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(client.GENRE_URI, id)), GenreDeezerAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist info (null result)'() {
    given:
    GenreDeezerAdaptor expected = null

    when:
    GenreDeezerAdaptor result = client.getGenre(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(client.GENRE_URI, id)), GenreDeezerAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

}
