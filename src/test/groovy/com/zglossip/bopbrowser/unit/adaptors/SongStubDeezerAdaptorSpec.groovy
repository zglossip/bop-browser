package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongStubDeezerAdaptor
import spock.lang.Specification

class SongStubDeezerAdaptorSpec extends Specification {

  def 'Get album art URI'() {
    given:
    AlbumStubDeezerAdaptor album = new AlbumStubDeezerAdaptor(cover: albumArtUri)
    songStub.setAlbum(album)

    when:
    URI result = songStub.getAlbumArtUri()

    then:
    result == albumArtUri

    where:
    songStub = new SongStubDeezerAdaptor()
    albumArtUri = new URI('notreal.org')
  }

  def 'Get album art URI (null URI)'() {
    given:
    AlbumStubDeezerAdaptor album = new AlbumStubDeezerAdaptor()
    songStub.setAlbum(album)

    when:
    URI result = songStub.getAlbumArtUri()

    then:
    result == null

    where:
    songStub = new SongStubDeezerAdaptor()
  }

  def 'Get album art URI (null album)'() {
    when:
    URI result = songStub.getAlbumArtUri()

    then:
    result == null

    where:
    songStub = new SongStubDeezerAdaptor()
  }

  def 'Get preview URI'() {
    given:
    songStub.setPreview(previewUri)

    when:
    URI result = songStub.getPreviewUri()

    then:
    result == previewUri

    where:
    songStub = new SongStubDeezerAdaptor()
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
    songStub = new SongStubDeezerAdaptor()
  }

}
