package com.zglossip.bopbrowser.unit.services


import com.zglossip.bopbrowser.clients.DeezerGenreClient
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor
import com.zglossip.bopbrowser.domains.adaptor.deezer.GenreDeezerAdaptor
import com.zglossip.bopbrowser.services.GenreService
import spock.lang.Specification
import spock.lang.Subject

class GenreServiceSpec extends Specification {

  @Subject
  GenreService genreService

  DeezerGenreClient deezerGenreClient

  def setup() {
    deezerGenreClient = Mock(DeezerGenreClient)
    genreService = new GenreService(deezerGenreClient)
  }

  def 'Populate album genre'() {
    when:
    genreService.populateAlbumStubGenre(albumListInput)

    then:
    1 * deezerGenreClient.getGenre(genreId1) >> genre1
    1 * deezerGenreClient.getGenre(genreId2) >> genre2
    albumListInput.each({ album ->
      assert album.getGenreList() == expectedGenreOutput.get(album.getId())
    })

    where:
    genreId1 = 10
    genreId2 = 20
    genre1 = new GenreDeezerAdaptor(id: genreId1)
    genre2 = new GenreDeezerAdaptor(id: genreId2)
    albumId1 = 1
    albumId2 = 2
    albumId3 = 3
    albumListInput = [new AlbumStubDeezerAdaptor(id: albumId1, genreId: genreId1),
                      new AlbumStubDeezerAdaptor(id: albumId2, genreId: genreId2),
                      new AlbumStubDeezerAdaptor(id: albumId3, genreId: genreId2)]
    expectedGenreOutput = [
        (albumId1): [genre1],
        (albumId2): [genre2],
        (albumId3): [genre2]
    ]
  }

  def 'Populate album genre (null genre)'() {
    when:
    genreService.populateAlbumStubGenre(albumListInput)

    then:
    1 * deezerGenreClient.getGenre(genreId2) >> genre2
    albumListInput.each({ album ->
      assert album.getGenreList() == expectedGenreOutput.get(album.getId())
    })

    where:
    genreId1 = 10
    genreId2 = 20
    genre2 = new GenreDeezerAdaptor(id: genreId2)
    albumId1 = 1
    albumId2 = 2
    albumId3 = 3
    albumListInput = [new AlbumStubDeezerAdaptor(id: albumId1, genreId: genreId1),
                      new AlbumStubDeezerAdaptor(id: albumId2, genreId: genreId2),
                      new AlbumStubDeezerAdaptor(id: albumId3, genreId: genreId2)]
    expectedGenreOutput = [
        (albumId1): [],
        (albumId2): [genre2],
        (albumId3): [genre2]
    ]
  }

  def 'Populate album genre (null genre ID)'() {
    when:
    genreService.populateAlbumStubGenre(albumListInput)

    then:
    1 * deezerGenreClient.getGenre(genreId2) >> genre2
    albumListInput.each({ album ->
      assert album.getGenreList() == expectedGenreOutput.get(album.getId())
    })

    where:
    genreId1 = null
    genreId2 = 20
    genre2 = new GenreDeezerAdaptor(id: genreId2)
    albumId1 = 1
    albumId2 = 2
    albumId3 = 3
    albumListInput = [new AlbumStubDeezerAdaptor(id: albumId1, genreId: genreId1),
                      new AlbumStubDeezerAdaptor(id: albumId2, genreId: genreId2),
                      new AlbumStubDeezerAdaptor(id: albumId3, genreId: genreId2)]
    expectedGenreOutput = [
        (albumId1): [],
        (albumId2): [genre2],
        (albumId3): [genre2]
    ]
  }
}
