<template>
  <div class="row">
    <div class="col">
      <h1>Search results for song "{{ query }}"...</h1>
    </div>
  </div>
  <div class="row">
    <div v-if="isLoading" class="col">
      <p>Loading...</p>
    </div>
    <div v-else class="col">
      <search-song-result
        v-for="songResult in songResults"
        :key="songResult.id"
        :song-result="songResult"
        class="mb-2"
      />
    </div>
  </div>
</template>

<script>
import SearchSongResult from "@/components/search/SearchSongResult.vue";

import { ref } from "vue";
import axios from "axios";
import { API_URIS } from "../../util/constants";

export default {
  components: { SearchSongResult },
  props: { query: String },
  setup(props) {
    const songResults = ref([]);
    const isLoading = ref(true);

    axios
      .get(API_URIS.searchSong(props.query))
      .then((response) => {
        if (response) {
          songResults.value = response.data;
        }
      })
      .catch(() => {
        //TODO: Handle error
      })
      .finally(() => {
        isLoading.value = false;
      });

    return { songResults, isLoading };
  },
};
</script>
