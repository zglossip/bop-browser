<template>
  <div class="row">
    <div class="col">
      <h1>Search results for "{{ query }}"...</h1>
    </div>
  </div>
  <div class="row">
    <div class="col">
      <p v-if="isLoading">Loading...</p>
      <search-artist-result
        v-for="artistResult in artistResults"
        v-else
        :key="artistResult.id"
        :artist-result="artistResult"
        class="mb-2"
      />
    </div>
  </div>
</template>

<script>
import SearchArtistResult from "@/components/search/SearchArtistResult.vue";

import axios from "axios";
import { API_URIS } from "../../util/constants";
import { ref } from "vue";

export default {
  components: {
    SearchArtistResult,
  },
  props: {
    query: String,
  },
  setup(props) {
    const artistResults = ref([]);
    const isLoading = ref(true);

    axios
      .get(API_URIS.searchArtist(props.query))
      .then((response) => {
        if (response) {
          artistResults.value = response.data;
        }
      })
      .catch(() => {
        //TODO: Handle error
      })
      .finally(() => {
        isLoading.value = false;
      });

    return { artistResults, isLoading };
  },
};
</script>
