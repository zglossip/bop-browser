package com.zglossip.bopbrowser.domains.categories;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = Artist.class)
public interface Artist extends ArtistStub {
  List<? extends Genre> getGenreList();

  List<? extends SongStub> getTopSongList();

  List<? extends AlbumStub> getTopAlbumList();

  List<? extends ArtistStub> getRelatedArtistList();
}
