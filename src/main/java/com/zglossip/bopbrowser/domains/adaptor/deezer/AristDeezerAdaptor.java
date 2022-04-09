package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.AlbumStub;
import com.zglossip.bopbrowser.domains.Artist;
import com.zglossip.bopbrowser.domains.SongStub;

import java.util.List;

public class AristDeezerAdaptor extends ArtistStubDeezerAdaptor implements Artist {
  private List<SongStub> topSongList;
  private List<AlbumStub> topAlbumList;

  @Override
  public List<SongStub> getTopSongList() {
    return topSongList;
  }

  public void setTopSongList(final List<SongStub> topSongList) {
    this.topSongList = topSongList;
  }

  @Override
  public List<AlbumStub> getTopAlbumList() {
    return topAlbumList;
  }

  public void setTopAlbumList(final List<AlbumStub> topAlbumList) {
    this.topAlbumList = topAlbumList;
  }
}
