package com.zglossip.bopbrowser.domains.models.deezer;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumDeezerAdaptor;

import java.net.URI;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerAlbum implements Comparable<DeezerAlbum> {
  private int id;
  private String title;
  private String upc;
  private URI link;
  private URI share;
  private URI cover;
  @JsonAlias("cover_small")
  private URI coverSmall;
  @JsonAlias("cover_medium")
  private URI coverMedium;
  @JsonAlias("cover_big")
  private URI coverBig;
  @JsonAlias("cover_xl")
  private URI coverXl;
  @JsonAlias("md5_image")
  private String md5Image;
  @JsonAlias("genre_id")
  private Integer genreId;
  private DeezerGenreList genres;
  private String label;
  @JsonAlias("nb_tracks")
  private Integer nbTracks;
  private Integer duration;
  private Integer fans;
  @JsonAlias("release_date")
  private Date releaseDate;
  @JsonAlias("record_type")
  private String recordType;
  private Boolean available;
  private AlbumDeezerAdaptor alternative;
  private URI tracklist;
  @JsonAlias("explicit_lyrics")
  private Boolean explicitLyrics;
  @JsonAlias("explicit_content_lyrics")
  private Boolean explicitContentLyrics;
  @JsonAlias("explicit_content_cover")
  private Boolean explicitContentCover;
  private DeezerArtistList contributers;
  private DeezerArtist artist;
  protected final Comparator<DeezerAlbum> comparator = Comparator.comparing(DeezerAlbum::getId, Comparator.naturalOrder())
                                                                 .thenComparing(DeezerAlbum::getTitle,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getUpc,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getLink,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getShare,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getCover,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getCoverSmall,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getCoverMedium,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getCoverBig,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getCoverXl,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getMd5Image,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getGenreId,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getLabel,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getNbTracks,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getDuration,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getFans,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getReleaseDate,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getRecordType,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::isAvailable,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getAlternative,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getTracklist,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::isExplicitLyrics,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::isExplicitContentLyrics,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::isExplicitContentCover,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()))
                                                                 .thenComparing(DeezerAlbum::getArtist,
                                                                                Comparator.nullsLast(Comparator.naturalOrder()));
  private DeezerSongList tracks;

  protected DeezerAlbum(final DeezerAlbum deezerAlbum) {
    id = deezerAlbum.getId();
    title = deezerAlbum.getTitle();
    upc = deezerAlbum.getUpc();
    link = deezerAlbum.getLink();
    share = deezerAlbum.getShare();
    cover = deezerAlbum.getCover();
    coverSmall = deezerAlbum.getCoverSmall();
    coverMedium = deezerAlbum.getCoverMedium();
    coverBig = deezerAlbum.getCoverBig();
    coverXl = deezerAlbum.getCoverXl();
    md5Image = deezerAlbum.getMd5Image();
    genreId = deezerAlbum.getGenreId();
    genres = deezerAlbum.getGenres();
    label = deezerAlbum.getLabel();
    nbTracks = deezerAlbum.getNbTracks();
    duration = deezerAlbum.getDuration();
    fans = deezerAlbum.getFans();
    releaseDate = deezerAlbum.getReleaseDate();
    recordType = deezerAlbum.getRecordType();
    available = deezerAlbum.isAvailable();
    alternative = deezerAlbum.getAlternative();
    tracklist = deezerAlbum.getTracklist();
    explicitLyrics = deezerAlbum.isExplicitLyrics();
    explicitContentLyrics = deezerAlbum.isExplicitContentLyrics();
    explicitContentCover = deezerAlbum.isExplicitContentCover();
    contributers = deezerAlbum.getContributers();
    artist = deezerAlbum.getArtist();
    tracks = deezerAlbum.getTracks();
  }

  public DeezerAlbum() {

  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getUpc() {
    return upc;
  }

  public void setUpc(final String upc) {
    this.upc = upc;
  }

  public URI getLink() {
    return link;
  }

  public void setLink(final URI link) {
    this.link = link;
  }

  public URI getShare() {
    return share;
  }

  public void setShare(final URI share) {
    this.share = share;
  }

  public URI getCover() {
    return cover;
  }

  public void setCover(final URI cover) {
    this.cover = cover;
  }

  public URI getCoverSmall() {
    return coverSmall;
  }

  public void setCoverSmall(final URI coverSmall) {
    this.coverSmall = coverSmall;
  }

  public URI getCoverMedium() {
    return coverMedium;
  }

  public void setCoverMedium(final URI coverMedium) {
    this.coverMedium = coverMedium;
  }

  public URI getCoverBig() {
    return coverBig;
  }

  public void setCoverBig(final URI coverBig) {
    this.coverBig = coverBig;
  }

  public URI getCoverXl() {
    return coverXl;
  }

  public void setCoverXl(final URI coverXl) {
    this.coverXl = coverXl;
  }

  public String getMd5Image() {
    return md5Image;
  }

  public void setMd5Image(final String md5Image) {
    this.md5Image = md5Image;
  }

  public Integer getGenreId() {
    return genreId;
  }

  public void setGenreId(final Integer genreId) {
    this.genreId = genreId;
  }

  public DeezerGenreList getGenres() {
    return genres;
  }

  public void setGenres(final DeezerGenreList genres) {
    this.genres = genres;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public Integer getNbTracks() {
    return nbTracks;
  }

  public void setNbTracks(final Integer nbTracks) {
    this.nbTracks = nbTracks;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(final Integer duration) {
    this.duration = duration;
  }

  public Integer getFans() {
    return fans;
  }

  public void setFans(final Integer fans) {
    this.fans = fans;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(final Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getRecordType() {
    return recordType;
  }

  public void setRecordType(final String recordType) {
    this.recordType = recordType;
  }

  public Boolean isAvailable() {
    return available;
  }

  public void setAvailable(final Boolean available) {
    this.available = available;
  }

  public AlbumDeezerAdaptor getAlternative() {
    return alternative;
  }

  public void setAlternative(final AlbumDeezerAdaptor alternative) {
    this.alternative = alternative;
  }

  public URI getTracklist() {
    return tracklist;
  }

  public void setTracklist(final URI tracklist) {
    this.tracklist = tracklist;
  }

  public Boolean isExplicitLyrics() {
    return explicitLyrics;
  }

  public void setExplicitLyrics(final Boolean explicitLyrics) {
    this.explicitLyrics = explicitLyrics;
  }

  public Boolean isExplicitContentLyrics() {
    return explicitContentLyrics;
  }

  public void setExplicitContentLyrics(final Boolean explicitContentLyrics) {
    this.explicitContentLyrics = explicitContentLyrics;
  }

  public Boolean isExplicitContentCover() {
    return explicitContentCover;
  }

  public void setExplicitContentCover(final Boolean explicitContentCover) {
    this.explicitContentCover = explicitContentCover;
  }

  public DeezerArtistList getContributers() {
    return contributers;
  }

  public void setContributers(final DeezerArtistList contributers) {
    this.contributers = contributers;
  }

  public DeezerArtist getArtist() {
    return artist;
  }

  public void setArtist(final DeezerArtist artist) {
    this.artist = artist;
  }

  public DeezerSongList getTracks() {
    return tracks;
  }

  public void setTracks(final DeezerSongList tracks) {
    this.tracks = tracks;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final DeezerAlbum that = (DeezerAlbum) o;
    return id == that.id && Objects.equals(title, that.title) && Objects.equals(upc, that.upc) &&
           Objects.equals(link, that.link) && Objects.equals(share, that.share) &&
           Objects.equals(cover, that.cover) && Objects.equals(coverSmall, that.coverSmall) &&
           Objects.equals(coverMedium, that.coverMedium) && Objects.equals(coverBig, that.coverBig) &&
           Objects.equals(coverXl, that.coverXl) && Objects.equals(md5Image, that.md5Image) &&
           Objects.equals(genreId, that.genreId) && Objects.equals(genres, that.genres) &&
           Objects.equals(label, that.label) && Objects.equals(nbTracks, that.nbTracks) &&
           Objects.equals(duration, that.duration) && Objects.equals(fans, that.fans) &&
           Objects.equals(releaseDate, that.releaseDate) && Objects.equals(recordType, that.recordType) &&
           Objects.equals(available, that.available) && Objects.equals(alternative, that.alternative) &&
           Objects.equals(tracklist, that.tracklist) && Objects.equals(explicitLyrics, that.explicitLyrics) &&
           Objects.equals(explicitContentLyrics, that.explicitContentLyrics) &&
           Objects.equals(explicitContentCover, that.explicitContentCover) &&
           Objects.equals(contributers, that.contributers) && Objects.equals(artist, that.artist) &&
           Objects.equals(tracks, that.tracks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, upc, link, share, cover, coverSmall, coverMedium, coverBig, coverXl, md5Image, genreId, genres, label,
                        nbTracks, duration, fans, releaseDate, recordType, available, alternative, tracklist, explicitLyrics,
                        explicitContentLyrics, explicitContentCover, contributers, artist, tracks);
  }

  @Override
  public String toString() {
    return "DeezerAlbum{" +
           "id=" + id +
           ", title='" + title + '\'' +
           ", upc='" + upc + '\'' +
           ", link=" + link +
           ", share=" + share +
           ", cover=" + cover +
           ", coverSmall=" + coverSmall +
           ", coverMedium=" + coverMedium +
           ", coverBig=" + coverBig +
           ", coverXl=" + coverXl +
           ", md5Image='" + md5Image + '\'' +
           ", genreId=" + genreId +
           ", genres=" + genres +
           ", label='" + label + '\'' +
           ", nbTracks=" + nbTracks +
           ", duration=" + duration +
           ", fans=" + fans +
           ", releaseDate=" + releaseDate +
           ", recordType='" + recordType + '\'' +
           ", available=" + available +
           ", alternative=" + alternative +
           ", tracklist=" + tracklist +
           ", explicitLyrics=" + explicitLyrics +
           ", explicitContentLyrics=" + explicitContentLyrics +
           ", explicitContentCover=" + explicitContentCover +
           ", contributers=" + contributers +
           ", artist=" + artist +
           ", tracks=" + tracks +
           '}';
  }

  @Override
  public int compareTo(final DeezerAlbum o) {
    return comparator.compare(this, o);
  }
}
