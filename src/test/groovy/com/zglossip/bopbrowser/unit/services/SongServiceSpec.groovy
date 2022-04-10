package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.Song
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor
import com.zglossip.bopbrowser.services.SongService
import spock.lang.Specification
import spock.lang.Subject

class SongServiceSpec extends Specification {

  @Subject
  SongService songService

  DeezerSearchClient deezerSearchClient

  def setup() {
    deezerSearchClient = Mock(DeezerSearchClient)
    songService = new SongService(deezerSearchClient)
  }

  def 'Search artists'() {
    given:
    def expected = [new SongDeezerAdaptor(id: 3), new SongDeezerAdaptor(id: 4)]

    when:
    List<Song> results = songService.search(query)

    then:
    1 * deezerSearchClient.searchSongs(query) >> expected
    results.equals(expected)

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
