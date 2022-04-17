<template>
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Bop Browser</a>
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
