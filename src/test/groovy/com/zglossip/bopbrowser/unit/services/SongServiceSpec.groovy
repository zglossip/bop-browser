package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.Song
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerGenreToGenreAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenreList
import com.zglossip.bopbrowser.services.AlbumService
import com.zglossip.bopbrowser.services.SongContributorService
import com.zglossip.bopbrowser.services.SongService
import spock.lang.Specification
import spock.lang.Subject

class SongServiceSpec extends Specification {

  @Subject
  SongService songService

  DeezerSearchClient deezerSearchClient
  AlbumService albumService
  SongContributorService songContributorService

  def setup() {
    deezerSearchClient = Mock(DeezerSearchClient)
    albumService = Mock(AlbumService)
    songContributorService = Mock(SongContributorService)
    songService = new SongService(deezerSearchClient, albumService, songContributorService)
  }

  def 'Search songs'() {
    given:
    def expected = [new DeezerSongToSongAdaptor(
        id: 3,
        album: new DeezerAlbumToAlbumStubAdaptor(id: 1),
        contributors: [new DeezerArtistToArtistStubAdaptor(id: 300)]
    ), new DeezerSongToSongAdaptor(id: 4)]

    when:
    List<Song> results = songService.search(query)

    then:
    1 * deezerSearchClient.searchSongs(query) >> [new DeezerSongToSongAdaptor(id: 3, album: new DeezerAlbumToAlbumStubAdaptor(id: 1)), new DeezerSongToSongAdaptor(id: 4)]
    1 * albumService.getAlbumStub(1) >> new DeezerAlbumToAlbumStubAdaptor(id: 1, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(id: 100)]))
    1 * songContributorService.getSongListWithContributors([new DeezerSongToSongAdaptor(id: 3, album: new DeezerAlbumToAlbumStubAdaptor(id: 1)), new DeezerSongToSongAdaptor(id: 4)]) >> expected
    results.equals(expected)
    results.get(0).getAlbumGenres() == [new DeezerGenreToGenreAdaptor(id: 100)]
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
    1 * songContributorService.getSongListWithContributors(expected) >> expected
    results.equals(expected)

    where:
    query = 'Test Test'
  }

}
