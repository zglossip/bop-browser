package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.Genre
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.GenreDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenreList
import spock.lang.Specification

class AlbumStubDeezerAdaptorSpec extends Specification {

  def 'Get pictureUri'() {
    given:
    albumStub.setCoverMedium(pictureUri)

    when:
    URI result = albumStub.getPictureUri()

    then:
    result == pictureUri

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    pictureUri = new URI("thisisntarealURI.org.co.uk.gov.edu.com")
  }

  def 'Get pictureUri null'() {
    given:
    albumStub.setCover(pictureUri)

    when:
    URI result = albumStub.getPictureUri()

    then:
    result == pictureUri

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    pictureUri = null
  }

  def 'Get genres'() {
    given:
    albumStub.setGenres(new DeezerGenreList(data: [new GenreDeezerAdaptor(id: 0, name: genreName1), new GenreDeezerAdaptor(id: 1, name: genreName2)]))

    and:
    List<Genre> expected = []
    [genreName1, genreName2].eachWithIndex { name, id ->
      Genre genre = new GenreDeezerAdaptor()
      genre.setId(id)
      genre.setName(name)
      expected << genre
    }

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    genreName1 = 'Test name 1'
    genreName2 = 'Test name 2'
  }

  def 'Get genres (empty)'() {
    given:
    albumStub.setGenres(new DeezerGenreList(data: []))

    and:
    List<Genre> expected = []

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

  def 'Get genres (null data)'() {
    given:
    albumStub.setGenres(new DeezerGenreList(data: null))

    and:
    List<Genre> expected = []

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

  def 'Get genres (null)'() {
    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == []

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

  def 'Get downloaded genres'() {
    given:
    albumStub.setDownloadedGenres([new GenreDeezerAdaptor(id: 0, name: genreName1), new GenreDeezerAdaptor(id: 1, name: genreName2)])

    and:
    List<Genre> expected = []
    [genreName1, genreName2].eachWithIndex { name, id ->
      Genre genre = new GenreDeezerAdaptor()
      genre.setId(id)
      genre.setName(name)
      expected << genre
    }

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    genreName1 = 'Test name 1'
    genreName2 = 'Test name 2'
  }

  def 'Get genres (with downloaded genres)'() {
    given:
    albumStub.setGenres(new DeezerGenreList(data: [new GenreDeezerAdaptor(id: 0, name: genreName1), new GenreDeezerAdaptor(id: 1, name: genreName2)]))
    albumStub.setDownloadedGenres([new GenreDeezerAdaptor(id: 1, name: 'bad genre'), new GenreDeezerAdaptor(id: 2, name: 'bad genre 2')])

    and:
    List<Genre> expected = []
    [genreName1, genreName2].eachWithIndex { name, id ->
      Genre genre = new GenreDeezerAdaptor()
      genre.setId(id)
      genre.setName(name)
      expected << genre
    }

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    genreName1 = 'Test name 1'
    genreName2 = 'Test name 2'
  }

  def 'Get artist ID'() {
    given:
    DeezerArtist artist = new DeezerArtist(id: artistId)
    albumStub.setArtist(artist)

    when:
    Integer result = albumStub.getArtistId()

    then:
    result == artistId

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    artistId = 100
  }

  def 'Get artist ID (null ID)'() {
    given:
    DeezerArtist artist = new DeezerArtist()
    albumStub.setArtist(artist)

    when:
    Integer result = albumStub.getArtistId()

    then:
    result == null

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

  def 'Get artist ID (null artist)'() {
    when:
    Integer result = albumStub.getArtistId()

    then:
    result == null

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

  def 'Get artist name'() {
    given:
    DeezerArtist artist = new DeezerArtist(name: artistName)
    albumStub.setArtist(artist)

    when:
    String result = albumStub.getArtistName()

    then:
    result == artistName

    where:
    albumStub = new AlbumStubDeezerAdaptor()
    artistName = 'Test McTesty'
  }

  def 'Get artist name (null name)'() {
    given:
    DeezerArtist artist = new DeezerArtist()
    albumStub.setArtist(artist)

    when:
    String result = albumStub.getArtistName()

    then:
    result == null

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

  def 'Get artist name (null artist)'() {
    when:
    String result = albumStub.getArtistName()

    then:
    result == null

    where:
    albumStub = new AlbumStubDeezerAdaptor()
  }

}
