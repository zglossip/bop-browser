<template>
  <RouterView :key="$route.fullPath" />
</template>

<script>
import { RouterView } from "vue-router";

export default {
  components: { RouterView },
};
</script>
