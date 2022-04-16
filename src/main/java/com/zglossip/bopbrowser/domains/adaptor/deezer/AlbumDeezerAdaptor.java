package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.domains.SongStub;

import java.util.List;
import java.util.Objects;

public class AlbumDeezerAdaptor extends AlbumStubDeezerAdaptor implements Album {

  private List<? extends SongStub> songList;

  @Override
  public List<? extends SongStub> getSongList() {
    return songList;
  }

  public void setSongList(final List<? extends SongStub> songList) {
    this.songList = songList;
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
    final AlbumDeezerAdaptor that = (AlbumDeezerAdaptor) o;
    return Objects.equals(songList, that.songList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), songList);
  }
}
