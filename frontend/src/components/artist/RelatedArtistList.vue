<template>
  <div>
    <div class="row">
      <div class="col">
        <h2>Related Artists</h2>
      </div>
    </div>
    <div class="row gx-5">
      <div
        v-for="relatedArtist in relatedArtists"
        :key="relatedArtist.id"
        class="col-6 col-md-3"
      >
        <artist-stub
          :artist-id="relatedArtist.id"
          :name="relatedArtist.name"
          :picture-uri="relatedArtist.pictureUri"
        />
      </div>
    </div>
    <div class="row">
      <div class="col">
        <a href="#" @click.prevent="seeRelatedArtists(artistId)">See more...</a>
      </div>
    </div>
  </div>
</template>

<script>
import ArtistStub from "@/components/ArtistStub.vue";
import { useRouter } from "vue-router";

export default {
  components: {
    ArtistStub,
  },
  props: {
    relatedArtists: Array,
    artistId: Number,
  },
  setup() {
    const router = useRouter();

    const seeRelatedArtists = (id) => {
      router.push({
        name: "RelatedArtists",
        params: { id },
      });
    };

    return { seeRelatedArtists };
  },
};
</script>
