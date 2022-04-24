<template>
  <album-stub
    :album-id="albumResult.id"
    :artist-name="albumResult.artistName"
    :genre-list="albumResult.genreList"
    :picture-uri="albumResult.pictureUri"
    :record-type="albumResult.recordType"
    :title="albumResult.title"
    class="h-100"
  />
</template>

<script>
import AlbumStub from "@/components/AlbumStub.vue";
import {getReleaseYear} from "@/util/util";

export default {
  components: {
    AlbumStub,
  },
  props: {
    albumResult: Object,
  },
  setup() {
    return {getReleaseYear}
  }
};
</script>
