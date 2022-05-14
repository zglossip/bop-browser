package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.BasicClient
import com.zglossip.bopbrowser.clients.DeezerAlbumClient
import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.SearchResults
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor
import com.zglossip.bopbrowser.domains.categories.Album
import com.zglossip.bopbrowser.domains.categories.AlbumStub
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList
import com.zglossip.bopbrowser.services.DeezerGenreService
import com.zglossip.bopbrowser.services.SongContributorService
import com.zglossip.bopbrowser.services.deezer.AlbumServiceDeezerImpl
import spock.lang.Specification
import spock.lang.Subject

class AlbumServiceDeezerImplSpec extends Specification {

  @Subject
  AlbumServiceDeezerImpl albumService

  DeezerAlbumClient deezerAlbumClient
  DeezerSearchClient deezerSearchClient
  SongContributorService songContributorService
  BasicClient basicClient
  DeezerGenreService genreService

  def setup() {
    deezerAlbumClient = Mock(DeezerAlbumClient)
    deezerSearchClient = Mock(DeezerSearchClient)
    songContributorService = Mock(SongContributorService)
    basicClient = Mock(BasicClient)
    genreService = Mock(DeezerGenreService)
    albumService = new AlbumServiceDeezerImpl(deezerAlbumClient, deezerSearchClient, songContributorService, basicClient, genreService)
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
    def expected = new SearchResults<>(data: [new DeezerAlbumToAlbumStubAdaptor(id: 1), new DeezerAlbumToAlbumStubAdaptor(id: 2)])

    when:
    SearchResults<AlbumStub> results = albumService.search(query, index, limit)

    then:
    1 * deezerSearchClient.searchAlbums(query, index, limit) >> expected
    1 * genreService.populateAlbumStubGenre(expected.getData())
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

  def 'Search album (empty)'() {
    given:
    def expected = new SearchResults<>(data: [], total: 0)

    when:
    SearchResults<AlbumStub> results = albumService.search(query, index, limit)

    then:
    1 * deezerSearchClient.searchAlbums(query, index, limit) >> expected
    results.equals(expected)

    where:
    query = 'Test query'
    index = 1
    limit = 2
  }

}
