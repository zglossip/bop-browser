package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.Song
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.GenreDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenreList
import com.zglossip.bopbrowser.services.AlbumService
import com.zglossip.bopbrowser.services.SongService
import spock.lang.Specification
import spock.lang.Subject

class SongServiceSpec extends Specification {

  @Subject
  SongService songService

  DeezerSearchClient deezerSearchClient
  AlbumService albumService

  def setup() {
    deezerSearchClient = Mock(DeezerSearchClient)
    albumService = Mock(AlbumService)
    songService = new SongService(deezerSearchClient, albumService)
  }

  def 'Search artists'() {
    given:
    def expected = [new SongDeezerAdaptor(id: 3, album: new AlbumStubDeezerAdaptor(id: 1)), new SongDeezerAdaptor(id: 4)]

    when:
    List<Song> results = songService.search(query)

    then:
    1 * deezerSearchClient.searchSongs(query) >> expected
    1 * albumService.getAlbumStub(1) >> new AlbumStubDeezerAdaptor(id: 1, genres: new DeezerGenreList(data: [new GenreDeezerAdaptor(id: 100)]))
    results.equals(expected)
    results.get(0).getAlbumGenres() == [new GenreDeezerAdaptor(id: 100)]
    results.get(1).getAlbumGenres() == []

    where:
    query = 'Test Test'
  }

  def 'Search artists (empty)'() {
    given:
    def expected = []

    when:
    List<Song> results = songService.search(query)

    then:
    1 * deezerSearchClient.searchSongs(query) >> expected
    results.equals(expected)

    where:
    query = 'Test Test'
  }

}
