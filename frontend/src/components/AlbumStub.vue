<template>
  <div class="bb-album-stub" @click="openAlbum(albumId)">
    <div class="card h-100 p-3">
      <img :alt="title + ' Picture'" :src="pictureUri" class="card-img-top" />
      <div class="card-body d-flex flex-column p-0">
        <span class="card-text">{{ title }}</span>
        <span v-if="artistName" class="card-text">{{ artistName }}</span>
        <span v-if="genres" class="card-text">{{ genres }}</span>
        <span v-if="releaseYear" class="card-text">{{ releaseYear }}</span>
        <span class="mt-auto">
          <album-type-badge :type="recordType.toUpperCase()" />
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import AlbumTypeBadge from "@/components/AlbumTypeBadge.vue";
import { useRouter } from "vue-router";
import { computed } from "vue";
import { getGenres } from "@/util/util";

export default {
  components: { AlbumTypeBadge },
  props: {
    albumId: Number,
    artistName: String,
    genreList: Array,
    pictureUri: [String, URL],
    recordType: String,
    releaseYear: [Number, String],
    title: String,
  },
  setup(props) {
    const router = useRouter();
    const openAlbum = (id) => {
      router.push({
        name: "Album",
        params: { id },
      });
    };

    const genres = computed(() =>
      props.genreList ? getGenres(props.genreList) : ""
    );

    return { genres, openAlbum };
  },
};
</script>
