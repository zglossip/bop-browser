import { createRouter, createWebHashHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Search from "@/views/Search.vue";
import Artist from "@/views/Artist.vue";
import Album from "@/views/Album.vue";
import ArtistAlbums from "@/views/ArtistAlbums.vue";
import RelatedArtists from "@/views/RelatedArtists.vue";

const router = createRouter({
  history: createWebHashHistory(),
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
      path: "/artist/:id/related",
      name: "RelatedArtists",
      component: RelatedArtists,
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
