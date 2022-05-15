<template>
  <div>
    <navbar />
    <div class="container">
      <div class="row">
        <div class="col">
          <search-artist-result-container
            v-if="currentCategory === musicCategories.ARTIST.value"
            :query="currentQuery"
            :cur-page="curPage"
          />
          <search-album-result-container
            v-else-if="currentCategory === musicCategories.ALBUM.value"
            :query="currentQuery"
            :cur-page="curPage"
          />
          <search-song-result-container
            v-else-if="currentCategory === musicCategories.SONG.value"
            :query="currentQuery"
            :cur-page="curPage"
          />
          <div v-else>
            <span>
              <h1>404</h1>
              <p>
                Music category "{{ currentCategory }}" not found. Please return
                <a href="#"> home </a>
              </p>
            </span>
          </div>
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
    const curPage = ref(route.query.curPage ? route.query.curPage : 1);
    const musicCategories = ref(MUSIC_CATEGORIES);

    return { currentCategory, currentQuery, curPage, musicCategories };
  },
};
</script>
