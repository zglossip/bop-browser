<template>
  <a :href="`#/artist/${artistId}`" class="bb-artist-stub">
    <div class="card h-100 p-3">
      <img :alt="name + ' Picture'" :src="pictureUri" class="card-img-top" />
      <div class="card-body p-0">
        <p class="card-text fw-bolder fs-5">{{ name }}</p>
      </div>
    </div>
  </a>
</template>

<script>
export default {
  props: {
    pictureUri: String,
    name: String,
    artistId: Number,
  },
};
</script>
