package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Artist;
import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.domains.SongStub;

import java.util.List;
import java.util.Objects;

public class ArtistDeezerAdaptor extends ArtistStubDeezerAdaptor implements Artist {
  private List<? extends SongStub> topSongList;
  private List<? extends AlbumStub> topAlbumList;
  private List<? extends ArtistStub> relatedArtistList;

  @Override
  public List<? extends SongStub> getTopSongList() {
    return topSongList;
  }

  @Override
  public void setTopSongList(final List<? extends SongStub> topSongList) {
    this.topSongList = topSongList;
  }

  @Override
  public List<? extends AlbumStub> getTopAlbumList() {
    return topAlbumList;
  }

  @Override
  public void setTopAlbumList(final List<? extends AlbumStub> topAlbumList) {
    this.topAlbumList = topAlbumList;
  }

  @Override
  public List<? extends ArtistStub> getRelatedArtistList() {
    return relatedArtistList;
  }

  @Override
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
           Objects.equals(relatedArtistList, that.relatedArtistList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), topSongList, topAlbumList, relatedArtistList);
  }
}
