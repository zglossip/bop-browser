<template>
  <div
    class="card bb-search-result d-flex flex-row"
    @click="loadArtist(artistResult.id)"
  >
    <img
      :alt="artistResult.name"
      :src="artistResult.pictureUri"
      class="img-fluid rounded-start bb-search-image"
    />
    <div class="card-body d-flex flex-column align-items-start">
      <p class="card-title">{{ artistResult.name }}</p>
      <p class="card-text">
        {{ genres }}
      </p>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { useRouter } from "vue-router";

export default {
  props: {
    artistResult: Object,
  },
  setup(props) {
    const router = useRouter();

    const genres = computed(() => {
      return props.artistResult.genreList.reduce(
        (prev, cur) => prev + (prev ? ", " : "") + cur.name,
        ""
      );
    });

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
