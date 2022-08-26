package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerArtistClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbumList
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistList
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.DEEZER_BASE_URI

class DeezerArtistClientSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerArtistClient artistClient

  def setup() {
    apiUtil = Mock(ApiUtil)
    artistClient = new DeezerArtistClient(apiUtil)
  }

  def 'Get artist info'() {
    given:
    DeezerArtistToArtistAdaptor expected = new DeezerArtistToArtistAdaptor(id: id)

    when:
    DeezerArtistToArtistAdaptor result = artistClient.getArtistInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.ARTIST_INFO_URI, id)), DeezerArtistToArtistAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist info (null result)'() {
    given:
    DeezerArtistToArtistAdaptor expected = null

    when:
    DeezerArtistToArtistAdaptor result = artistClient.getArtistInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.ARTIST_INFO_URI, id)), DeezerArtistToArtistAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = [new DeezerAlbumToAlbumStubAdaptor(id: 1), new DeezerAlbumToAlbumStubAdaptor(id: 2)]

    when:
    List<DeezerAlbumToAlbumStubAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums (multi page)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected1 = [new DeezerAlbumToAlbumStubAdaptor(id: 1), new DeezerAlbumToAlbumStubAdaptor(id: 2)]
    List<DeezerAlbumToAlbumStubAdaptor> expected2 = [new DeezerAlbumToAlbumStubAdaptor(id: 3), new DeezerAlbumToAlbumStubAdaptor(id: 4)]
    List<DeezerAlbumToAlbumStubAdaptor> expected3 = [new DeezerAlbumToAlbumStubAdaptor(id: 5)]

    when:
    List<DeezerAlbumToAlbumStubAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList(data: expected1, next: nextUrl1)
    1 * apiUtil.getRequest(nextUrl1, DeezerAlbumList.class) >> new DeezerAlbumList(data: expected2, next: nextUrl2)
    1 * apiUtil.getRequest(nextUrl2, DeezerAlbumList.class) >> new DeezerAlbumList(data: expected3)
    0 * apiUtil.getRequest(_, DeezerAlbumList.class)
    result.equals(expected1 + expected2 + expected3)

    where:
    id = 123
    nextUrl1 = new URI("url1.com")
    nextUrl2 = new URI("url2.com")
  }

  def 'Get artist albums (empty data)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = []

    when:
    List<DeezerAlbumToAlbumStubAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums (null data)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = []

    when:
    List<DeezerAlbumToAlbumStubAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList()
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums (null result)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = []

    when:
    List<DeezerAlbumToAlbumStubAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> null
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = [new DeezerArtist(id: 1), new DeezerArtist(id: 2)]

    when:
    List<DeezerArtistToArtistStubAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> new DeezerArtistList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists (empty data)'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = []

    when:
    List<DeezerArtistToArtistStubAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> new DeezerArtistList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists (null data)'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = []

    when:
    List<DeezerArtistToArtistStubAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> new DeezerArtistList()
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists (null result)'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = []

    when:
    List<DeezerArtistToArtistStubAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(DEEZER_BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> null
    result.equals(expected)

    where:
    id = 123
  }

}
