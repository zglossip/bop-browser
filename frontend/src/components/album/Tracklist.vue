<template>
  <song
    v-for="song in songList"
    :key="song.id"
    :album-art-uri="song.albumArtUri"
    :album-id="albumId"
    :position="song.trackPosition"
    :seconds="song.duration"
    :song-id="song.id"
    :title="song.title"
  />
</template>

<script>
import Song from "@/components/Song.vue";

export default {
  components: { Song },
  props: {
    songList: Array,
    albumId: Number,
  },
};
</script>
