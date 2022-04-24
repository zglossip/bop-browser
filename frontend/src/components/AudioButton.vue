<template>
  <audio :id="audioPlayerId" controls hidden>
    <source :src="previewUri" />
    Your browser does not support the audio element.
  </audio>
  <button
    v-if="previewUri"
    :class="classes"
    type="button"
    @click.stop="handleAudioButton"
  >
    <font-awesome-icon :icon="isPlaying ? 'pause' : 'play'" size="xs" />
  </button>
</template>

<script>
import { ref } from "vue";

export default {
  props: {
    audioPlayerId: String,
    previewUri: [String, URL],
    addClasses: Array,
  },
  setup(props) {
    const isPlaying = ref(false);

    const handleAudioButton = () => {
      if (isPlaying.value) {
        document.getElementById(props.audioPlayerId).pause();
      } else {
        document.getElementById(props.audioPlayerId).play();
      }
      isPlaying.value = !isPlaying.value;
    };

    const classes = ["btn", "btn-secondary", "btn-sm", ...props.addClasses];

    return { isPlaying, handleAudioButton, classes };
  },
};
</script>
