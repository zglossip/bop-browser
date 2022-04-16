<template>
  <div>
    <div class="row">
      <div class="col text-center">
        <h1>Bop Browser</h1>
      </div>
    </div>
    <div class="row">
      <div class="col col-sm-3" />
      <div class="col-12 col-lg-6">
        <search-bar />
      </div>
      <div class="col col-sm-3" />
    </div>
  </div>
</template>

<script>
import SearchBar from "@/components/SearchBar";

export default {
  components: { SearchBar }
};
</script>
