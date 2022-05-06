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
        <h1 class="me-2">Albums</h1>
        <span v-if="albums[0]" class="pb-2">
          {{ albums[0].artistName }}
        </span>
      </div>
    </div>
    <div v-if="isLoading" class="row">Loading...</div>
    <div v-else class="row">
      <div v-for="album in albums" :key="album.id" class="col-md-2">
        <album-stub
          :albumId="album.id"
          :genre-list="album.genreList"
          :picture-uri="album.pictureUri"
          :recordType="album.recordType"
          :releaseYear="getReleaseYear(album.releaseDate)"
          :title="album.title"
          class="h-100"
        />
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
import { useRoute, useRouter } from "vue-router";

export default {
  components: { AlbumStub, Navbar },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const isLoading = ref(true);
    const albums = ref([]);
    const back = () => {
      router.push({
        name: "Artist",
        params: {
          id: route.params.id,
        },
      });
    };

    axios
      .get(API_URIS.getArtistAlbums(route.params.id))
      .then((response) => {
        if (response) {
          albums.value = response.data;
        }
      })
      .catch(() => {
        //TODO: Handle error
      })
      .finally(() => (isLoading.value = false));

    return { getReleaseYear, back, isLoading, albums };
  },
};
</script>
