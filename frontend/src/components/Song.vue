<template>
  <div class="card d-flex flex-row">
    <img
      v-if="displayAsLink"
      :src="albumArtUri"
      alt="Album Art"
      class="img-fluid bb-track-image"
    />
    <div class="card-body d-flex px-3 py-2 align-items-center">
      <span v-if="position" class="card-text">{{ position }}.&nbsp;</span>
      <a
        v-if="displayAsLink"
        :class="[featuringList.length > 0 ? 'me-1' : 'me-auto']"
        href="#"
        @click.prevent="openSong(albumId, songId)"
      >
        {{ title }}
      </a>
      <span v-else :class="[featuringList.length > 0 ? 'me-1' : 'me-auto']">
        {{ title }}
      </span>
      <span v-if="featuringList.length > 0" class="me-1">|</span>
      <span
        v-for="(featured, id) in featuringList"
        :key="featured.id"
        :class="[id === featuringList.length - 1 ? 'me-auto' : '']"
      >
        <span v-if="id !== 0">, </span>
        <a href="#" @click.prevent="loadArtist(featured.id)">
          {{ featured.name }}
        </a>
      </span>
      <span class="ms-3">{{ duration }}</span>
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
    featuringList: Array,
  },
  setup(props) {
    const router = useRouter();

    const openSong = (albumId, songId) => {
      router.push({
        name: "Album",
        params: { id: albumId },
        query: { songId },
      });
    };

    const loadArtist = (id) => {
      router.push({
        name: "Artist",
        params: { id },
      });
    };

    const duration = computed(() => {
      //If duration longer or equal to an hour
      if (props.seconds >= 3600) {
        return Duration.fromMillis(props.seconds * 1000).toFormat("hh:mm:ss");
      }
      return Duration.fromMillis(props.seconds * 1000).toFormat("mm:ss");
    });

    return { loadArtist, openSong, duration };
  },
};
</script>
