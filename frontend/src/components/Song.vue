<template>
  <div class="card d-flex flex-row">
    <img
      v-if="albumArtUri"
      :src="albumArtUri"
      alt="Album Art"
      class="img-fluid bb-track-image"
    />
    <div class="card-body d-flex px-3 py-2 align-items-center">
      <span v-if="position" class="card-text">{{ position }}.&nbsp;</span>
      <a
        v-if="displayAsLink"
        class="card-text me-auto"
        href="#"
        @click.stop="openSong($event, albumId, songId)"
        >{{ title }}</a
      >
      <span v-else class="card-text me-auto">{{ title }}</span>
      <span class="card-text">{{ duration }}</span>
      <audio-button
        :add-classes="['ms-1']"
        :audio-player-id="songId + '-audio'"
        :preview-uri="previewUri"
      />
    </div>
  </div>
</template>

<script>
import AudioButton from "@/components/AudioButton.vue";

import { computed } from "vue";
import { Duration } from "luxon";
import { useRouter } from "vue-router";

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
  },
  setup(props) {
    const router = useRouter();

    const openSong = (evt, albumId, songId) => {
      evt.preventDefault();
      router.push({
        name: "Album",
        params: { id: albumId },
        query: { songId },
      });
    };

    const duration = computed(() => {
      //If duration longer or equal to an hour
      if (props.seconds >= 3600) {
        return Duration.fromMillis(props.seconds * 1000).toFormat("hh:mm:ss");
      }
      return Duration.fromMillis(props.seconds * 1000).toFormat("mm:ss");
    });

    return { openSong, duration };
  },
};
</script>
