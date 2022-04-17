<template>
  <div class="bb-search-result">
    <div class="card d-flex flex-row" @click="loadAlbum(albumResult.id)">
      <img
        :alt="albumResult.title"
        :src="albumResult.pictureUri"
        class="img-fluid bb-search-image"
      />
      <div class="card-body d-flex flex-column align-items-start">
        <h2 class="card-title">{{ albumResult.title }}</h2>
        <span class="card-text">by {{ albumResult.artistName }}</span>
        <span class="mt-auto mb-1">{{ genres }}</span>
        <album-type-badge :type="albumResult.recordType.toUpperCase()" />
      </div>
    </div>
  </div>
</template>

<script>
import AlbumTypeBadge from "@/components/AlbumTypeBadge.vue";

import { useRouter } from "vue-router";
import { computed } from "vue";
import { getGenres } from "@/util/util.js";

export default {
  components: {
    AlbumTypeBadge,
  },
  props: {
    albumResult: Object,
  },
  setup(props) {
    const router = useRouter();

    const genres = computed(() => getGenres(props.albumResult.genreList));

    const loadAlbum = (id) => {
      router.push({
        name: "Album",
        params: { id },
      });
    };

    return { genres, loadAlbum };
  },
};
</script>
