import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/Home")
  }
];

export default new VueRouter({
  base: process.env.BASE_URL,
  routes
});
