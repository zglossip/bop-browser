<template>
  <div class="card d-flex flex-md-row">
    <zoomable-image
      :img-src="pictureUri"
      :big-img-src="bigPictureUri"
      :img-alt="name"
      :img-class="['img-fluid']"
      :img-key="albumId"
    />
    <div class="card-body d-flex flex-column align-items-start">
      <h1 class="card-title">{{ name }}</h1>
      <p class="card-text">{{ releaseYear }}</p>
      <p class="card-text">{{ genres }}</p>
      <p class="card-text">{{ duration }}</p>
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
  },
  setup(props) {
    const releaseYear = getReleaseYear(props.releaseDate);
    const genres = getGenres(props.genreList);

    return { releaseYear, genres };
  },
};
</script>
