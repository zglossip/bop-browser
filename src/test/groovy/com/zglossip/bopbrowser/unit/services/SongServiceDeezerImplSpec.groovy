package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.SearchResults
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerGenreToGenreAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import com.zglossip.bopbrowser.domains.categories.Song
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenreList
import com.zglossip.bopbrowser.services.SongContributorService
import com.zglossip.bopbrowser.services.deezer.AlbumServiceDeezerImpl
import com.zglossip.bopbrowser.services.deezer.SongServiceDeezerImpl
import spock.lang.Specification
import spock.lang.Subject

class SongServiceDeezerImplSpec extends Specification {

  @Subject
  SongServiceDeezerImpl songService

  DeezerSearchClient deezerSearchClient
  AlbumServiceDeezerImpl albumService
  SongContributorService songContributorService

  def setup() {
    deezerSearchClient = Mock(DeezerSearchClient)
    albumService = Mock(AlbumServiceDeezerImpl)
    songContributorService = Mock(SongContributorService)
    songService = new SongServiceDeezerImpl(deezerSearchClient, albumService, songContributorService)
  }

  def 'Search songs'() {
    given:
    def expected = new SearchResults<>(
        data: [new DeezerSongToSongAdaptor(
            id: 3,
            album: new DeezerAlbumToAlbumStubAdaptor(id: 1),
            contributors: [new DeezerArtistToArtistStubAdaptor(id: 300)]
        ), new DeezerSongToSongAdaptor(id: 4)],
        total: total
    )

    when:
    SearchResults<Song> results = songService.search(query, index, limit)

    then:
    1 * deezerSearchClient.searchSongs(query, index, limit) >> new SearchResults<>(data: [new DeezerSongToSongAdaptor(id: 3, album: new DeezerAlbumToAlbumStubAdaptor(id: 1)), new DeezerSongToSongAdaptor(id: 4)], total: total)
    1 * albumService.getAlbumStub(1) >> new DeezerAlbumToAlbumStubAdaptor(id: 1, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(id: 100)]))
    1 * songContributorService.getSongListWithContributors([new DeezerSongToSongAdaptor(id: 3, album: new DeezerAlbumToAlbumStubAdaptor(id: 1)), new DeezerSongToSongAdaptor(id: 4)]) >> expected.getData()
    results.equals(expected)
    results.getData().get(0).getAlbumGenres() == [new DeezerGenreToGenreAdaptor(id: 100)]
    results.getData().get(1).getAlbumGenres() == []

    where:
    query = 'Test Test'
    index = 1
    limit = 2
    total = 737
  }

  def 'Search artists (empty)'() {
    given:
    def expected = new SearchResults<>(data: [], total: 0)

    when:
    SearchResults<Song> results = songService.search(query, index, limit)

    then:
    1 * deezerSearchClient.searchSongs(query, index, limit) >> expected
    1 * songContributorService.getSongListWithContributors(expected.getData()) >> expected.getData()
    results.equals(expected)

    where:
    query = 'Test Test'
    index = 1
    limit = 2
  }

}
