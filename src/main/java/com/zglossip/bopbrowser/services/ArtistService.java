package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.categories.AlbumStub;
import com.zglossip.bopbrowser.domains.categories.Artist;
import com.zglossip.bopbrowser.domains.categories.ArtistStub;

import java.util.List;

public interface ArtistService extends AbstractService<ArtistStub> {

  Artist getArtistInfo(final int id, final int numberOfGenres, final int numberOfTracks, final int numberOfAlbums,
                       final int numberOfArtists);

  List<? extends AlbumStub> getArtistAlbums(final int id);

  List<? extends ArtistStub> getRelatedArtists(final int id);
}
