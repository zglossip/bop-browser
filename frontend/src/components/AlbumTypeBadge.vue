<template>
  <span class="badge bg-secondary">
    {{ type }}
  </span>
</template>

<script>
export default {
  props: {
    type: String,
  },
};
</script>
