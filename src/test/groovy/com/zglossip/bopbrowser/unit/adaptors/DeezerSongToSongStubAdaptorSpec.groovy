package com.zglossip.bopbrowser.unit.adaptors

import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerAlbumToAlbumStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerArtistToArtistStubAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.DeezerSongToSongStubAdaptor
import com.zglossip.bopbrowser.domains.models.deezer.DeezerArtist
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

  def 'Get featured artists'() {
    given:
    songStub.setArtist(new DeezerArtistToArtistStubAdaptor(id: mainId))
    songStub.setContributors([new DeezerArtist(id: mainId), new DeezerArtist(id: featId)])

    and:
    def expected = [new DeezerArtistToArtistStubAdaptor(id: featId)]

    when:
    def result = songStub.getFeaturingList()

    then:
    result.equals(expected)

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    mainId = 100
    featId = 200
  }

  def 'Get featured artists (only main)'() {
    given:
    songStub.setArtist(new DeezerArtistToArtistStubAdaptor(id: mainId))
    songStub.setContributors([new DeezerArtist(id: mainId)])

    and:
    def expected = []

    when:
    def result = songStub.getFeaturingList()

    then:
    result.equals(expected)

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    mainId = 100
  }

  def 'Get featured artists (null artist)'() {
    given:
    songStub.setContributors([new DeezerArtist(id: mainId), new DeezerArtist(id: featId)])

    and:
    def expected = []

    when:
    def result = songStub.getFeaturingList()

    then:
    result.equals(expected)

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    mainId = 100
    featId = 200
  }

  def 'Get featured artists (empty contributors)'() {
    given:
    songStub.setArtist(new DeezerArtistToArtistStubAdaptor(id: mainId))
    songStub.setContributors([])

    and:
    def expected = []

    when:
    def result = songStub.getFeaturingList()

    then:
    result.equals(expected)

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    mainId = 100
    featId = 200
  }

  def 'Get featured artists (null contributors)'() {
    given:
    songStub.setArtist(new DeezerArtistToArtistStubAdaptor(id: mainId))

    and:
    def expected = []

    when:
    def result = songStub.getFeaturingList()

    then:
    result.equals(expected)

    where:
    songStub = new DeezerSongToSongStubAdaptor()
    mainId = 100
  }

  def 'Get featured artists (null both)'() {
    given:
    def expected = []

    when:
    def result = songStub.getFeaturingList()

    then:
    result.equals(expected)

    where:
    songStub = new DeezerSongToSongStubAdaptor()
  }

}
