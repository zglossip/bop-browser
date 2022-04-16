package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.BasicClient
import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.Album
import com.zglossip.bopbrowser.domains.AlbumStub
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList
import com.zglossip.bopbrowser.services.AlbumService
import com.zglossip.bopbrowser.services.GenreService
import spock.lang.Specification
import spock.lang.Subject

class AlbumServiceSpec extends Specification {

  @Subject
  AlbumService albumService

  DeezerAlbumClient deezerAlbumClient
  DeezerSearchClient deezerSearchClient
  BasicClient basicClient
  GenreService genreService

  def setup() {
    deezerAlbumClient = Mock(DeezerAlbumClient)
    deezerSearchClient = Mock(DeezerSearchClient)
    basicClient = Mock(BasicClient)
    genreService = Mock(GenreService)
    albumService = new AlbumService(deezerAlbumClient, deezerSearchClient, basicClient, genreService)
  }

  def 'Get album info'() {
    given:
    def expected = new AlbumDeezerAdaptor(id: id, tracklist: tracklist)

    when:
    Album result = albumService.getAlbumInfo(id)

    then:
    1 * deezerAlbumClient.getAlbumInfo(id) >> expected
    1 * basicClient.getRequest(tracklist, DeezerSongList.class) >> new DeezerSongList(data: songList)
    result.equals(expected)
    result.getSongList().equals(songList)

    where:
    id = 123
    tracklist = new URI("test")
    songList = [new DeezerSong(id: 1), new DeezerSong(id: 3)]
  }

  def 'Get album info (no tracklist)'() {
    given:
    def expected = new AlbumDeezerAdaptor(id: id)

    when:
    Album result = albumService.getAlbumInfo(id)

    then:
    1 * deezerAlbumClient.getAlbumInfo(id) >> expected
    0 * basicClient.getRequest(_)
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
