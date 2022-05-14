<template>
  <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img
          style="width: 50px; height: 50px"
          src="@/assets/bb_logo_small_dark.png"
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

export default {
  components: { SearchBar },
};
</script>
