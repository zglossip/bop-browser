<template>
  <div>
    <navbar class="mb-3" />
    <div class="container">
      <div v-if="isLoading" class="row">
        <div class="col">Loading...</div>
      </div>
      <div v-else>
        <div class="row mb-4 gx-5">
          <div class="col-md-4">
            <artist-header
              :genres="genres"
              :name="artist.name"
              :picture-uri="artist.pictureUri"
            />
          </div>
          <div class="col-md-8">
            <div class="row mb-5">
              <div class="col">
                <top-song-list :top-songs="artist.topSongList" />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <top-album-list
                  :id="artist.id"
                  :top-albums="artist.topAlbumList"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <related-artist-list
              :artist-id="artist.id"
              :related-artists="artist.relatedArtistList"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ArtistHeader from "@/components/artist/ArtistHeader.vue";
import TopSongList from "@/components/artist/TopSongList.vue";
import TopAlbumList from "@/components/artist/TopAlbumList.vue";
import RelatedArtistList from "@/components/artist/RelatedArtistList.vue";

import { computed, ref } from "vue";
import axios from "axios";
import { API_URIS } from "@/util/constants";
import { useRoute } from "vue-router";
import { getGenres } from "../util/util";

export default {
  components: {
    Navbar,
    ArtistHeader,
    TopSongList,
    TopAlbumList,
    RelatedArtistList,
  },
  setup() {
    const route = useRoute();

    const isLoading = ref(true);
    const artist = ref({});

    const genres = computed(() => getGenres(artist.value.genreList));

    axios
      .get(API_URIS.getArtist(route.params.id))
      .then((response) => {
        if (response) {
          artist.value = response.data;
        }
      })
      .catch(() => {
        //TODO: Handle error
      })
      .finally(() => {
        isLoading.value = false;
      });

    return { isLoading, artist, genres };
  },
};
</script>
