<template>
  <div>
    <navbar class="mb-3" />
    <div class="container">
      <div v-if="isError">
        <div class="row">
          <div class="col">
            <div class="alert alert-danger" role="alert">
              There was an error loading the album. Please try again.
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <div v-if="isLoading" class="row">
          <div class="col-md-8 offset-md-2">Loading...</div>
        </div>
        <div v-else>
          <div class="row mb-3">
            <div class="col-md-8 offset-md-2">
              <a :href="`#/artist/${album.artistId}`">
                &#8592; {{ album.artistName }}
              </a>
            </div>
          </div>
          <div class="row mb-2">
            <div class="col-md-8 offset-md-2">
              <album-header
                :duration="duration"
                :genre-list="album.genreList"
                :name="album.title"
                :picture-uri="album.pictureUri"
                :big-picture-uri="album.bigPictureUri"
                :release-date="album.releaseDate"
                :album-id="album.id"
                :artist-name="album.artistName"
                :artist-id="album.artistId"
                :featuring-list="album.featuringList"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-md-8 offset-md-2">
              <tracklist
                :album-id="album.id"
                :song-list="album.songList"
                :selected-song-id="selectedSongId"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import AlbumHeader from "@/components/album/AlbumHeader.vue";
import Tracklist from "@/components/album/Tracklist.vue";
import { computed, ref } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import { API_URIS } from "@/util/constants";
import { Duration } from "luxon";

export default {
  components: { AlbumHeader, Tracklist, Navbar },
  setup() {
    const route = useRoute();

    const album = ref({});
    const isLoading = ref(true);
    const isError = ref(false);

    const duration = computed(() => {
      const albumLength = album.value.songList.reduce(
        (length, song) => length + song.duration,
        0
      );
      //If duration longer or equal to an hour
      if (albumLength >= 3600) {
        return Duration.fromMillis(albumLength * 1000).toFormat(
          "h 'hour(s), 'm' minute(s)'"
        );
      }
      return Duration.fromMillis(albumLength * 1000).toFormat(
        "m 'minute(s), ' s 'second(s)'"
      );
    });

    const selectedSongId = ref(Number(route.query.songId));

    axios
      .get(API_URIS.getAlbum(route.params.id))
      .then((response) => {
        if (response) {
          album.value = response.data;
        }
      })
      .catch(() => (isError.value.vaue = true))
      .finally(() => (isLoading.value = false));

    return { album, isLoading, isError, duration, selectedSongId };
  },
};
</script>
