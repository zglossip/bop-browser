package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.Album
import com.zglossip.bopbrowser.domains.AlbumStub
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.services.AlbumService
import com.zglossip.bopbrowser.services.GenreService
import spock.lang.Specification
import spock.lang.Subject

class AlbumServiceSpec extends Specification {

  @Subject
  AlbumService albumService

  DeezerAlbumClient deezerAlbumClient
  DeezerSearchClient deezerSearchClient
  GenreService genreService

  def setup() {
    deezerAlbumClient = Mock(DeezerAlbumClient)
    deezerSearchClient = Mock(DeezerSearchClient)
    genreService = Mock(GenreService)
    albumService = new AlbumService(deezerAlbumClient, deezerSearchClient, genreService)
  }

  def 'Get album info'() {
    given:
    def expected = new AlbumDeezerAdaptor(id: id)

    when:
    Album result = albumService.getAlbumInfo(id)

    then:
    1 * deezerAlbumClient.getAlbumInfo(id) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Get album info (null)'() {
    given:
    def expected = null

    when:
    Album result = albumService.getAlbumInfo(id)

    then:
    1 * deezerAlbumClient.getAlbumInfo(id) >> expected
    result.equals(expected)

    where:
    id = 123
  }

  def 'Search album'() {
    given:
    def expected = [new AlbumStubDeezerAdaptor(id: 1), new AlbumStubDeezerAdaptor(id: 2)]

    when:
    List<AlbumStub> results = albumService.search(query)

    then:
    1 * deezerSearchClient.searchAlbums(query) >> expected
    1 * genreService.populateAlbumStubGenre(expected)
    results.equals(expected)

    where:
    query = 'Test query'
  }

  def 'Search album (empty)'() {
    given:
    def expected = []

    when:
    List<AlbumStub> results = albumService.search(query)

    then:
    1 * deezerSearchClient.searchAlbums(query) >> expected
    results.equals(expected)

    where:
    query = 'Test query'
  }

}
