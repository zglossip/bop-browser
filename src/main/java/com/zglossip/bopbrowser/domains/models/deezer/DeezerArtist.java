package com.zglossip.bopbrowser.domains.models.deezer;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URI;
import java.util.Comparator;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerArtist implements Comparable<DeezerArtist> {
  private static final Comparator<DeezerArtist> comparator = Comparator.comparing(DeezerArtist::getId)
                                                                       .thenComparing(DeezerArtist::getName,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getLink,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getShare,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getPicture,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getPictureSmall,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getPictureMedium,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getPictureBig,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getPictureXl,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getNbAlbum,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getNbFan,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::isRadio,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()))
                                                                       .thenComparing(DeezerArtist::getTracklist,
                                                                                      Comparator.nullsLast(Comparator.naturalOrder()));
  private int id;
  private String name;
  private URI link;
  private URI share;
  private URI picture;
  @JsonAlias("picture_small")
  private URI pictureSmall;
  @JsonAlias("picture_medium")
  private URI pictureMedium;
  @JsonAlias("picture_big")
  private URI pictureBig;
  @JsonAlias("picture_xl")
  private URI pictureXl;
  @JsonAlias("nb_album")
  private Integer nbAlbum;
  @JsonAlias("nb_fan")
  private Integer nbFan;
  private Boolean radio;
  private URI tracklist;

  public DeezerArtist() {

  }

  protected DeezerArtist(final DeezerArtist deezerArtist) {
    id = deezerArtist.getId();
    name = deezerArtist.getName();
    link = deezerArtist.getLink();
    share = deezerArtist.getShare();
    picture = deezerArtist.getPicture();
    pictureSmall = deezerArtist.getPictureSmall();
    pictureMedium = deezerArtist.getPictureMedium();
    pictureBig = deezerArtist.getPictureBig();
    pictureXl = deezerArtist.getPictureXl();
    nbAlbum = deezerArtist.getNbAlbum();
    nbFan = deezerArtist.getNbFan();
    radio = deezerArtist.isRadio();
    tracklist = deezerArtist.getTracklist();
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
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

  public URI getPicture() {
    return picture;
  }

  public void setPicture(final URI picture) {
    this.picture = picture;
  }

  public URI getPictureSmall() {
    return pictureSmall;
  }

  public void setPictureSmall(final URI pictureSmall) {
    this.pictureSmall = pictureSmall;
  }

  public URI getPictureMedium() {
    return pictureMedium;
  }

  public void setPictureMedium(final URI pictureMedium) {
    this.pictureMedium = pictureMedium;
  }

  public URI getPictureBig() {
    return pictureBig;
  }

  public void setPictureBig(final URI pictureBig) {
    this.pictureBig = pictureBig;
  }

  public URI getPictureXl() {
    return pictureXl;
  }

  public void setPictureXl(final URI pictureXl) {
    this.pictureXl = pictureXl;
  }

  public Integer getNbAlbum() {
    return nbAlbum;
  }

  public void setNbAlbum(final Integer nbAlbum) {
    this.nbAlbum = nbAlbum;
  }

  public Integer getNbFan() {
    return nbFan;
  }

  public void setNbFan(final Integer nbFan) {
    this.nbFan = nbFan;
  }

  public Boolean isRadio() {
    return radio;
  }

  public void setRadio(final Boolean radio) {
    this.radio = radio;
  }

  public URI getTracklist() {
    return tracklist;
  }

  public void setTracklist(final URI tracklist) {
    this.tracklist = tracklist;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final DeezerArtist that = (DeezerArtist) o;
    return id == that.id && Objects.equals(name, that.name) && Objects.equals(link, that.link) &&
           Objects.equals(share, that.share) && Objects.equals(picture, that.picture) &&
           Objects.equals(pictureSmall, that.pictureSmall) && Objects.equals(pictureMedium, that.pictureMedium) &&
           Objects.equals(pictureBig, that.pictureBig) && Objects.equals(pictureXl, that.pictureXl) &&
           Objects.equals(nbAlbum, that.nbAlbum) && Objects.equals(nbFan, that.nbFan) &&
           Objects.equals(radio, that.radio) && Objects.equals(tracklist, that.tracklist);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, link, share, picture, pictureSmall, pictureMedium, pictureBig, pictureXl, nbAlbum, nbFan, radio,
                        tracklist);
  }

  @Override
  public String toString() {
    return "DeezerArtist{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", link=" + link +
           ", share=" + share +
           ", picture=" + picture +
           ", pictureSmall=" + pictureSmall +
           ", pictureMedium=" + pictureMedium +
           ", pictureBig=" + pictureBig +
           ", pictureXl=" + pictureXl +
           ", nbAlbum=" + nbAlbum +
           ", nbFan=" + nbFan +
           ", radio=" + radio +
           ", tracklist=" + tracklist +
           '}';
  }

  @Override
  public int compareTo(final DeezerArtist o) {
    return comparator.compare(this, o);
  }
}
