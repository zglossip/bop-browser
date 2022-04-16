<template>
  <div>
    <div class="row">
      <form
        class="col d-flex"
        @submit="onSubmit(currentCategory, currentQuery)"
      >
        <select
          v-model="currentCategory"
          aria-label="Search type selection"
          class="form-select me-2 bb-search"
        >
          <option
            v-for="category in musicCategories"
            :key="category.value"
            :value="category.value"
            >{{ category.label }}
          </option>
        </select>
        <div class="input-group">
          <input v-model="currentQuery" class="form-control" />
          <button
            class="btn btn-secondary"
            type="button"
            @click="onSubmit(currentCategory, currentQuery)"
          >
            Search
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { MUSIC_CATEGORIES } from "@/util/constants";
import { ref } from "@vue/composition-api";

export default {
  setup(props, context) {
    const musicCategories = ref(Object.values(MUSIC_CATEGORIES));
    const currentCategory = ref(MUSIC_CATEGORIES.ARTIST.value);
    const currentQuery = ref("");

    const onSubmit = (category, query) => {
      context.root.$router.push("/search/" + category + "?query=" + query);
    };

    return { musicCategories, currentCategory, currentQuery, onSubmit };
  }
};
</script>
