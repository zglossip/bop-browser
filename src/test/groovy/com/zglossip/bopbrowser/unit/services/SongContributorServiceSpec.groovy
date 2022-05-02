package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.DeezerSongClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor
import com.zglossip.bopbrowser.services.SongContributorService
import spock.lang.Specification
import spock.lang.Subject

class SongContributorServiceSpec extends Specification {

  @Subject
  SongContributorService songContributorService

  DeezerSongClient deezerSongClient

  def setup() {
    deezerSongClient = Mock(DeezerSongClient)
    songContributorService = new SongContributorService(deezerSongClient)
  }

  def 'Get song list with contributors (has contributors)'() {
    given:
    def expected = [new DeezerSongToSongStubAdaptor(
        id: songId,
        contributors: [contributer1, contributer2],
        artist: artist
    )]

    when:
    def result = songContributorService.getSongListWithContributors(songList)

    then:
    0 * deezerSongClient.getSongInfo(_)
    result.equals(expected)
    result[0].getFeaturingList() == [contributer1, contributer2]

    where:
    contributer1 = new DeezerArtistToArtistStubAdaptor(id: 100)
    contributer2 = new DeezerArtistToArtistStubAdaptor(id: 200)
    artist = new DeezerArtistToArtistStubAdaptor(id: 300)
    songId = 1
    songList = [
        new DeezerSongToSongAdaptor(id: songId, contributors: [contributer1, contributer2], artist: artist)
    ]
  }

  def 'Get song list with contributers (no contributers)'() {
    given:
    def expected = [new DeezerSongToSongStubAdaptor(id: 1, artist: artist, contributors: [contributer1, contributer2])]

    when:
    def result = songContributorService.getSongListWithContributors(songList)

    then:
    1 * deezerSongClient.getSongInfo(songId) >> new DeezerSongToSongAdaptor(id: songId, artist: artist, contributors: [contributer1, contributer2])
    result.equals(expected)
    result[0].getFeaturingList() == [contributer1, contributer2]

    where:
    contributer1 = new DeezerArtistToArtistStubAdaptor(id: 100)
    contributer2 = new DeezerArtistToArtistStubAdaptor(id: 200)
    artist = new DeezerArtistToArtistStubAdaptor(id: 300)
    songId = 1
    songList = [new DeezerSongToSongAdaptor(id: songId, artist: artist)]
  }

  def 'Get song list with contributers (empty songs)'() {
    given:
    def expected = []

    when:
    def result = songContributorService.getSongListWithContributors(songList)

    then:
    0 * deezerSongClient.getSongInfo(_)
    result.equals(expected)

    where:
    songList = []
  }

  def 'Get song list with contributers (null)'() {
    given:
    def expected = []

    when:
    def result = songContributorService.getSongListWithContributors(songList)

    then:
    0 * deezerSongClient.getSongInfo(_)
    result.equals(expected)

    where:
    songList = null
  }

}
