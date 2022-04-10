package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SongDeezerAdaptorSpec extends Specification {
  def 'Get album title'() {
    given:
    AlbumStubDeezerAdaptor album = new AlbumStubDeezerAdaptor(title: albumTitle)
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
    AlbumStubDeezerAdaptor album = new AlbumStubDeezerAdaptor()
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
    AlbumStubDeezerAdaptor album = new AlbumStubDeezerAdaptor(id: albumId)
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
    AlbumStubDeezerAdaptor album = new AlbumStubDeezerAdaptor()
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
    ArtistStubDeezerAdaptor artist = new ArtistStubDeezerAdaptor(id: artistId)
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
    ArtistStubDeezerAdaptor artist = new ArtistStubDeezerAdaptor()
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
    ArtistStubDeezerAdaptor artist = new ArtistStubDeezerAdaptor(name: artistName)
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
    ArtistStubDeezerAdaptor artist = new ArtistStubDeezerAdaptor()
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
