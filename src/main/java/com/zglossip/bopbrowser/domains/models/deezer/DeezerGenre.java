package com.zglossip.bopbrowser.domains.models.deezer;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URI;
import java.util.Comparator;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerGenre implements Comparable<DeezerGenre> {
  private int id;
  private String name;
  private URI picture;
  @JsonAlias("picture_small")
  private URI pictureSmall;
  @JsonAlias("picture_medium")
  private URI pictureMedium;
  @JsonAlias("picture_large")
  private URI pictureLarge;
  @JsonAlias("picture_xl")
  private URI pictureXl;

  private static final Comparator<DeezerGenre> comparator = Comparator.comparing(DeezerGenre::getId)
                                                                      .thenComparing(DeezerGenre::getName,
                                                                                     Comparator.nullsLast(Comparator.naturalOrder()))
                                                                      .thenComparing(DeezerGenre::getPicture,
                                                                                     Comparator.nullsLast(Comparator.naturalOrder()))
                                                                      .thenComparing(DeezerGenre::getPictureSmall,
                                                                                     Comparator.nullsLast(Comparator.naturalOrder()))
                                                                      .thenComparing(DeezerGenre::getPictureMedium,
                                                                                     Comparator.nullsLast(Comparator.naturalOrder()))
                                                                      .thenComparing(DeezerGenre::getPictureLarge,
                                                                                     Comparator.nullsLast(Comparator.naturalOrder()))
                                                                      .thenComparing(DeezerGenre::getPictureXl,
                                                                                     Comparator.nullsLast(Comparator.naturalOrder()));

  public DeezerGenre() {

  }

  public DeezerGenre(final DeezerGenre genre) {
    id = genre.id;
    name = genre.name;
    picture = genre.picture;
    pictureSmall = genre.pictureSmall;
    pictureMedium = genre.pictureMedium;
    pictureLarge = genre.pictureLarge;
    pictureXl = genre.pictureXl;
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

  public URI getPictureLarge() {
    return pictureLarge;
  }

  public void setPictureLarge(final URI pictureLarge) {
    this.pictureLarge = pictureLarge;
  }

  public URI getPictureXl() {
    return pictureXl;
  }

  public void setPictureXl(final URI pictureXl) {
    this.pictureXl = pictureXl;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final DeezerGenre that = (DeezerGenre) o;
    return id == that.id && Objects.equals(name, that.name) && Objects.equals(picture, that.picture) &&
           Objects.equals(pictureSmall, that.pictureSmall) && Objects.equals(pictureMedium, that.pictureMedium) &&
           Objects.equals(pictureLarge, that.pictureLarge) && Objects.equals(pictureXl, that.pictureXl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, picture, pictureSmall, pictureMedium, pictureLarge, pictureXl);
  }

  @Override
  public String toString() {
    return "DeezerGenre{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", picture=" + picture +
           ", pictureSmall=" + pictureSmall +
           ", pictureMedium=" + pictureMedium +
           ", pictureLarge=" + pictureLarge +
           ", pictureXl=" + pictureXl +
           '}';
  }

  @Override
  public int compareTo(final DeezerGenre o) {
    return comparator.compare(this, o);
  }
}
