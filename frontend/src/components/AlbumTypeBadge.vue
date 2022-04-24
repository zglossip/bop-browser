<template>
  <span class="badge bg-primary">
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
