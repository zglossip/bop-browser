<template>
  <div class="card d-flex flex-md-row">
    <zoomable-image
      :img-src="pictureUri"
      :big-img-src="bigPictureUri"
      :img-alt="name"
      :img-class="['img-fluid', 'bb-album-header-image']"
      :img-key="albumId"
    />
    <div class="card-body d-flex flex-column align-items-start">
      <h1 class="card-title">{{ name }}</h1>
      <span class="card-text">
        <a :href="`/#/artist/${artistId}`" class="me-2">
          {{ artistName }}
        </a>
        <a
          v-for="featured in featuringList"
          :key="featured.id"
          :href="`/#/artist/${featured.id}`"
          class="me-2"
        >
          {{ featured.name }}
        </a>
      </span>
      <span class="card-text">{{ releaseYear }}</span>
      <span class="card-text">{{ genres }}</span>
      <span class="card-text">{{ duration }}</span>
    </div>
  </div>
</template>

<script>
import { getGenres, getReleaseYear } from "@/util/util";
import ZoomableImage from "@/components/ZoomableImage.vue";

export default {
  components: { ZoomableImage },
  props: {
    pictureUri: [URL, String],
    bigPictureUri: [URL, String],
    name: String,
    releaseDate: Date,
    genreList: Array,
    duration: String,
    albumId: [Number, String],
    artistName: String,
    artistId: Number,
    featuringList: Array,
  },
  setup(props) {
    const releaseYear = getReleaseYear(props.releaseDate);
    const genres = getGenres(props.genreList);

    return { releaseYear, genres };
  },
};
</script>
