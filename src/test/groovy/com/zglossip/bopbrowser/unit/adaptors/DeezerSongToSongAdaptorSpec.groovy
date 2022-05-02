package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongAdaptor
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DeezerSongToSongAdaptorSpec extends Specification {
  def 'Get album title'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor(title: albumTitle)
    song.setAlbum(album)

    when:
    String result = song.getAlbumTitle()

    then:
    result == albumTitle

    where:
    song = new DeezerSongToSongAdaptor()
    albumTitle = 'Test title'
  }

  def 'Get album title (null title)'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor()
    song.setAlbum(album)

    when:
    String result = song.getAlbumTitle()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get album title (null album)'() {
    when:
    String result = song.getAlbumTitle()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get album ID'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor(id: albumId)
    song.setAlbum(album)

    when:
    Integer result = song.getAlbumId()

    then:
    result == albumId

    where:
    song = new DeezerSongToSongAdaptor()
    albumId = 123
  }

  def 'Get album ID (null ID)'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor()
    song.setAlbum(album)

    when:
    Integer result = song.getAlbumId()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get album ID (null album)'() {
    when:
    Integer result = song.getAlbumId()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get artist ID'() {
    given:
    DeezerArtistToArtistStubAdaptor artist = new DeezerArtistToArtistStubAdaptor(id: artistId)
    song.setArtist(artist)

    when:
    Integer result = song.getArtistId()

    then:
    result == artistId

    where:
    song = new DeezerSongToSongAdaptor()
    artistId = 1234
  }

  def 'Get artist ID (null ID)'() {
    given:
    DeezerArtistToArtistStubAdaptor artist = new DeezerArtistToArtistStubAdaptor()
    song.setArtist(artist)

    when:
    Integer result = song.getArtistId()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get artist ID (null artist)'() {
    when:
    Integer result = song.getArtistId()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get artist name'() {
    given:
    DeezerArtistToArtistStubAdaptor artist = new DeezerArtistToArtistStubAdaptor(name: artistName)
    song.setArtist(artist)

    when:
    String result = song.getArtistName()

    then:
    result == artistName

    where:
    song = new DeezerSongToSongAdaptor()
    artistName = 'Test name'
  }

  def 'Get artist name (null name)'() {
    given:
    DeezerArtistToArtistStubAdaptor artist = new DeezerArtistToArtistStubAdaptor()
    song.setArtist(artist)

    when:
    String result = song.getArtistName()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get artist name (null artist)'() {
    when:
    String result = song.getArtistName()

    then:
    result == null

    where:
    song = new DeezerSongToSongAdaptor()
  }

  def 'Get album genres'() {

  }
}
