package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.BasicClient
import com.zglossip.bopbrowser.clients.DeezerArtistClient
import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.AlbumStub
import com.zglossip.bopbrowser.domains.Artist
import com.zglossip.bopbrowser.domains.ArtistStub
import com.zglossip.bopbrowser.domains.adaptor.deezer.*
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenreList
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSongList
import com.zglossip.bopbrowser.services.ArtistService
import com.zglossip.bopbrowser.services.GenreService
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors

class ArtistServiceSpec extends Specification {

  @Subject
  ArtistService artistService

  DeezerArtistClient deezerArtistClient
  DeezerSearchClient deezerSearchClient
  BasicClient basicClient
  GenreService genreService

  def setup() {
    deezerArtistClient = Mock(DeezerArtistClient)
    deezerSearchClient = Mock(DeezerSearchClient)
    basicClient = Mock(BasicClient)
    genreService = Mock(GenreService)
    artistService = new ArtistService(deezerArtistClient, deezerSearchClient, basicClient, genreService)
  }

  def 'Get artist info'() {
    given:
    def expectedGenres = [
        new DeezerGenreToGenreAdaptor(name: 'Genre4'),
        new DeezerGenreToGenreAdaptor(name: 'Genre2'),
        new DeezerGenreToGenreAdaptor(name: 'Genre1')
    ]

    def artist = new DeezerArtistToArtistAdaptor(
        id: id,
        tracklist: tracklistUri
    )

    def expected = new DeezerArtistToArtistAdaptor(
        id: id,
        genreList: expectedGenres,
        topSongList: topSongList.stream().limit(nbTracks).collect(Collectors.toList()),
        topAlbumList: topAlbumList.stream().limit(nbAlbums).collect(Collectors.toList()),
        relatedArtistList: relatedArtistList.stream().limit(nbArtists).collect(Collectors.toList()),
        tracklist: tracklistUri
    )

    when:
    Artist result = artistService.getArtistInfo(id, expectedGenres.size(), nbTracks, nbAlbums, nbArtists)

    then:
    1 * deezerArtistClient.getArtistInfo(id) >> artist
    1 * deezerArtistClient.getTopAlbums(id) >> topAlbumList
    1 * genreService.populateAlbumStubGenre(topAlbumList)
    1 * basicClient.getRequest(tracklistUri, DeezerSongList.class) >> new DeezerSongList(data: topSongList)
    1 * deezerArtistClient.getRelatedArtists(id) >> relatedArtistList
    result.equals(expected)

    where:
    id = 234
    tracklistUri = new URI("tracklist")
    topSongList = [new DeezerSongToSongStubAdaptor(id: 1), new DeezerSongToSongStubAdaptor(id: 4), new DeezerSongToSongStubAdaptor(id: 7)]
    topAlbumList = [new DeezerAlbumToAlbumStubAdaptor(id: 2, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(name: 'Genre1'), new DeezerGenreToGenreAdaptor(name: 'Genre2')])),
                    new DeezerAlbumToAlbumStubAdaptor(id: 24, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(name: 'Genre2'), new DeezerGenreToGenreAdaptor(name: 'Genre3'), new DeezerGenreToGenreAdaptor(name: 'Genre4')])),
                    new DeezerAlbumToAlbumStubAdaptor(id: 25, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(name: 'Genre4')])),
                    new DeezerAlbumToAlbumStubAdaptor(id: 26, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(name: 'Genre4')])),
                    new DeezerAlbumToAlbumStubAdaptor(id: 27, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(name: 'Genre4')])),
                    new DeezerAlbumToAlbumStubAdaptor(id: 28, genres: new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(name: 'Genre4')]))]
    relatedArtistList = [new DeezerArtistToArtistStubAdaptor(id: 3939), new DeezerArtistToArtistStubAdaptor(id: 328)]
    nbTracks = 2
    nbAlbums = 1
    nbArtists = 3838
  }

  def 'Get artist info (null)'() {
    given:
    def expected = null

    when:
    Artist result = artistService.getArtistInfo(id, nbGenres, nbAlbums, nbArtists, nbTracks)

    then:
    1 * deezerArtistClient.getArtistInfo(id) >> expected
    result.equals(expected)

    where:
    id = 234
    nbGenres = 3
    nbTracks = 28
    nbAlbums = 8
    nbArtists = 3838
  }

  def 'Get artist albums'() {
    given:
    def expected = [new DeezerAlbumToAlbumStubAdaptor(id: 3), new DeezerAlbumToAlbumStubAdaptor(id: 4)]

    when:
    List<AlbumStub> results = artistService.getArtistAlbums(id)

    then:
    1 * deezerArtistClient.getTopAlbums(id) >> expected
    results.equals(expected)

    where:
    id = 928
  }

  def 'Get artist albums (empty)'() {
    given:
    def expected = []

    when:
    List<AlbumStub> results = artistService.getArtistAlbums(id)

    then:
    1 * deezerArtistClient.getTopAlbums(id) >> expected
    results.equals(expected)

    where:
    id = 928
  }

  def 'Get related artists'() {
    given:
    def expected = [new DeezerArtistToArtistStubAdaptor(id: 3), new DeezerAlbumToAlbumStubAdaptor(id: 4)]

    when:
    List<ArtistStub> results = artistService.getRelatedArtists(id)

    then:
    1 * deezerArtistClient.getRelatedArtists(id) >> expected
    results.equals(expected)

    where:
    id = 928
  }

  def 'Get related artists (empty)'() {
    given:
    def expected = []

    when:
    List<ArtistStub> results = artistService.getRelatedArtists(id)

    then:
    1 * deezerArtistClient.getRelatedArtists(id) >> expected
    results.equals(expected)

    where:
    id = 928
  }

  def 'Search artists'() {
    given:
    def artists = [new DeezerArtistToArtistStubAdaptor(id: 3), new DeezerArtistToArtistStubAdaptor(id: 4)]

    def expected = [new DeezerArtistToArtistStubAdaptor(id: 3), new DeezerArtistToArtistStubAdaptor(id: 4)]

    when:
    List<ArtistStub> results = artistService.search(query)

    then:
    1 * deezerSearchClient.searchArtists(query) >> artists
    results.equals(expected)

    where:
    query = 'Test Test'
  }

  def 'Search artists (empty)'() {
    given:
    def expected = []

    when:
    List<ArtistStub> results = artistService.search(query)

    then:
    1 * deezerSearchClient.searchArtists(query) >> expected
    results.equals(expected)

    where:
    query = 'Test Test'
  }

}
