package com.zglossip.bopbrowser.domains.adaptor.deezer;

import com.zglossip.bopbrowser.domains.ArtistStub;
import com.zglossip.bopbrowser.domains.SongStub;
import com.zglossip.bopbrowser.domains.models.deezer.DeezerSong;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeezerSongToSongStubAdaptor extends DeezerSong implements SongStub {

  protected DeezerSongToSongStubAdaptor(final DeezerSong deezerSong) {
    super(deezerSong);
  }

  public DeezerSongToSongStubAdaptor() {
    super();
  }

  public static DeezerSongToSongStubAdaptor clone(final DeezerSong deezerSong) {
    return new DeezerSongToSongStubAdaptor(deezerSong);
  }

  @Override
  public URI getAlbumArtUri() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getCoverBig();
  }

  @Override
  public URI getBigAlbumArtUri() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getCoverXl();
  }

  @Override
  public Integer getAlbumId() {
    if (Objects.isNull(getAlbum())) {
      return null;
    }

    return getAlbum().getId();
  }

  @Override
  public URI getPreviewUri() {
    return getPreview();
  }

  @Override
  public List<? extends ArtistStub> getFeaturingList() {
    if (Objects.isNull(getContributors())) {
      return Collections.emptyList();
    }

    return getContributors().stream().map(deezerArtist -> {
      if (Objects.isNull(getArtist()) || getArtist().getId() == 0) {
        return null;
      }

      if (Objects.equals(getArtist().getId(), deezerArtist.getId())) {
        return null;
      }
      return DeezerArtistToArtistStubAdaptor.clone(deezerArtist);
    }).filter(Objects::nonNull).collect(Collectors.toList());
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
