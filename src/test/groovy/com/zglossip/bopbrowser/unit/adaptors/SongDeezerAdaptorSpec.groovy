package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbum
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SongDeezerAdaptorSpec extends Specification {
  def 'Get album title'() {
    given:
    DeezerAlbum album = new DeezerAlbum(title: albumTitle)
    song.setAlbum(album)

    when:
    String result = song.getAlbumTitle()

    then:
    result == albumTitle

    where:
    song = new SongDeezerAdaptor()
    albumTitle = 'Test title'
  }

  def 'Get album title (null title)'() {
    given:
    DeezerAlbum album = new DeezerAlbum()
    song.setAlbum(album)

    when:
    String result = song.getAlbumTitle()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get album title (null album)'() {
    when:
    String result = song.getAlbumTitle()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get album ID'() {
    given:
    DeezerAlbum album = new DeezerAlbum(id: albumId)
    song.setAlbum(album)

    when:
    Integer result = song.getAlbumId()

    then:
    result == albumId

    where:
    song = new SongDeezerAdaptor()
    albumId = 123
  }

  def 'Get album ID (null ID)'() {
    given:
    DeezerAlbum album = new DeezerAlbum()
    song.setAlbum(album)

    when:
    Integer result = song.getAlbumId()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get album ID (null album)'() {
    when:
    Integer result = song.getAlbumId()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get artist ID'() {
    given:
    DeezerArtist artist = new DeezerArtist(id: artistId)
    song.setArtist(artist)

    when:
    Integer result = song.getArtistId()

    then:
    result == artistId

    where:
    song = new SongDeezerAdaptor()
    artistId = 1234
  }

  def 'Get artist ID (null ID)'() {
    given:
    DeezerArtist artist = new DeezerArtist()
    song.setArtist(artist)

    when:
    Integer result = song.getArtistId()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get artist ID (null artist)'() {
    when:
    Integer result = song.getArtistId()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get artist name'() {
    given:
    DeezerArtist artist = new DeezerArtist(name: artistName)
    song.setArtist(artist)

    when:
    String result = song.getArtistName()

    then:
    result == artistName

    where:
    song = new SongDeezerAdaptor()
    artistName = 'Test name'
  }

  def 'Get artist name (null name)'() {
    given:
    DeezerArtist artist = new DeezerArtist()
    song.setArtist(artist)

    when:
    String result = song.getArtistName()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get artist name (null artist)'() {
    when:
    String result = song.getArtistName()

    then:
    result == null

    where:
    song = new SongDeezerAdaptor()
  }

  def 'Get album genres'() {

  }
}
