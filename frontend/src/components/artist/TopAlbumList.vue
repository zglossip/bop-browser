<template>
  <div>
    <div class="row">
      <div class="col">
        <h2>Albums</h2>
      </div>
    </div>
    <div class="row gx-3">
      <div
        v-for="topAlbum in topAlbums"
        :key="topAlbum.id"
        class="col-6 col-md-3"
      >
        <album-stub
          :album-id="topAlbum.id"
          :genre-list="topAlbum.genreList"
          :picture-uri="topAlbum.pictureUri"
          :record-type="topAlbum.recordType"
          :release-year="getReleaseYear(topAlbum.releaseDate)"
          :title="topAlbum.title"
          :artist-name="topAlbum.artistName"
          :featuring-list="topAlbum.featuringList"
          class="h-100"
        />
      </div>
    </div>
    <div class="row">
      <div class="col">
        <a href="#" @click.prevent="seeMoreAlbums(id)">See more...</a>
      </div>
    </div>
  </div>
</template>

<script>
import AlbumStub from "@/components/AlbumStub.vue";
import { useRouter } from "vue-router";
import { getReleaseYear } from "@/util/util";

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

    const seeMoreAlbums = (id) => {
      router.push({
        name: "ArtistAlbums",
        params: { id },
      });
    };

    return { getReleaseYear, seeMoreAlbums };
  },
};
</script>
