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
        <a :href="`#/artist/${artistId}/related`">See more...</a>
      </div>
    </div>
  </div>
</template>

<script>
import ArtistStub from "@/components/ArtistStub.vue";

export default {
  components: {
    ArtistStub,
  },
  props: {
    relatedArtists: Array,
    artistId: Number,
  },
};
</script>
