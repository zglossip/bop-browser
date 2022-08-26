package com.zglossip.bopbrowser.domains.models.lyricsovh;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Comparator;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LyricsOvhLyrics implements Comparable<LyricsOvhLyrics> {

  private String lyrics;
  private String errors;

  protected final Comparator<LyricsOvhLyrics> comparator =
          Comparator.comparing(LyricsOvhLyrics::getLyrics, Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(LyricsOvhLyrics::getErrors, Comparator.nullsLast(Comparator.naturalOrder()));

  public String getLyrics() {
    return lyrics;
  }

  public void setLyrics(final String lyrics) {
    this.lyrics = lyrics;
  }

  public String getErrors() {
    return errors;
  }

  public void setErrors(final String errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final LyricsOvhLyrics that = (LyricsOvhLyrics) o;
    return Objects.equals(lyrics, that.lyrics) && Objects.equals(errors, that.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lyrics, errors);
  }

  @Override
  public String toString() {
    return "LyricsOvhLyrics{" +
           "lyrics='" + lyrics + '\'' +
           ", errors='" + errors + '\'' +
           '}';
  }

  @Override
  public int compareTo(final LyricsOvhLyrics that) {
    return comparator.compare(this, that);
  }
}
