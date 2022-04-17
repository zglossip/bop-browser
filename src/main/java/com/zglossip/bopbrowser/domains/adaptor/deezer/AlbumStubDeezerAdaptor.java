package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Genre;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerAlbum;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlbumStubDeezerAdaptor extends DeezerAlbum implements AlbumStub {

  private List<? extends Genre> downloadedGenres;

  protected AlbumStubDeezerAdaptor(final DeezerAlbum deezerAlbum) {
    super(deezerAlbum);
  }

  public AlbumStubDeezerAdaptor() {
    super();
  }

  public static AlbumStubDeezerAdaptor clone(final DeezerAlbum deezerAlbum) {
    return new AlbumStubDeezerAdaptor(deezerAlbum);
  }

  @Override
  public URI getPictureUri() {
    return getCoverMedium();
  }

  @Override
  public List<? extends Genre> getGenreList() {
    if (Objects.isNull(getGenres()) || Objects.isNull(getGenres().getData())) {
      return getDownloadedGenres();
    }

    return getGenres().getData().stream().map(GenreDeezerAdaptor::new).collect(Collectors.toList());
  }

  private List<? extends Genre> getDownloadedGenres() {
    if (Objects.isNull(downloadedGenres)) {
      return Collections.emptyList();
    }

    return downloadedGenres;
  }

  public void setDownloadedGenres(final List<? extends Genre> downloadedGenres) {
    this.downloadedGenres = downloadedGenres;
  }

  @Override
  public Integer getArtistId() {
    if (Objects.isNull(getArtist()) || getArtist().getId() == 0) {
      return null;
    }
    return getArtist().getId();
  }

  @Override
  public String getArtistName() {
    if (Objects.isNull(getArtist())) {
      return null;
    }

    return getArtist().getName();
  }
}
