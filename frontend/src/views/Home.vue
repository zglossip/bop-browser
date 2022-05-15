<template>
  <div class="container">
    <div class="row mb-2">
      <div class="col text-center mt-5">
        <img
          src="@/assets/bb_logo.png"
          alt="Bop Browser"
          style="max-height: 20rem; max-width: 90vw"
        />
      </div>
    </div>
    <div class="row mb-5">
      <div class="col text-center">
        <span>Free music directory</span>
      </div>
    </div>
    <div class="row">
      <div class="col col-md-3" />
      <div class="col-12 col-lg-6">
        <search-bar />
      </div>
      <div class="col col-md-3" />
    </div>
    <footer class="row mt-5">
      <div class="col col-md-3" />
      <div class="col-12 col-lg-6 small text-center">
        <p>
          Zach Glossip 2022 |
          <a href="https://github.com/zglossip/bop-browser" target="_blank">
            Github
          </a>
        </p>
        <p>
          All music data provided by
          <a href="https://developers.deezer.com/" target="_blank">
            <img
              src="@/assets/Deezer_Logo_RVB_White.svg"
              alt="Deezer"
              style="max-height: 0.7rem"
            />
          </a>
        </p>
      </div>
      <div class="col col-md-3" />
    </footer>
  </div>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";

export default {
  components: { SearchBar },
};
</script>
