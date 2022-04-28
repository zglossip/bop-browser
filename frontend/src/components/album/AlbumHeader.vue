<template>
  <div class="card d-flex flex-sm-row">
    <img :alt="name" :src="pictureUri" class="img-fluid" />
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

export default {
  props: {
    pictureUri: URL,
    name: String,
    releaseDate: Date,
    genreList: Array,
    duration: String,
  },
  setup(props) {
    const releaseYear = getReleaseYear(props.releaseDate);
    const genres = getGenres(props.genreList);

    return { releaseYear, genres };
  },
};
</script>
