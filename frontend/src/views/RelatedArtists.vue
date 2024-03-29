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
        <h1 class="me-2">{{ artistName }} Related Artists</h1>
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
        <div
          v-for="artist in relatedArtists"
          :key="artist.id"
          class="col-6 col-md-3"
        >
          <artist-stub
            :artist-id="artist.id"
            :name="artist.name"
            :picture-uri="artist.pictureUri"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Navbar from "@/components/Navbar.vue";
import ArtistStub from "@/components/ArtistStub.vue";
import { ref } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import { API_URIS } from "../util/constants";

export default {
  components: { ArtistStub, Navbar },
  setup() {
    const route = useRoute();

    const artistName = ref("");
    const isLoading = ref(true);
    const isError = ref(false);
    const relatedArtists = ref([]);
    const artistId = ref(route.params.id);

    axios
      .get(API_URIS.getRelatedArtists(route.params.id))
      .then((response) => {
        if (response) {
          relatedArtists.value = response.data;
        }
      })
      .catch(() => (isError.value = true))
      .finally(() => (isLoading.value = false));

    return { artistName, isLoading, isError, relatedArtists, artistId };
  },
};
</script>
