package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor
import spock.lang.Specification

class DeezerSongToSongStubAdaptorSpec extends Specification {

  def 'Get album art URI'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor(coverBig: albumArtUri)
    songStub.setAlbum(album)

    when:
    URI result = songStub.getAlbumArtUri()

    then:
    result == albumArtUri

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    albumArtUri = new URI('notreal.org')
  }

  def 'Get album art URI (null URI)'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor()
    songStub.setAlbum(album)

    when:
    URI result = songStub.getAlbumArtUri()

    then:
    result == null

    where:
    songStub = new DeezerSongToSongStubAdaptor()
  }

  def 'Get album art URI (null album)'() {
    when:
    URI result = songStub.getAlbumArtUri()

    then:
    result == null

    where:
    songStub = new DeezerSongToSongStubAdaptor()
  }

  def 'Get big album art URI'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor(coverXl: albumArtUri)
    songStub.setAlbum(album)

    when:
    URI result = songStub.getBigAlbumArtUri()

    then:
    result == albumArtUri

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    albumArtUri = new URI('notreal.org')
  }

  def 'Get big album art URI (null URI)'() {
    given:
    DeezerAlbumToAlbumStubAdaptor album = new DeezerAlbumToAlbumStubAdaptor()
    songStub.setAlbum(album)

    when:
    URI result = songStub.getBigAlbumArtUri()

    then:
    result == null

    where:
    songStub = new DeezerSongToSongStubAdaptor()
  }

  def 'Get big album art URI (null album)'() {
    when:
    URI result = songStub.getBigAlbumArtUri()

    then:
    result == null

    where:
    songStub = new DeezerSongToSongStubAdaptor()
  }

  def 'Get preview URI'() {
    given:
    songStub.setPreview(previewUri)

    when:
    URI result = songStub.getPreviewUri()

    then:
    result == previewUri

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    previewUri = new URI('okay.ok')
  }

  def 'Get preview URI (null)'() {
    given:
    songStub.setPreview()

    when:
    URI result = songStub.getPreviewUri()

    then:
    result == null

    where:
    songStub = new DeezerSongToSongStubAdaptor()
  }

}
