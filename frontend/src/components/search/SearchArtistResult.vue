<template>
  <div class="bb-search-result">
    <div class="card d-flex flex-row" @click="loadArtist(artistResult.id)">
      <img
        :alt="artistResult.name"
        :src="artistResult.pictureUri"
        class="img-fluid bb-search-image"
      />
      <div class="card-body d-flex flex-column align-items-start">
        <p class="card-title">{{ artistResult.name }}</p>
        <p class="card-text">
          {{ genres }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { useRouter } from "vue-router";
import { getGenres } from "@/util/util.js";

export default {
  props: {
    artistResult: Object,
  },
  setup(props) {
    const router = useRouter();

    const genres = computed(() => getGenres(props.artistResult.genreList));

    const loadArtist = (id) => {
      router.push({
        name: "Artist",
        params: { id },
      });
    };

    return { genres, loadArtist };
  },
};
</script>
