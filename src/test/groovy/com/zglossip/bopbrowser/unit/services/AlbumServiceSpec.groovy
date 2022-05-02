package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.BasicClient
import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.Album
import com.zglossip.bopbrowser.domains.AlbumStub
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList
import com.zglossip.bopbrowser.services.AlbumService
import com.zglossip.bopbrowser.services.GenreService
import com.zglossip.bopbrowser.services.SongContributorService
import spock.lang.Specification
import spock.lang.Subject

class AlbumServiceSpec extends Specification {

  @Subject
  AlbumService albumService

  DeezerAlbumClient deezerAlbumClient
  DeezerSearchClient deezerSearchClient
  SongContributorService songContributorService
  BasicClient basicClient
  GenreService genreService

  def setup() {
    deezerAlbumClient = Mock(DeezerAlbumClient)
    deezerSearchClient = Mock(DeezerSearchClient)
    songContributorService = Mock(SongContributorService)
    basicClient = Mock(BasicClient)
    genreService = Mock(GenreService)
    albumService = new AlbumService(deezerAlbumClient, deezerSearchClient, songContributorService, basicClient, genreService)
  }

  def 'Get album info'() {
    given:
    def album = new DeezerAlbumToAlbumAdaptor(id: id, tracklist: tracklist)

    and:
    def expected = new DeezerAlbumToAlbumAdaptor(id: id, tracklist: tracklist, songList: songList)

    when:
    Album result = albumService.getAlbumInfo(id)

    then:
    1 * deezerAlbumClient.getAlbumInfo(id) >> album
    1 * basicClient.getRequest(tracklist, DeezerSongList.class) >> new DeezerSongList(data: [new DeezerSong(id: songId1),
                                                                                             new DeezerSong(id: songId2)])
    1 * songContributorService.getSongListWithContributors([new DeezerSong(id: songId1),
                                                            new DeezerSong(id: songId2)]) >> [song1, song2]
    result.equals(expected)

    where:
    id = 123
    tracklist = new URI("test")
    songId1 = 1
    songId2 = 2
    artist1 = new DeezerArtist(id: 100)
    artist2 = new DeezerArtist(id: 200)
    contributerList1 = [artist1]
    contributerList2 = [artist1, artist2]
    song1 = new DeezerSongToSongStubAdaptor(id: songId1, contributors: contributerList1)
    song2 = new DeezerSongToSongStubAdaptor(id: songId2, contributors: contributerList2)
    songList = [song1, song2]
  }

  def 'Get album info (no tracklist)'() {
    given:
    def expected = new DeezerAlbumToAlbumAdaptor(id: id)

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

  def 'Get album stub'() {
    when:
    AlbumStub result = albumService.getAlbumStub(id)

    then:
    1 * deezerAlbumClient.getAlbumStub(id) >> expected
    result == expected

    where:
    id = 100
    expected = new DeezerAlbumToAlbumStubAdaptor(id: id)
  }

  def 'Get album stub (null)'() {
    when:
    AlbumStub result = albumService.getAlbumStub(id)

    then:
    1 * deezerAlbumClient.getAlbumStub(id) >> expected
    result == expected

    where:
    id = 100
    expected = null
  }

  def 'Search album'() {
    given:
    def expected = [new DeezerAlbumToAlbumStubAdaptor(id: 1), new DeezerAlbumToAlbumStubAdaptor(id: 2)]

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
