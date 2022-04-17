import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Search from "@/views/Search.vue";
import Artist from "@/views/Artist.vue";
import Album from "@/views/Album.vue";

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
    {
      path: "/artist/:id",
      name: "Artist",
      component: Artist,
    },
    {
      path: "/album/:id",
      name: "Album",
      component: Album,
    },
    {
      path: "/album/:albumId",
      name: "Song",
      component: Album,
    },
  ],
});

export default router;