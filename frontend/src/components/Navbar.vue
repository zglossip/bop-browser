<template>
  <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#" @click.prevent="returnHome">
        <img
          style="width: 50px"
          src="/bb_logo_small_dark.png"
          alt="Bop Browser"
          class="d-inline-block align-text-top mb-md-2"
        />
      </a>
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
