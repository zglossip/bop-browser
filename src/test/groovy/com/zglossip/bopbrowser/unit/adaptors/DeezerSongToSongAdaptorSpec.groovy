package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
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
}
