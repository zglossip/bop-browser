<template>
  <div>
    <navbar />
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <span>Query = {{ currentQuery }}</span>
          <search-artist-result-container
            v-if="currentCategory === musicCategories.ARTIST.value"
          ></search-artist-result-container>
          <search-album-result-container
            v-else-if="currentCategory === musicCategories.ALBUM.value"
          ></search-album-result-container>
          <search-song-result-container
            v-else-if="currentCategory === musicCategories.SONG.value"
          ></search-song-result-container>
          <div v-else></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import SearchArtistResultContainer from "@/components/search/SearchArtistResultContainer.vue";
import SearchAlbumResultContainer from "@/components/search/SearchAlbumResultContainer.vue";
import SearchSongResultContainer from "@/components/search/SearchSongResultContainer.vue";

import { MUSIC_CATEGORIES } from "@/util/constants.js";

import { ref } from "vue";
import { useRoute } from "vue-router";

export default {
  components: {
    Navbar,
    SearchArtistResultContainer,
    SearchAlbumResultContainer,
    SearchSongResultContainer,
  },
  setup() {
    const route = useRoute();
    const currentCategory = ref(route.params.category);
    const currentQuery = ref(route.query.q);
    const musicCategories = ref(MUSIC_CATEGORIES);

    return { currentCategory, currentQuery, musicCategories };
  },
};
</script>
