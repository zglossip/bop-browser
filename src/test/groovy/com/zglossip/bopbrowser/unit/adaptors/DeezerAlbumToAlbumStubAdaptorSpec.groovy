package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.Genre
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerGenreToGenreAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import com.zglossip.bopbrowser.domains.models.deezer.DeezerGenreList
import spock.lang.Specification

class DeezerAlbumToAlbumStubAdaptorSpec extends Specification {

  def 'Get pictureUri'() {
    given:
    albumStub.setCoverBig(pictureUri)

    when:
    URI result = albumStub.getPictureUri()

    then:
    result == pictureUri

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
    pictureUri = new URI("thisisntarealURI.org.co.uk.gov.edu.com")
  }

  def 'Get pictureUri null'() {
    given:
    albumStub.setCoverBig(pictureUri)

    when:
    URI result = albumStub.getPictureUri()

    then:
    result == pictureUri

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
    pictureUri = null
  }

  def 'Get big pictureUri'() {
    given:
    albumStub.setCoverXl(pictureUri)

    when:
    URI result = albumStub.getBigPictureUri()

    then:
    result == pictureUri

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
    pictureUri = new URI("thisisntarealURI.org.co.uk.gov.edu.com")
  }

  def 'Get big pictureUri null'() {
    given:
    albumStub.setCoverXl(pictureUri)

    when:
    URI result = albumStub.getBigPictureUri()

    then:
    result == pictureUri

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
    pictureUri = null
  }

  def 'Get genres'() {
    given:
    albumStub.setGenres(new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(id: 0, name: genreName1), new DeezerGenreToGenreAdaptor(id: 1, name: genreName2)]))

    and:
    List<Genre> expected = []
    [genreName1, genreName2].eachWithIndex { name, id ->
      Genre genre = new DeezerGenreToGenreAdaptor()
      genre.setId(id)
      genre.setName(name)
      expected << genre
    }

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
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
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
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
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
  }

  def 'Get genres (null)'() {
    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == []

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
  }

  def 'Get downloaded genres'() {
    given:
    albumStub.setDownloadedGenres([new DeezerGenreToGenreAdaptor(id: 0, name: genreName1), new DeezerGenreToGenreAdaptor(id: 1, name: genreName2)])

    and:
    List<Genre> expected = []
    [genreName1, genreName2].eachWithIndex { name, id ->
      Genre genre = new DeezerGenreToGenreAdaptor()
      genre.setId(id)
      genre.setName(name)
      expected << genre
    }

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
    genreName1 = 'Test name 1'
    genreName2 = 'Test name 2'
  }

  def 'Get genres (with downloaded genres)'() {
    given:
    albumStub.setGenres(new DeezerGenreList(data: [new DeezerGenreToGenreAdaptor(id: 0, name: genreName1), new DeezerGenreToGenreAdaptor(id: 1, name: genreName2)]))
    albumStub.setDownloadedGenres([new DeezerGenreToGenreAdaptor(id: 1, name: 'bad genre'), new DeezerGenreToGenreAdaptor(id: 2, name: 'bad genre 2')])

    and:
    List<Genre> expected = []
    [genreName1, genreName2].eachWithIndex { name, id ->
      Genre genre = new DeezerGenreToGenreAdaptor()
      genre.setId(id)
      genre.setName(name)
      expected << genre
    }

    when:
    List<Genre> results = albumStub.getGenreList()

    then:
    results == expected

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
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
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
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
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
  }

  def 'Get artist ID (null artist)'() {
    when:
    Integer result = albumStub.getArtistId()

    then:
    result == null

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
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
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
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
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
  }

  def 'Get artist name (null artist)'() {
    when:
    String result = albumStub.getArtistName()

    then:
    result == null

    where:
    albumStub = new DeezerAlbumToAlbumStubAdaptor()
  }

}
