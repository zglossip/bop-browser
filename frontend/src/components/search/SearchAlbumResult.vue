<template>
  <album-stub
    :album-id="albumResult.id"
    :artist-name="albumResult.artistName"
    :genre-list="albumResult.genreList"
    :picture-uri="albumResult.pictureUri"
    :record-type="albumResult.recordType"
    :title="albumResult.title"
  />
</template>

<script>
import AlbumStub from "@/components/AlbumStub.vue";

export default {
  components: {
    AlbumStub,
  },
  props: {
    albumResult: Object,
  },
};
</script>
