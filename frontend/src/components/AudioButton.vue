<template>
  <audio :id="audioPlayerId" ref="audio" controls hidden>
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
import { inject, ref } from "vue";

export default {
  props: {
    audioPlayerId: String,
    previewUri: [String, URL],
    addClasses: Array,
  },
  setup(props) {
    const audio = ref(null);

    const emitter = inject("emitter");
    emitter.on("audio-playing", (audioPlayerId) => {
      if (audio.value && props.audioPlayerId !== audioPlayerId) {
        audio.value.pause();
        isPlaying.value = false;
      }
    });

    const isPlaying = ref(false);

    const handleAudioButton = () => {
      if (isPlaying.value) {
        audio.value.pause();
      } else {
        emitter.emit("audio-playing", props.audioPlayerId);
        audio.value.play();
      }
      isPlaying.value = !isPlaying.value;
    };

    const classes = [
      "btn",
      "btn-secondary",
      "btn-sm",
      ...(props.addClasses ? props.addClasses : []),
    ];

    return { audio, isPlaying, handleAudioButton, classes };
  },
};
</script>
