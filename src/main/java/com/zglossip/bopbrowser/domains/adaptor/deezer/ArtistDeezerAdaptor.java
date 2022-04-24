package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.*;

import java.util.List;
import java.util.Objects;

public class ArtistDeezerAdaptor extends ArtistStubDeezerAdaptor implements Artist {
  private List<? extends SongStub> topSongList;
  private List<? extends AlbumStub> topAlbumList;
  private List<? extends ArtistStub> relatedArtistList;
  private List<? extends Genre> genreList;

  @Override
  public List<? extends Genre> getGenreList() {
    return genreList;
  }

  public void setGenreList(final List<? extends Genre> genreList) {
    this.genreList = genreList;
  }

  @Override
  public List<? extends SongStub> getTopSongList() {
    return topSongList;
  }

  public void setTopSongList(final List<? extends SongStub> topSongList) {
    this.topSongList = topSongList;
  }

  @Override
  public List<? extends AlbumStub> getTopAlbumList() {
    return topAlbumList;
  }

  public void setTopAlbumList(final List<? extends AlbumStub> topAlbumList) {
    this.topAlbumList = topAlbumList;
  }

  @Override
  public List<? extends ArtistStub> getRelatedArtistList() {
    return relatedArtistList;
  }

  public void setRelatedArtistList(final List<? extends ArtistStub> relatedArtistList) {
    this.relatedArtistList = relatedArtistList;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    final ArtistDeezerAdaptor that = (ArtistDeezerAdaptor) o;
    return Objects.equals(topSongList, that.topSongList) && Objects.equals(topAlbumList, that.topAlbumList) &&
           Objects.equals(relatedArtistList, that.relatedArtistList) && Objects.equals(genreList, that.genreList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), topSongList, topAlbumList, relatedArtistList);
  }
}
