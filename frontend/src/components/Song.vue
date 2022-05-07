<template>
  <div class="card p-2">
    <div class="row">
      <div class="col-auto" v-if="displayAsLink">
        <img
          :src="albumArtUri"
          alt="Album Art"
          class="img-fluid bb-track-image"
        />
      </div>
      <div class="col">
        <div class="row">
          <div class="col">
            <span v-if="position" class="card-text">{{ position }}.&nbsp;</span>
            <a
              v-if="displayAsLink"
              class="card-text fw-bolder"
              :href="`#/album/${albumId}?songId=${songId}`"
            >
              {{ title }}
            </a>
            <span v-else class="card-text fw-bolder">
              {{ title }}
            </span>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <span class="card-text">
              <a :href="`#/artist/${artistId}`" class="me-2">
                {{ artistName }}
              </a>
              <a
                v-for="featured in featuringList"
                :key="featured.id"
                :href="`#/artist/${artistId}`"
                class="me-2"
              >
                {{ featured.name }}
              </a>
            </span>
          </div>
        </div>
      </div>
      <div class="col-auto d-flex justify-content-end align-items-center">
        <span class="ms-3">{{ duration }}</span>
        <audio-button
          :add-classes="['ms-1']"
          :audio-player-id="songId + '-audio'"
          :preview-uri="previewUri"
        />
      </div>
    </div>
  </div>
</template>

<script>
import AudioButton from "@/components/AudioButton.vue";

import { computed } from "vue";
import { Duration } from "luxon";

export default {
  components: { AudioButton },
  props: {
    seconds: Number,
    title: String,
    albumArtUri: [String, URL],
    songId: Number,
    position: Number,
    albumId: Number,
    previewUri: [String, URL],
    displayAsLink: Boolean,
    artistId: Number,
    artistName: String,
    featuringList: Array,
  },
  setup(props) {
    const duration = computed(() => {
      //If duration longer or equal to an hour
      if (props.seconds >= 3600) {
        return Duration.fromMillis(props.seconds * 1000).toFormat("hh:mm:ss");
      }
      return Duration.fromMillis(props.seconds * 1000).toFormat("mm:ss");
    });

    return { duration };
  },
};
</script>
