import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "@/styles/styles.scss";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPause, faPlay } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import mitt from "mitt";

library.add(faPlay, faPause);

const emitter = mitt();

const app = createApp(App);

app.use(router);
app.component("font-awesome-icon", FontAwesomeIcon);
app.provide("emitter", emitter);

app.mount("#app");
