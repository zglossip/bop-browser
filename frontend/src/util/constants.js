export const MUSIC_CATEGORIES = {
  ARTIST: {
    value: "artist",
    label: "Artist",
  },
  ALBUM: {
    value: "album",
    label: "Album",
  },
  SONG: {
    value: "song",
    label: "Song",
  },
};

let uriBase;
// eslint-disable-next-line no-undef
if (!process.env.NODE_ENV || process.env.NODE_ENV === "development") {
  uriBase = "/BopBrowser/api/v1";
} else {
  uriBase = "/api/v1";
}

export const API_URIS = {
  searchArtist: (query, limit, index) =>
    uriBase + `/artist/search?query=${query}&limit=${limit}&index=${index}`,
  searchAlbum: (query, limit, index) =>
    uriBase + `/album/search?query=${query}&limit=${limit}&index=${index}`,
  searchSong: (query, limit, index) =>
    uriBase + `/song/search?query=${query}&limit=${limit}&index=${index}`,
  getArtist: (id) => uriBase + `/artist/${id}`,
  getArtistAlbums: (id) => uriBase + `/artist/${id}/albums`,
  getRelatedArtists: (id) => uriBase + `/artist/${id}/related`,
  getAlbum: (id) => uriBase + `/album/${id}`,
};
