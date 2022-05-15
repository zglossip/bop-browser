<template>
  <navbar />
  <div class="container">
    <div class="row">
      <div class="col">
        <a :href="`#/artist/${artistId}`">&#8592; Back</a>
      </div>
    </div>
    <div class="row">
      <div class="col d-flex d-inline align-items-end">
        <h1 class="me-2">{{ artistName }} Albums</h1>
      </div>
    </div>
    <div v-if="isError">
      <div class="row">
        <div class="col">
          <div class="alert alert-danger" role="alert">
            There was an error loading the artist. Please try again.
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <div v-if="isLoading" class="row">Loading...</div>
      <div v-else class="row">
        <div v-for="album in albums" :key="album.id" class="col-6 col-md-3">
          <album-stub
            :albumId="album.id"
            :genre-list="album.genreList"
            :picture-uri="album.pictureUri"
            :recordType="album.recordType"
            :releaseYear="getReleaseYear(album.releaseDate)"
            :title="album.title"
            :artist-name="album.artistName"
            :featuring-list="album.featuringList"
            class="h-100"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import AlbumStub from "@/components/AlbumStub.vue";
import { getReleaseYear } from "@/util/util.js";
import { API_URIS } from "@/util/constants.js";

import { ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

export default {
  components: { AlbumStub, Navbar },
  setup() {
    const route = useRoute();
    const isLoading = ref(true);
    const isError = ref(false);
    const albums = ref([]);
    const artistId = ref(route.params.id);

    axios
      .get(API_URIS.getArtistAlbums(route.params.id))
      .then((response) => {
        if (response) {
          albums.value = response.data;
        }
      })
      .catch(() => (isError.value = true))
      .finally(() => (isLoading.value = false));

    return { getReleaseYear, isLoading, isError, albums, artistId };
  },
};
</script>
