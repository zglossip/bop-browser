package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.Album;
import com.zglossip.bopbrowser.domains.SongStub;

import java.util.List;

public class AlbumDeezerAdaptor extends AlbumStubDeezerAdaptor implements Album {

  private List<SongStub> songList;
  
  @Override
  public List<SongStub> getSongList() {
    return songList;
  }

  public void setSongList(final List<SongStub> songList) {
    this.songList = songList;
  }
}
