<template>
  <div class="row">
    <div class="col">
      <h1>Search results for album "{{ query }}"...</h1>
    </div>
  </div>
  <div class="row">
    <div v-if="isLoading" class="col">
      <p>Loading...</p>
    </div>
    <div
      v-for="albumResult in albumResults"
      v-else
      :key="albumResult.id"
      class="col-md-2"
    >
      <search-album-result :album-result="albumResult" class="mb-2" />
    </div>
  </div>
</template>

<script>
import SearchAlbumResult from "@/components/search/SearchAlbumResult.vue";

import { ref } from "vue";
import axios from "axios";
import { API_URIS } from "@/util/constants";

export default {
  components: { SearchAlbumResult },
  component: {
    SearchAlbumResult,
  },
  props: {
    query: String,
  },
  setup(props) {
    const albumResults = ref([]);
    const isLoading = ref(true);

    axios
      .get(API_URIS.searchAlbum(props.query))
      .then((response) => {
        if (response) {
          albumResults.value = response.data;
        }
      })
      .catch(() => {
        //TODO: Handle error
      })
      .finally(() => {
        isLoading.value = false;
      });

    return { albumResults, isLoading };
  },
};
</script>
