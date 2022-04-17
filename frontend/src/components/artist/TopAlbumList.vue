<template>
  <div>
    <div class="row">
      <div class="col">
        <h2>Albums</h2>
      </div>
    </div>
    <div class="row gx-3">
      <div v-for="topAlbum in topAlbums" :key="topAlbum.id" class="col-3">
        <album-stub
          :album-id="topAlbum.id"
          :picture-uri="topAlbum.pictureUri"
          :record-type="topAlbum.recordType"
          :release-year="getReleaseYear(topAlbum.releaseDate)"
          :title="topAlbum.title"
          class="h-100"
        />
      </div>
    </div>
    <div class="row">
      <div class="col">
        <a href="#" @click="seeMoreAlbums($event, id)">See more...</a>
      </div>
    </div>
  </div>
</template>

<script>
import AlbumStub from "@/components/AlbumStub.vue";
import { DateTime } from "luxon";
import { useRouter } from "vue-router";

export default {
  components: {
    AlbumStub,
  },
  props: {
    topAlbums: Array,
    id: Number,
  },
  setup() {
    const router = useRouter();

    const getReleaseYear = (releaseDate) => {
      return DateTime.fromISO(releaseDate).year;
    };

    const seeMoreAlbums = (evt, id) => {
      evt.preventDefault();
      router.push({
        name: "ArtistAlbum",
        params: { id },
      });
    };

    return { getReleaseYear, seeMoreAlbums };
  },
};
</script>
