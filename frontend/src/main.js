import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "@/styles/styles.scss";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPause, faPlay } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(faPlay, faPause);

const app = createApp(App);

app.use(router);
app.component("font-awesome-icon", FontAwesomeIcon);

app.mount("#app");
