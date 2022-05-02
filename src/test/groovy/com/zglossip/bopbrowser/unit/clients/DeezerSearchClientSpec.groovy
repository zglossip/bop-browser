package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.*
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import java.nio.charset.StandardCharsets

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

class DeezerSearchClientSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerSearchClient searchClient

  def setup() {
    apiUtil = Mock(ApiUtil)
    searchClient = new DeezerSearchClient(apiUtil)
  }

  def 'Search artists'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = [new DeezerArtistToArtistStubAdaptor(id: 1), new DeezerArtistToArtistStubAdaptor(id: 3)]
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> new DeezerArtistList(data: expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search artists (empty data)'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> new DeezerArtistList(data: expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search artists (null data)'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> new DeezerArtistList()
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search artists (null results)'() {
    given:
    List<DeezerArtistToArtistStubAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> null
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search albums'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = [new DeezerAlbum(id: 1), new DeezerAlbum(id: 3)]
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> new DeezerAlbumList(data: expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search albums (empty data)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> new DeezerAlbumList(data: expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search albums (null data)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> new DeezerAlbumList()
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search albums (null results)'() {
    given:
    List<DeezerAlbumToAlbumStubAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> null
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search songs'() {
    given:
    List<DeezerSongToSongAdaptor> expected = [new DeezerSong(id: 1), new DeezerSong(id: 3)]
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> new DeezerSongList(data: expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search songs (empty data)'() {
    given:
    List<DeezerSongToSongAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> new DeezerSongList(data: expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search songs (null data)'() {
    given:
    List<DeezerSongToSongAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> new DeezerSongList()
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search songs (null results)'() {
    given:
    List<DeezerSongToSongAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchClient.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> null
    results.equals(expected)

    where:
    query = 'Test query'
  }

}
