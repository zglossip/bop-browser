import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Search from "@/views/Search.vue";

const router = createRouter({
  base: "BopBrowserFrontend",
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home,
    },
    {
      path: "/search/:category",
      name: "Search",
      component: Search,
    },
  ],
});

export default router;
