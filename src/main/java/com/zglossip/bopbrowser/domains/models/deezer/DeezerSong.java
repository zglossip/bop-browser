package com.zglossip.bopbrowser.domains.models.deezer;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zglossip.bopbrowser.domains.adaptor.deezer.AlbumStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.ArtistStubDeezerAdaptor;
import com.zglossip.bopbrowser.domains.adaptor.deezer.SongDeezerAdaptor;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class DeezerSong implements Comparable<DeezerSong> {
  private static final Comparator<DeezerSong> comparator = Comparator.comparing(DeezerSong::getId)
                                                                     .thenComparing(DeezerSong::isReadable,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getTitle,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getTitleVersion,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::isUnseen,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getIsrc,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getLink,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getShare,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getDuration,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getTrackPosition,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getDiskNumber,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getRank,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getReleaseDate,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::isExplicitLyrics,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getExplicitContentLyrics,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getExplicitContentCover,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getPreview,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getBpm,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getGain,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getAlternative,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getMd5Image,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getArtist,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()))
                                                                     .thenComparing(DeezerSong::getAlbum,
                                                                                    Comparator.nullsLast(Comparator.naturalOrder()));
  private int id;
  private Boolean readable;
  private String title;
  @JsonAlias("title_version")
  private String titleVersion;
  private Boolean unseen;
  private String isrc;
  private URI link;
  private URI share;
  private Integer duration;
  @JsonAlias("track_position")
  private Integer trackPosition;
  @JsonAlias("disk_number")
  private Integer diskNumber;
  private Integer rank;
  @JsonAlias("release_date")
  private LocalDate releaseDate;
  @JsonAlias("explicit_lyrics")
  private Boolean explicitLyrics;
  @JsonAlias("explicit_content_lyrics")
  private Integer explicitContentLyrics;
  @JsonAlias("explicit_content_cover")
  private Integer explicitContentCover;
  private URI preview;
  private BigDecimal bpm;
  private BigDecimal gain;
  @JsonAlias("available_countries")
  private Collection<String> availableCountries;
  private SongDeezerAdaptor alternative;
  private DeezerArtistList contributers;
  @JsonAlias("md5_image")
  private String md5Image;
  private ArtistStubDeezerAdaptor artist;
  private AlbumStubDeezerAdaptor album;

  public DeezerSong() {

  }

  protected DeezerSong(final DeezerSong deezerSong) {
    id = deezerSong.getId();
    title = deezerSong.getTitle();
    titleVersion = deezerSong.getTitleVersion();
    unseen = deezerSong.isUnseen();
    isrc = deezerSong.getIsrc();
    link = deezerSong.getLink();
    share = deezerSong.getShare();
    duration = deezerSong.getDuration();
    trackPosition = deezerSong.getTrackPosition();
    diskNumber = deezerSong.getDiskNumber();
    rank = deezerSong.getRank();
    releaseDate = deezerSong.getReleaseDate();
    explicitLyrics = deezerSong.isExplicitLyrics();
    explicitContentLyrics = deezerSong.getExplicitContentLyrics();
    explicitContentCover = deezerSong.getExplicitContentCover();
    preview = deezerSong.getPreview();
    bpm = deezerSong.getBpm();
    gain = deezerSong.getGain();
    availableCountries = deezerSong.getAvailableCountries();
    alternative = deezerSong.getAlternative();
    contributers = deezerSong.getContributers();
    md5Image = deezerSong.getMd5Image();
    artist = deezerSong.getArtist();
    album = deezerSong.getAlbum();
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public Boolean isReadable() {
    return readable;
  }

  public void setReadable(final Boolean readable) {
    this.readable = readable;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getTitleVersion() {
    return titleVersion;
  }

  public void setTitleVersion(final String titleVersion) {
    this.titleVersion = titleVersion;
  }

  public Boolean isUnseen() {
    return unseen;
  }

  public void setUnseen(final Boolean unseen) {
    this.unseen = unseen;
  }

  public String getIsrc() {
    return isrc;
  }

  public void setIsrc(final String isrc) {
    this.isrc = isrc;
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

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(final Integer duration) {
    this.duration = duration;
  }

  public Integer getTrackPosition() {
    return trackPosition;
  }

  public void setTrackPosition(final Integer trackPosition) {
    this.trackPosition = trackPosition;
  }

  public Integer getDiskNumber() {
    return diskNumber;
  }

  public void setDiskNumber(final Integer diskNumber) {
    this.diskNumber = diskNumber;
  }

  public Integer getRank() {
    return rank;
  }

  public void setRank(final Integer rank) {
    this.rank = rank;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(final LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Boolean isExplicitLyrics() {
    return explicitLyrics;
  }

  public void setExplicitLyrics(final Boolean explicitLyrics) {
    this.explicitLyrics = explicitLyrics;
  }

  public Integer getExplicitContentLyrics() {
    return explicitContentLyrics;
  }

  public void setExplicitContentLyrics(final Integer explicitContentLyrics) {
    this.explicitContentLyrics = explicitContentLyrics;
  }

  public Integer getExplicitContentCover() {
    return explicitContentCover;
  }

  public void setExplicitContentCover(final Integer explicitContentCover) {
    this.explicitContentCover = explicitContentCover;
  }

  public URI getPreview() {
    return preview;
  }

  public void setPreview(final URI preview) {
    this.preview = preview;
  }

  public BigDecimal getBpm() {
    return bpm;
  }

  public void setBpm(final BigDecimal bpm) {
    this.bpm = bpm;
  }

  public BigDecimal getGain() {
    return gain;
  }

  public void setGain(final BigDecimal gain) {
    this.gain = gain;
  }

  public Collection<String> getAvailableCountries() {
    return availableCountries;
  }

  public void setAvailableCountries(final Collection<String> availableCountries) {
    this.availableCountries = availableCountries;
  }

  public SongDeezerAdaptor getAlternative() {
    return alternative;
  }

  public void setAlternative(final SongDeezerAdaptor alternative) {
    this.alternative = alternative;
  }

  public DeezerArtistList getContributers() {
    return contributers;
  }

  public void setContributers(final DeezerArtistList contributers) {
    this.contributers = contributers;
  }

  public String getMd5Image() {
    return md5Image;
  }

  public void setMd5Image(final String md5Image) {
    this.md5Image = md5Image;
  }

  public ArtistStubDeezerAdaptor getArtist() {
    return artist;
  }

  public void setArtist(final ArtistStubDeezerAdaptor artist) {
    this.artist = artist;
  }

  public AlbumStubDeezerAdaptor getAlbum() {
    return album;
  }

  public void setAlbum(final AlbumStubDeezerAdaptor album) {
    this.album = album;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final DeezerSong that = (DeezerSong) o;
    return id == that.id && Objects.equals(readable, that.readable) && Objects.equals(title, that.title) &&
           Objects.equals(titleVersion, that.titleVersion) && Objects.equals(unseen, that.unseen) &&
           Objects.equals(isrc, that.isrc) && Objects.equals(link, that.link) &&
           Objects.equals(share, that.share) && Objects.equals(duration, that.duration) &&
           Objects.equals(trackPosition, that.trackPosition) && Objects.equals(diskNumber, that.diskNumber) &&
           Objects.equals(rank, that.rank) && Objects.equals(releaseDate, that.releaseDate) &&
           Objects.equals(explicitLyrics, that.explicitLyrics) &&
           Objects.equals(explicitContentLyrics, that.explicitContentLyrics) &&
           Objects.equals(explicitContentCover, that.explicitContentCover) && Objects.equals(preview, that.preview) &&
           Objects.equals(bpm, that.bpm) && Objects.equals(gain, that.gain) &&
           Objects.equals(availableCountries, that.availableCountries) && Objects.equals(alternative, that.alternative) &&
           Objects.equals(contributers, that.contributers) && Objects.equals(md5Image, that.md5Image) &&
           Objects.equals(artist, that.artist) && Objects.equals(album, that.album);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, readable, title, titleVersion, unseen, isrc, link, share, duration, trackPosition, diskNumber, rank,
                        releaseDate,
                        explicitLyrics, explicitContentLyrics, explicitContentCover, preview, bpm, gain, availableCountries, alternative,
                        contributers, md5Image, artist, album);
  }

  @Override
  public String toString() {
    return "DeezerSong{" +
           "id=" + id +
           ", readable=" + readable +
           ", title='" + title + '\'' +
           ", titleVersion='" + titleVersion + '\'' +
           ", unseen=" + unseen +
           ", isrc='" + isrc + '\'' +
           ", link=" + link +
           ", share=" + share +
           ", duration=" + duration +
           ", trackPosition=" + trackPosition +
           ", diskNumber=" + diskNumber +
           ", rank=" + rank +
           ", releaseDate=" + releaseDate +
           ", explicitLyrics=" + explicitLyrics +
           ", explicitContentLyrics=" + explicitContentLyrics +
           ", explicitContentCover=" + explicitContentCover +
           ", preview=" + preview +
           ", bpm=" + bpm +
           ", gain=" + gain +
           ", availableCountries=" + availableCountries +
           ", alternative=" + alternative +
           ", contributers=" + contributers +
           ", md5Image='" + md5Image + '\'' +
           ", artist=" + artist +
           ", album=" + album +
           '}';
  }

  @Override
  public int compareTo(final DeezerSong o) {
    return comparator.compare(this, o);
  }
}
