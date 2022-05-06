<template>
  <div class="bb-artist-stub" @click.prevent="openArtist(artistId)">
    <div class="card h-100 p-3">
      <img :alt="name + ' Picture'" :src="pictureUri" class="card-img-top" />
      <div class="card-body p-0">
        <p class="card-text fw-bolder fs-5">{{ name }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  props: {
    pictureUri: String,
    name: String,
    artistId: Number,
  },
  setup() {
    const router = useRouter();
    const openArtist = (id) => {
      router.push({
        name: "Artist",
        params: { id },
      });
    };

    return { openArtist };
  },
};
</script>
