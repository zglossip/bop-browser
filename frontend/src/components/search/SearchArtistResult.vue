<template>
  <artist-stub
    :artist-id="artistResult.id"
    :name="artistResult.name"
    :picture-uri="artistResult.pictureUri"
  />
</template>

<script>
import ArtistStub from "@/components/ArtistStub.vue";

export default {
  components: {
    ArtistStub,
  },
  props: {
    artistResult: Object,
  },
};
</script>
