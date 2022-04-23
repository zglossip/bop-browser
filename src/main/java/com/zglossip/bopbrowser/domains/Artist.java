package com.zglossip.bopbrowser.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.util.List;

@JsonSerialize(as = Artist.class)
public interface Artist extends ArtistStub {
  List<? extends Genre> getGenreList();

  void setGenreList(List<? extends Genre> genreList);

  List<? extends SongStub> getTopSongList();

  void setTopSongList(List<? extends SongStub> topSongList);

  List<? extends AlbumStub> getTopAlbumList();

  void setTopAlbumList(List<? extends AlbumStub> topAlbumList);

  List<? extends ArtistStub> getRelatedArtistList();

  void setRelatedArtistList(List<? extends ArtistStub> relatedArtistList);

  URI getTracklist();
}
