<template>
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#" @click="returnHome">Bop Browser</a>
      <search-bar />
    </div>
  </nav>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";
import { useRouter } from "vue-router";

export default {
  components: { SearchBar },
  setup() {
    const router = useRouter();

    const returnHome = (evt) => {
      evt.preventDefault();
      router.push({ name: "Home" });
    };

    return { returnHome };
  },
};
</script>
