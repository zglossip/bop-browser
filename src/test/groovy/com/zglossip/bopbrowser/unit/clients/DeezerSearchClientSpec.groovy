package com.zglossip.bopbrowser.unit.clients

import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.SearchResults
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbumList
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtistList
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import java.nio.charset.StandardCharsets

import static com.zglossip.bopbrowser.util.MiscConstants.DEEZER_BASE_URI

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
    SearchResults<DeezerArtistToArtistStubAdaptor> expected = new SearchResults<>(
        data: [new DeezerArtistToArtistStubAdaptor(id: 1), new DeezerArtistToArtistStubAdaptor(id: 3)],
        total: total
    )
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ARTISTS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> new DeezerArtistList(data: expected.getData(), total: total)
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
    total = 20
  }

  def 'Search artists (empty data)'() {
    given:
    SearchResults<DeezerArtistToArtistStubAdaptor> expected = new SearchResults<>(
        data: [],
        total: 0
    )
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ARTISTS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> new DeezerArtistList(data: expected.getData())
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search artists (null data)'() {
    given:
    SearchResults<DeezerArtistToArtistStubAdaptor> expected = new SearchResults<>(
        data: [],
        total: 0
    )
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ARTISTS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> new DeezerArtistList()
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search artists (null results)'() {
    given:
    SearchResults<DeezerArtistToArtistStubAdaptor> expected = new SearchResults<>(
        data: [],
        total: 0
    )

    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ARTISTS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerArtistToArtistStubAdaptor> results = searchClient.searchArtists(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerArtistList.class) >> null
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search albums'() {
    given:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> expected = new SearchResults<>(
        data: [new DeezerAlbumToAlbumStubAdaptor(id: 1), new DeezerAlbumToAlbumStubAdaptor(id: 3)],
        total: total
    )
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ALBUMS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> new DeezerAlbumList(data: expected.getData(), total: total)
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
    total = 39
  }

  def 'Search albums (empty data)'() {
    given:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> expected = new SearchResults<>(data: [], total: 0)
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ALBUMS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> new DeezerAlbumList(data: expected.getData())
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search albums (null data)'() {
    given:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> expected = new SearchResults<>(data: [])
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ALBUMS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> new DeezerAlbumList()
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search albums (null results)'() {
    given:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> expected = new SearchResults<>(data: [], total: 0)
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_ALBUMS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerAlbumToAlbumStubAdaptor> results = searchClient.searchAlbums(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerAlbumList.class) >> null
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search songs'() {
    given:
    SearchResults<DeezerSongToSongAdaptor> expected = new SearchResults<>(total: total, data: [new DeezerSongToSongAdaptor(id: 1), new DeezerSongToSongAdaptor(id: 3)])
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_SONGS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> new DeezerSongList(data: expected.getData(), total: total)
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
    total = 24
  }

  def 'Search songs (empty data)'() {
    given:
    SearchResults<DeezerSongToSongAdaptor> expected = new SearchResults(data: [], total: 0)
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_SONGS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> new DeezerSongList(data: expected.getData())
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search songs (null data)'() {
    given:
    SearchResults<DeezerSongToSongAdaptor> expected = new SearchResults<>(data: [], total: 0)
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_SONGS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> new DeezerSongList()
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search songs (null results)'() {
    given:
    SearchResults<DeezerSongToSongAdaptor> expected = new SearchResults<>(data: [], total: 0)
    URI expectedUri = new URI(String.format(DEEZER_BASE_URI + searchClient.SEARCH_SONGS_URL,
        URLEncoder.encode(query, StandardCharsets.UTF_8.toString()),
        index,
        limit))

    when:
    SearchResults<DeezerSongToSongAdaptor> results = searchClient.searchSongs(query, index, limit)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSongList.class) >> null
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

}
