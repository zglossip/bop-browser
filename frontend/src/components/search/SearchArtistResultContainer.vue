<template>
  <div class="row mt-3 mb-2">
    <div class="col">
      <h1>Artist Search</h1>
    </div>
  </div>
  <div v-if="isError">
    <div class="row">
      <div class="col">
        <div class="alert alert-danger" role="alert">
          There was an error loading the search results. Please try again.
        </div>
      </div>
    </div>
  </div>
  <div v-else>
    <div class="row mb-2">
      <div class="col">
        <h2>
          {{ isLoading ? "Loading" : total }} results for "{{ query }}"...
        </h2>
      </div>
    </div>
    <div class="row" v-if="!isLoading">
      <div class="col d-flex justify-content-center">
        <pagination
          :cur-page="curPage"
          :limit="limit"
          :total="total"
          :url="`#/search/artist?q=${query}`"
        />
      </div>
    </div>
    <div class="row" v-if="!isLoading">
      <div
        v-for="artistResult in artistResults"
        :key="artistResult.id"
        class="col-6 col-md-3"
      >
        <search-artist-result :artist-result="artistResult" class="mb-2" />
      </div>
    </div>
    <div class="row" v-if="!isLoading">
      <div class="col d-flex justify-content-center">
        <pagination
          :cur-page="curPage"
          :limit="limit"
          :total="total"
          :url="`#/search/artist?q=${query}`"
        />
      </div>
    </div>
  </div>
</template>

<script>
import SearchArtistResult from "@/components/search/SearchArtistResult.vue";
import Pagination from "@/components/Pagination.vue";

import axios from "axios";
import { API_URIS } from "../../util/constants";
import { ref } from "vue";

export default {
  components: {
    SearchArtistResult,
    Pagination,
  },
  props: {
    query: String,
    curPage: Number,
  },
  setup(props) {
    const artistResults = ref([]);
    const isLoading = ref(true);
    const isError = ref(false);

    const total = ref(0);
    const limit = ref(24);

    axios
      .get(
        API_URIS.searchArtist(
          props.query,
          limit.value,
          (props.curPage - 1) * limit.value
        )
      )
      .then((response) => {
        if (response) {
          artistResults.value = response.data.data;
          total.value = response.data.total;
        }
      })
      .catch(() => (isError.value = true))
      .finally(() => (isLoading.value = false));

    return { artistResults, isLoading, isError, total, limit };
  },
};
</script>
