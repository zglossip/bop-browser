<template>
  <div class="bb-search-result">
    <div class="card d-flex flex-md-row">
      <img
        :alt="songResult.albumTitle"
        :src="songResult.albumArtUri"
        class="img-fluid bb-search-image"
      />
      <div class="card-body d-flex flex-column align-items-start">
        <div class="d-md-flex d-inline">
          <audio-button
            :add-classes="['mb-2', 'me-2']"
            :audio-player-id="songResult.id + '-audio'"
            :preview-uri="songResult.previewUri"
          />
          <a
            class="fw-bolder me-2"
            :href="`#/album/${songResult.albumId}?songId=${songResult.id}`"
          >
            {{ songResult.title }}
          </a>
          <span class="card-text pb-2">{{ duration }}</span>
        </div>
        <a
          class="card-text mb-auto"
          :href="`#/album/${songResult.albumId}?songId=${songResult.id}`"
        >
          {{ songResult.albumTitle }}
        </a>
        <span class="card-text">
          by
          <a :href="`#/artist/${songResult.artistId}`">
            {{ songResult.artistName }}
          </a>
          <span v-for="featured in songResult.featuringList" :key="featured.id"
            >,
            <a :href="`#/artist/${featured.artistId}`">
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

import { computed } from "vue";
import { Duration } from "luxon";
import { getGenres } from "@/util/util.js";

export default {
  components: { AudioButton },
  props: {
    songResult: Object,
  },
  setup(props) {
    const genres = computed(() => getGenres(props.songResult.albumGenres));

    const duration = computed(() => {
      return Duration.fromMillis(props.songResult.duration * 1000).toFormat(
        "mm:ss"
      );
    });

    return { genres, duration };
  },
};
</script>
