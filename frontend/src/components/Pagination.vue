<template>
  <nav aria-label="Search pagination">
    <ul class="pagination">
      <li class="page-item" v-if="curPage >= 2">
        <a class="page-link" :href="`${url}&curPage=${curPage - 1}`">
          &#10096;
        </a>
      </li>

      <li class="page-item" v-if="curPage >= 3">
        <a class="page-link" :href="`${url}&curPage=1`">1</a>
      </li>

      <li class="page-item" v-if="curPage >= 4">...</li>

      <li class="page-item" v-if="curPage >= 2">
        <a class="page-link" :href="`${url}&curPage=${curPage - 1}`">
          {{ curPage - 1 }}
        </a>
      </li>

      <li class="page-item active" aria-current="page">
        <a class="page-link" href="#">
          {{ curPage }}
        </a>
      </li>

      <li class="page-item" v-if="Number(curPage) * limit <= total">
        <a class="page-link" :href="`${url}&curPage=${Number(curPage) + 1}`">
          {{ Number(curPage) + 1 }}
        </a>
      </li>

      <li class="page-item" v-if="(Number(curPage) + 2) * limit <= total">
        ...
      </li>

      <li class="page-item" v-if="(Number(curPage) + 1) * limit <= total">
        <a
          class="page-link"
          :href="`${url}&curPage=${Math.ceil(total / limit)}`"
        >
          {{ Math.ceil(total / limit) }}
        </a>
      </li>

      <li class="page-item" v-if="Number(curPage) * limit <= total">
        <a class="page-link" :href="`${url}&curPage=${Number(curPage) + 1}`">
          &#10097;
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  props: {
    curPage: Number,
    limit: Number,
    total: Number,
    url: String,
    curPageString: {
      type: String,
      default: "curPage",
    },
  },
};
</script>
