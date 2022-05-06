<template>
  <div>
    <navbar />
    <div class="container">
      <div class="row">
        <div class="col">
          <search-artist-result-container
            v-if="currentCategory === musicCategories.ARTIST.value"
            :query="currentQuery"
          />
          <search-album-result-container
            v-else-if="currentCategory === musicCategories.ALBUM.value"
            :query="currentQuery"
          />
          <search-song-result-container
            v-else-if="currentCategory === musicCategories.SONG.value"
            :query="currentQuery"
          />
          <div v-else>
            <span>
              <h1>404</h1>
              <p>
                Music category "{{ currentCategory }}" not found. Please return
                <a href="/" @click.prevent="returnHome"> home </a>
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
import { useRoute, useRouter } from "vue-router";

export default {
  components: {
    Navbar,
    SearchArtistResultContainer,
    SearchAlbumResultContainer,
    SearchSongResultContainer,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const currentCategory = ref(route.params.category);
    const currentQuery = ref(route.query.q);
    const musicCategories = ref(MUSIC_CATEGORIES);

    const returnHome = () => {
      router.push({ name: "Home" });
    };

    return { currentCategory, currentQuery, musicCategories, returnHome };
  },
};
</script>
