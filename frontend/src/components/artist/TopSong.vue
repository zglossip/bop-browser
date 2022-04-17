<template>
  <div class="bb-song">
    <div class="card d-flex flex-row" @click="openSong(songId)">
      <img
        :src="albumArtUri"
        alt="Album Art"
        class="img-fluid bb-track-image"
      />
      <div
        class="card-body d-flex justify-content-between px-3 py-2 align-items-center"
      >
        <span class="card-text">{{ title }}</span>
        <span class="card-text">{{ duration }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { Duration } from "luxon";
import { useRouter } from "vue-router";

export default {
  props: {
    seconds: Number,
    title: String,
    albumArtUri: [String, URL],
    songId: Number,
  },
  setup(props) {
    const router = useRouter();
    const openSong = (songId) => {
      router.push({
        name: "Song",
        params: { songId },
      });
    };

    const duration = computed(() => {
      return Duration.fromMillis(props.seconds * 1000).toFormat("mm:ss");
    });

    return { openSong, duration };
  },
};
</script>
