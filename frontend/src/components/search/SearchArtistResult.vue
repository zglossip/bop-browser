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
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  props: {
    artistResult: Object,
  },
  setup() {
    const router = useRouter();

    const loadArtist = (id) => {
      router.push({
        name: "Artist",
        params: { id },
      });
    };

    return { loadArtist };
  },
};
</script>
