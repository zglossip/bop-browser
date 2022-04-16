import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "@/styles/styles.scss";
import {library} from "@fortawesome/fontawesome-svg-core";
import {
  faBook,
  faCopy,
  faExclamationCircle,
  faFileExcel,
  faMousePointer,
  faPaperPlane,
  faSort,
  faSortDown,
  faSortUp,
  faSyncAlt
} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

library.add(
  faCopy,
  faSyncAlt,
  faExclamationCircle,
  faBook,
  faSort,
  faSortUp,
  faSortDown,
  faPaperPlane,
  faFileExcel,
  faMousePointer
);

Vue.component("font-awesome-icon", FontAwesomeIcon);

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
