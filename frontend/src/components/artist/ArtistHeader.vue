<template>
  <div class="card">
    <zoomable-image
      :img-src="pictureUri"
      :big-img-src="bigPictureUri"
      :img-alt="name"
      :img-class="['card-img-top']"
      :img-key="artistId"
    />
    <div class="card-body ps-0">
      <h1 class="card-title">{{ name }}</h1>
      <p class="card-text">{{ genres }}</p>
    </div>
  </div>
</template>

<script>
import ZoomableImage from "@/components/ZoomableImage.vue";
export default {
  components: { ZoomableImage },
  props: {
    pictureUri: [URL, String],
    bigPictureUri: [URL, String],
    name: String,
    genres: String,
    artistId: [Number, String],
  },
};
</script>
