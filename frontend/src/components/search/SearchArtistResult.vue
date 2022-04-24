<template>
  <artist-stub
    :artist-id="artistResult.id"
    :name="artistResult.name"
    :picture-uri="artistResult.pictureUri"
  />
</template>

<script>
import ArtistStub from "@/components/ArtistStub.vue";

import { useRouter } from "vue-router";

export default {
  components: {
    ArtistStub,
  },
  props: {
    artistResult: Object,
  },
  setup() {
    const router = useRouter();

    const loadArtist = (id) => {
      router.push({
        name: "Artist",
        params: { id },
      });
    };

    return { loadArtist };
  },
};
</script>
