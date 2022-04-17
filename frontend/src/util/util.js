export const getGenres = (genreList) =>
  genreList.reduce((prev, cur) => prev + (prev ? ", " : "") + cur.name, "");
