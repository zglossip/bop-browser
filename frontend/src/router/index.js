import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Search from "@/views/Search.vue";
import Artist from "@/views/Artist.vue";
import Album from "@/views/Album.vue";
import ArtistAlbums from "@/views/ArtistAlbums.vue";

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
      path: "/artist/:id/albums",
      name: "ArtistAlbums",
      component: ArtistAlbums,
    },
    {
      path: "/album/:id",
      name: "Album",
      component: Album,
    },
    {
      path: "/song/:songId",
      name: "Song",
      component: Album,
    },
  ],
});

export default router;
