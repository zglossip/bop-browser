<template>
  <div class="card">
    <img :alt="name + 'Picture'" :src="pictureUri" class="card-img-top" />
    <div class="card-body">
      <h1 class="card-title">{{ name }}</h1>
      <p class="card-text">{{ genres }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    pictureUri: String,
    name: String,
    genres: String,
  },
};
</script>
