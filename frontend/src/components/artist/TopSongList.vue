<template>
  <div>
    <div class="row mb-3">
      <div class="col">
        <h2>Top Songs</h2>
      </div>
    </div>
    <div v-for="song in topSongs" :key="song.id" class="row px-3">
      <div class="col">
        <song
          :album-art-uri="song.albumArtUri"
          :preview-uri="song.previewUri"
          :seconds="song.duration"
          :song-id="song.id"
          :title="song.title"
          :album-id="song.albumId"
          :featuring-list="song.featuringList"
          :artist-id="song.artistId"
          :artist-name="song.artistName"
          display-as-link
          class="mb-2"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Song from "@/components/Song.vue";

export default {
  components: { Song },
  props: {
    topSongs: Array,
  },
};
</script>
