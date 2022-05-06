<template>
  <navbar />
  <div class="container">
    <div class="row">
      <div class="col">
        <a href="#" @click.prevent="back">&#8592; Back</a>
      </div>
    </div>
    <div class="row">
      <div class="col d-flex d-inline align-items-end">
        <h1 class="me-2">Related Artists</h1>
        <span v-if="artistName" class="pb-2">
          {{ artistName }}
        </span>
      </div>
    </div>
    <div v-if="isLoading" class="row">Loading...</div>
    <div v-else class="row">
      <div v-for="artist in relatedArtists" :key="artist.id" class="col-md-2">
        <artist-stub
          :artist-id="artist.id"
          :name="artist.name"
          :picture-uri="artist.pictureUri"
        />
      </div>
    </div>
  </div>
</template>
<script>
import Navbar from "@/components/Navbar.vue";
import ArtistStub from "@/components/ArtistStub.vue";
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { API_URIS } from "../util/constants";

export default {
  components: { ArtistStub, Navbar },
  setup() {
    const router = useRouter();
    const route = useRoute();

    const artistName = ref("");
    const isLoading = ref(true);
    const relatedArtists = ref([]);

    const back = () => {
      router.push({
        name: "Artist",
        params: {
          id: route.params.id,
        },
      });
    };

    axios
      .get(API_URIS.getRelatedArtists(route.params.id))
      .then((response) => {
        if (response) {
          relatedArtists.value = response.data;
        }
      })
      .catch(() => {
        //TODO Handle error
      })
      .finally(() => (isLoading.value = false));

    return { artistName, isLoading, relatedArtists, back };
  },
};
</script>
