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

const uriBase = "/BopBrowser/api/v1";

export const API_URIS = {
  searchArtist: (query) => uriBase + `/artist/search?query=${query}`,
  searchAlbum: (query) => uriBase + `/album/search?query=${query}`,
  searchSong: (query) => uriBase + `/song/search?query=${query}`,
  getArtist: (id) => uriBase + `/artist/${id}`,
  getArtistAlbums: (id) => uriBase + `/artist/${id}/albums`,
};
