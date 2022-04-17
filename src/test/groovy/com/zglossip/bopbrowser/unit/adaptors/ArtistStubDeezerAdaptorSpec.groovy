package com.zglossip.bopbrowser.unit.adaptors


import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor
import spock.lang.Specification

class ArtistStubDeezerAdaptorSpec extends Specification {

  def 'Get pictureUri'() {
    given:
    artistStub.setPictureMedium(pictureUri)

    when:
    URI result = artistStub.getPictureUri()

    then:
    result == pictureUri

    where:
    artistStub = new ArtistStubDeezerAdaptor()
    pictureUri = new URI("thisisntarealURI.org.co.uk.gov.edu.com")
  }

  def 'Get pictureUri null'() {
    given:
    artistStub.setPicture(pictureUri)

    when:
    URI result = artistStub.getPictureUri()

    then:
    result == pictureUri

    where:
    artistStub = new ArtistStubDeezerAdaptor()
    pictureUri = null
  }

}
