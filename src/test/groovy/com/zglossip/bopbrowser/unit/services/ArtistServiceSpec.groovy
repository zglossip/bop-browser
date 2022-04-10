package com.zglossip.bopbrowser.unit.services

import com.zglossip.bopbrowser.clients.BasicClient
import com.zglossip.bopbrowser.clients.DeezerArtistClient
import com.zglossip.bopbrowser.clients.DeezerSearchClient
import com.zglossip.bopbrowser.domains.AlbumStub
import com.zglossip.bopbrowser.domains.Artist
import com.zglossip.bopbrowser.domains.ArtistStub
import com.zglossip.bopbrowser.domains.adaptor.deezer.*
import com.zglossip.bopbrowser.domains.models.deezer.DeezerTopSongsResult
import com.zglossip.bopbrowser.services.ArtistService
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors

class ArtistServiceSpec extends Specification {

  @Subject
  ArtistService artistService

  DeezerArtistClient deezerArtistClient
  DeezerSearchClient deezerSearchClient
  BasicClient basicClient

  def setup() {
    deezerArtistClient = Mock(DeezerArtistClient)
    deezerSearchClient = Mock(DeezerSearchClient)
    basicClient = Mock(BasicClient)
    artistService = new ArtistService(deezerArtistClient, deezerSearchClient, basicClient)
  }

  def 'Get artist info'() {
    given:
    def expectedGenres = [
        new GenreDeezerAdaptor(name: 'Genre4'),
        new GenreDeezerAdaptor(name: 'Genre2'),
        new GenreDeezerAdaptor(name: 'Genre1')
    ]

    def artist = new ArtistDeezerAdaptor(
        id: id,
        tracklist: tracklistUri
    )

    def expected = new ArtistDeezerAdaptor(
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
    1 * basicClient.getRequest(tracklistUri, DeezerTopSongsResult.class) >> new DeezerTopSongsResult(data: topSongList)
    1 * deezerArtistClient.getRelatedArtists(id) >> relatedArtistList
    0 * _
    result.equals(expected)

    where:
    id = 234
    tracklistUri = new URI("tracklist")
    topSongList = [new SongStubDeezerAdaptor(id: 1), new SongStubDeezerAdaptor(id: 4), new SongStubDeezerAdaptor(id: 7)]
    topAlbumList = [new AlbumStubDeezerAdaptor(id: 2, genres: [new GenreDeezerAdaptor(name: 'Genre1'), new GenreDeezerAdaptor(name: 'Genre2')]),
                    new AlbumStubDeezerAdaptor(id: 24, genres: [new GenreDeezerAdaptor(name: 'Genre2'), new GenreDeezerAdaptor(name: 'Genre3'), new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 25, genres: [new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 26, genres: [new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 27, genres: [new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 28, genres: [new GenreDeezerAdaptor(name: 'Genre4')])]
    relatedArtistList = [new ArtistStubDeezerAdaptor(id: 3939), new ArtistStubDeezerAdaptor(id: 328)]
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
    def expected = [new AlbumStubDeezerAdaptor(id: 3), new AlbumStubDeezerAdaptor(id: 4)]

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
    def expected = [new ArtistStubDeezerAdaptor(id: 3), new AlbumStubDeezerAdaptor(id: 4)]

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
    def artists = [new ArtistStubDeezerAdaptor(id: 3), new ArtistStubDeezerAdaptor(id: 4)]

    def expectedGenres = [
        new GenreDeezerAdaptor(name: 'Genre4'),
        new GenreDeezerAdaptor(name: 'Genre2'),
        new GenreDeezerAdaptor(name: 'Genre1')
    ]

    def expected = [new ArtistStubDeezerAdaptor(id: 3, genreList: expectedGenres), new ArtistStubDeezerAdaptor(id: 4, genreList: [])]

    when:
    List<ArtistStub> results = artistService.search(query)

    then:
    1 * deezerSearchClient.searchArtists(query) >> artists
    1 * deezerArtistClient.getTopAlbums(3) >> topAlbumList
    results.equals(expected)

    where:
    query = 'Test Test'
    topAlbumList = [new AlbumStubDeezerAdaptor(id: 2, genres: [new GenreDeezerAdaptor(name: 'Genre1'), new GenreDeezerAdaptor(name: 'Genre2')]),
                    new AlbumStubDeezerAdaptor(id: 24, genres: [new GenreDeezerAdaptor(name: 'Genre2'), new GenreDeezerAdaptor(name: 'Genre3'), new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 25, genres: [new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 26, genres: [new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 27, genres: [new GenreDeezerAdaptor(name: 'Genre4')]),
                    new AlbumStubDeezerAdaptor(id: 28, genres: [new GenreDeezerAdaptor(name: 'Genre4')])]
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
