import { DateTime } from "luxon";

export const getGenres = (genreList) =>
  genreList.reduce((prev, cur) => prev + (prev ? ", " : "") + cur.name, "");

export const getReleaseYear = (releaseDate) => {
  return DateTime.fromISO(releaseDate).year;
};
