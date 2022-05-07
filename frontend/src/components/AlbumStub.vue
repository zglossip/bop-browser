<template>
  <a :href="`#/album/${albumId}`" class="bb-album-stub">
    <div class="card h-100 p-3">
      <img :alt="title + ' Picture'" :src="pictureUri" class="card-img-top" />
      <div class="card-body d-flex flex-column p-0">
        <span class="card-text fw-bolder fs-5">{{ title }}</span>
        <span v-if="artistName" class="card-text">
          {{ artistName }}
          <span
            v-for="featured in featuringList"
            :key="featured.id"
            class="me-2"
          >
            {{ featured.name }}
          </span>
        </span>
        <span v-if="genres" class="card-text">{{ genres }}</span>
        <span v-if="releaseYear" class="card-text">{{ releaseYear }}</span>
        <span class="mt-auto">
          <album-type-badge :type="recordType.toUpperCase()" />
        </span>
      </div>
    </div>
  </a>
</template>

<script>
import AlbumTypeBadge from "@/components/AlbumTypeBadge.vue";
import { computed } from "vue";
import { getGenres } from "@/util/util";

export default {
  components: { AlbumTypeBadge },
  props: {
    albumId: Number,
    artistName: String,
    genreList: Array,
    pictureUri: [String, URL],
    recordType: String,
    releaseYear: [Number, String],
    title: String,
    featuringList: Array,
  },
  setup(props) {
    const genres = computed(() =>
      props.genreList ? getGenres(props.genreList) : ""
    );

    return { genres };
  },
};
</script>
