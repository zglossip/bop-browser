<template>
  <div
    class="card bb-search-result d-flex flex-row"
    @click="loadAlbum(albumResult.id)"
  >
    <img
      :alt="albumResult.title"
      :src="albumResult.pictureUri"
      class="img-fluid rounded-start bb-search-image"
    />
    <div class="card-body d-flex flex-column align-items-start">
      <h2 class="card-title">{{ albumResult.title }}</h2>
      <span class="card-text">by {{ albumResult.artistName }}</span>
      <span class="mt-auto mb-1">{{ genres }}</span>
      <album-type-badge :type="albumResult.recordType.toUpperCase()" />
    </div>
  </div>
</template>

<script>
import AlbumTypeBadge from "@/components/AlbumTypeBadge.vue";

import { useRouter } from "vue-router";
import { computed } from "vue";

export default {
  components: {
    AlbumTypeBadge,
  },
  props: {
    albumResult: Object,
  },
  setup(props) {
    const router = useRouter();

    const genres = computed(() => {
      return props.albumResult.genreList.reduce(
        (prev, cur) => prev + (prev ? ", " : "") + cur.name,
        ""
      );
    });

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
