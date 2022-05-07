<template>
  <div class="row">
    <div class="col">
      <h1>Search results for "{{ query }}"...</h1>
    </div>
  </div>
  <div class="row">
    <div v-if="isLoading" class="col">
      <p>Loading...</p>
    </div>
    <div
      v-for="artistResult in artistResults"
      v-else
      :key="artistResult.id"
      class="col-6 col-md-3"
    >
      <search-artist-result :artist-result="artistResult" class="mb-2" />
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
