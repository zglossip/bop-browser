<template>
  <div>
    <div class="row">
      <form class="col d-flex" method="get" @submit="onSubmit">
        <select
          v-model="currentCategory"
          aria-label="Search type selection"
          class="form-select me-2 bb-search"
        >
          <option
            v-for="category in musicCategories"
            :key="category.value"
            :value="category.value"
          >
            {{ category.label }}
          </option>
        </select>
        <div class="input-group">
          <input v-model="currentQuery" class="form-control" />
          <button class="btn btn-secondary" type="button" @click="onSubmit">
            Search
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { MUSIC_CATEGORIES } from "@/util/constants";

import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const musicCategories = ref(Object.values(MUSIC_CATEGORIES));
    const currentCategory = ref(MUSIC_CATEGORIES.ARTIST.value);
    const currentQuery = ref("");

    const router = useRouter();

    const onSubmit = (evt) => {
      evt.preventDefault();
      router.push({
        name: "Search",
        params: {
          category: currentCategory.value,
        },
        query: {
          q: currentQuery.value,
        },
      });
    };

    return { musicCategories, currentCategory, currentQuery, onSubmit };
  },
};
</script>
