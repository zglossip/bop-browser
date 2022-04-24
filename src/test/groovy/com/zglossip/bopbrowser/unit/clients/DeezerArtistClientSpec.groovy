package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerArtistClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbumList
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistList
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

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
    ArtistDeezerAdaptor expected = new ArtistDeezerAdaptor(id: id)

    when:
    ArtistDeezerAdaptor result = artistClient.getArtistInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.ARTIST_INFO_URI, id)), ArtistDeezerAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist info (null result)'() {
    given:
    ArtistDeezerAdaptor expected = null

    when:
    ArtistDeezerAdaptor result = artistClient.getArtistInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.ARTIST_INFO_URI, id)), ArtistDeezerAdaptor.class) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = [new AlbumStubDeezerAdaptor(id: 1), new AlbumStubDeezerAdaptor(id: 2)]

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums (multi page)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected1 = [new AlbumStubDeezerAdaptor(id: 1), new AlbumStubDeezerAdaptor(id: 2)]
    List<AlbumStubDeezerAdaptor> expected2 = [new AlbumStubDeezerAdaptor(id: 3), new AlbumStubDeezerAdaptor(id: 4)]
    List<AlbumStubDeezerAdaptor> expected3 = [new AlbumStubDeezerAdaptor(id: 5)]

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList(data: expected1, next: nextUrl1)
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
    List<AlbumStubDeezerAdaptor> expected = []

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums (null data)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> new DeezerAlbumList()
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get artist albums (null result)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerAlbumList.class) >> null
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = [new DeezerArtist(id: 1), new DeezerArtist(id: 2)]

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> new DeezerArtistList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists (empty data)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> new DeezerArtistList(data: expected)
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists (null data)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> new DeezerArtistList()
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get related artists (null result)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerArtistList.class) >> null
    result.equals(expected)

    where:
    id = 123
  }

}
