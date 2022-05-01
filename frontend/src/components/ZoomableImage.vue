<template>
  <button
    type="button"
    class="btn btn-link p-0"
    data-bs-toggle="modal"
    :data-bs-target="'#img' + imgKey"
  >
    <img
      :src="imgSrc"
      :alt="imgAlt"
      :class="[...imgClass, 'bb-zoomable-img']"
    />
  </button>
  <div class="modal" tabindex="-1" :id="'img' + imgKey">
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-body">
          <img :src="bigImgSrc" :alt="imgAlt" style="width: 100%" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import { Modal } from "bootstrap";

export default {
  props: {
    imgKey: String,
    imgSrc: [URL, String],
    bigImgSrc: [URL, String],
    imgAlt: String,
    imgClass: [Array, String],
  },
};
</script>
