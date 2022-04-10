package com.zglossip.bopbrowser.unit.daos

import com.zglossip.bopbrowser.clients.DeezerArtistClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbum
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistAlbumsResult
import com.zglossip.bopbrowser.domains.models.deezer.DeezerRelatedArtistsResult
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
    ArtistDeezerAdaptor expected = new DeezerArtist(id: id)

    when:
    ArtistDeezerAdaptor result = artistClient.getArtistInfo(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.ARTIST_INFO_URI, id)), ArtistDeezerAdaptor.class) >> expected
    result == expected

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
    result == expected

    where:
    id = 123
  }

  def 'Get artist albums'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = [new DeezerAlbum(id: 1), new DeezerAlbum(id: 2)]

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerArtistAlbumsResult.class) >> new DeezerArtistAlbumsResult(data: expected)
    result == expected

    where:
    id = 123
  }

  def 'Get artist albums (empty data)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerArtistAlbumsResult.class) >> new DeezerArtistAlbumsResult(data: expected)
    result == expected

    where:
    id = 123
  }

  def 'Get artist albums (null data)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerArtistAlbumsResult.class) >> new DeezerArtistAlbumsResult()
    result == expected

    where:
    id = 123
  }

  def 'Get artist albums (null result)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []

    when:
    List<AlbumStubDeezerAdaptor> result = artistClient.getTopAlbums(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.TOP_ALBUMS_URI, id)), DeezerArtistAlbumsResult.class) >> null
    result == expected

    where:
    id = 123
  }

  def 'Get related artists'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = [new DeezerArtist(id: 1), new DeezerArtist(id: 2)]

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerRelatedArtistsResult.class) >> new DeezerRelatedArtistsResult(data: expected)
    result == expected

    where:
    id = 123
  }

  def 'Get related artists (empty data)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerRelatedArtistsResult.class) >> new DeezerRelatedArtistsResult(data: expected)
    result == expected

    where:
    id = 123
  }

  def 'Get related artists (null data)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerRelatedArtistsResult.class) >> new DeezerRelatedArtistsResult()
    result == expected

    where:
    id = 123
  }

  def 'Get related artists (null result)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []

    when:
    List<ArtistStubDeezerAdaptor> result = artistClient.getRelatedArtists(id)

    then:
    1 * apiUtil.getRequest(new URI(BASE_URI + String.format(artistClient.RELATED_ARTISTS_URI, id)), DeezerRelatedArtistsResult.class) >> null
    result == expected

    where:
    id = 123
  }

}
