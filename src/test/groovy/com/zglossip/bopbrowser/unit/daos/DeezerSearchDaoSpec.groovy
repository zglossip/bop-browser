package com.zglossip.bopbrowser.unit.daos

import com.zglossip.bopbrowser.daos.DeezerSearchDao
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.*
import com.zglossip.bopbrowser.util.ApiUtil
import spock.lang.Specification
import spock.lang.Subject

import java.nio.charset.StandardCharsets

import static com.zglossip.bopbrowser.util.MiscConstants.BASE_URI

class DeezerSearchDaoSpec extends Specification {

  private ApiUtil apiUtil

  @Subject
  private DeezerSearchDao searchDao

  def setup() {
    apiUtil = Mock(ApiUtil)
    searchDao = new DeezerSearchDao(apiUtil)
  }

  def 'Search artists'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = [new DeezerArtist(id: 1), new DeezerArtist(id: 3)]
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<ArtistStubDeezerAdaptor> results = searchDao.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchArtistResult.class) >> new DeezerSearchArtistResult(data: expected)
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search artists (empty data)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<ArtistStubDeezerAdaptor> results = searchDao.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchArtistResult.class) >> new DeezerSearchArtistResult(data: expected)
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search artists (null data)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<ArtistStubDeezerAdaptor> results = searchDao.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchArtistResult.class) >> new DeezerSearchArtistResult()
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search artists (null results)'() {
    given:
    List<ArtistStubDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ARTISTS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<ArtistStubDeezerAdaptor> results = searchDao.searchArtists(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchArtistResult.class) >> null
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search albums'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = [new DeezerAlbum(id: 1), new DeezerAlbum(id: 3)]
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<AlbumStubDeezerAdaptor> results = searchDao.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchAlbumResult.class) >> new DeezerSearchAlbumResult(data: expected)
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search albums (empty data)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<AlbumStubDeezerAdaptor> results = searchDao.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchAlbumResult.class) >> new DeezerSearchAlbumResult(data: expected)
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search albums (null data)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<AlbumStubDeezerAdaptor> results = searchDao.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchAlbumResult.class) >> new DeezerSearchAlbumResult()
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search albums (null results)'() {
    given:
    List<AlbumStubDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_ALBUMS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<AlbumStubDeezerAdaptor> results = searchDao.searchAlbums(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchAlbumResult.class) >> null
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search songs'() {
    given:
    List<SongDeezerAdaptor> expected = [new DeezerSong(id: 1), new DeezerSong(id: 3)]
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<SongDeezerAdaptor> results = searchDao.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchSongResult.class) >> new DeezerSearchSongResult(data: expected)
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search songs (empty data)'() {
    given:
    List<SongDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<SongDeezerAdaptor> results = searchDao.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchSongResult.class) >> new DeezerSearchSongResult(data: expected)
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search songs (null data)'() {
    given:
    List<SongDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<SongDeezerAdaptor> results = searchDao.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchSongResult.class) >> new DeezerSearchSongResult()
    results == expected

    where:
    query = 'Test query'
  }

  def 'Search songs (null results)'() {
    given:
    List<SongDeezerAdaptor> expected = []
    URI expectedUri = new URI(String.format(BASE_URI + searchDao.SEARCH_SONGS_URL, URLEncoder.encode(query, StandardCharsets.UTF_8.toString())))

    when:
    List<SongDeezerAdaptor> results = searchDao.searchSongs(query)

    then:
    1 * apiUtil.getRequest(expectedUri, DeezerSearchSongResult.class) >> null
    results == expected

    where:
    query = 'Test query'
  }

}
