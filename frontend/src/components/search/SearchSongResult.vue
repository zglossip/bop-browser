<template>
  <div class="bb-search-result">
    <div
      class="card d-flex flex-md-row"
      @click.prevent="loadSong(songResult.albumId, songResult.id)"
    >
      <img
        :alt="songResult.albumTitle"
        :src="songResult.albumArtUri"
        class="img-fluid bb-search-image"
      />
      <div class="card-body d-flex flex-column align-items-start">
        <div class="d-md-flex d-inline align-items-end">
          <audio-button
            :add-classes="['mb-2', 'me-2']"
            :audio-player-id="songResult.id + '-audio'"
            :preview-uri="songResult.previewUri"
          />
          <h2 class="card-title me-2">{{ songResult.title }}</h2>
          <span class="card-text pb-2">{{ duration }}</span>
        </div>
        <span class="card-text mb-auto">{{ songResult.albumTitle }}</span>
        <span class="card-text">
          by
          <a href="#" @click.stop="loadArtist(songResult.artistId)">
            {{ songResult.artistName }}
          </a>
          <span v-for="featured in songResult.featuringList" :key="featured.id"
            >,
            <a href="#" @click.prevent="loadArtist(featured.id)">
              {{ featured.name }}
            </a>
          </span>
        </span>
        <span class="card-text">{{ genres }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import AudioButton from "@/components/AudioButton.vue";

import { useRouter } from "vue-router";
import { computed } from "vue";
import { Duration } from "luxon";
import { getGenres } from "@/util/util.js";

export default {
  components: { AudioButton },
  props: {
    songResult: Object,
  },
  setup(props) {
    const router = useRouter();

    const genres = computed(() => getGenres(props.songResult.albumGenres));

    const loadSong = (albumId, songId) => {
      router.push({
        name: "Album",
        params: { id: albumId },
        queries: { songId },
      });
    };

    const loadArtist = (id) => {
      router.push({
        name: "Artist",
        params: { id },
      });
    };

    const duration = computed(() => {
      return Duration.fromMillis(props.songResult.duration * 1000).toFormat(
        "mm:ss"
      );
    });

    return { genres, loadSong, loadArtist, duration };
  },
};
</script>
