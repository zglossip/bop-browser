<template>
  <div class="bb-album-stub" @click="openAlbum(albumId)">
    <div class="card h-100 p-3">
      <img :alt="title + ' Picture'" :src="pictureUri" class="card-img-top" />
      <div class="card-body d-flex flex-column p-0">
        <span class="card-text">{{ title }}</span>
        <span class="card-text mb-auto">{{ releaseYear }}</span>
        <span>
          <album-type-badge :type="recordType.toUpperCase()" />
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import AlbumTypeBadge from "@/components/AlbumTypeBadge.vue";
import { useRouter } from "vue-router";

export default {
  components: { AlbumTypeBadge },
  props: {
    pictureUri: [String, URL],
    title: String,
    releaseYear: [Number, String],
    recordType: String,
    albumId: Number,
  },
  setup() {
    const router = useRouter();
    const openAlbum = (id) => {
      router.push({
        name: "Album",
        params: { id },
      });
    };

    return { openAlbum };
  },
};
</script>
