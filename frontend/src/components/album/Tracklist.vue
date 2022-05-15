<template>
  <div
    v-for="song in songList"
    :key="song.id"
    :class="getSongContainerClasses(song.id)"
  >
    <song
      :album-art-uri="song.albumArtUri"
      :album-id="albumId"
      :position="song.trackPosition"
      :preview-uri="song.previewUri"
      :seconds="song.duration"
      :song-id="song.id"
      :title="song.title"
      :featuring-list="song.featuringList"
      :artist-id="song.artistId"
      :artist-name="song.artistName"
    />
  </div>
</template>

<script>
import Song from "@/components/Song.vue";

export default {
  components: { Song },
  props: {
    songList: Array,
    albumId: Number,
    selectedSongId: Number,
  },
  setup(props) {
    const getSongContainerClasses = (id) => {
      if (!props.selectedSongId) {
        return "bb-album-song";
      }

      if (props.selectedSongId === id) {
        return "bb-selected-song bb-album-song";
      }

      return "bb-album-song";
    };

    return { getSongContainerClasses };
  },
};
</script>
