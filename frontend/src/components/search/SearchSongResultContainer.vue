<template>
  <div class="row mt-3 mb-2">
    <div class="col">
      <h1>Song Search</h1>
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
          :url="`#/search/song?q=${query}`"
        />
      </div>
    </div>
    <div v-if="!isLoading" class="row">
      <div
        class="col-12 mb-3"
        v-for="songResult in songResults"
        :key="songResult.id"
      >
        <search-song-result :song-result="songResult" class="mb-2" />
      </div>
    </div>
    <div class="row" v-if="!isLoading">
      <div class="col d-flex justify-content-center">
        <pagination
          :cur-page="curPage"
          :limit="limit"
          :total="total"
          :url="`#/search/song?q=${query}`"
        />
      </div>
    </div>
  </div>
</template>

<script>
import SearchSongResult from "@/components/search/SearchSongResult.vue";
import Pagination from "@/components/Pagination.vue";

import { ref } from "vue";
import axios from "axios";
import { API_URIS } from "@/util/constants";

export default {
  components: { SearchSongResult, Pagination },
  props: {
    query: String,
    curPage: Number,
  },
  setup(props) {
    const songResults = ref([]);
    const isLoading = ref(true);
    const isError = ref(false);

    const total = ref(0);
    const limit = ref(10);

    axios
      .get(
        API_URIS.searchSong(
          props.query,
          limit.value,
          (props.curPage - 1) * limit.value
        )
      )
      .then((response) => {
        if (response) {
          songResults.value = response.data.data;
          total.value = response.data.total;
        }
      })
      .catch(() => (isError.value = true))
      .finally(() => (isLoading.value = false));

    return { songResults, isLoading, isError, total, limit };
  },
};
</script>
