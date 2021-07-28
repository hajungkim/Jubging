import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Carousel3d from 'vue-carousel-3d'
import vuetify from './plugins/vuetify'
import VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faSearch, faTrophy, faShoePrints, faHome, faUser, faMedal, faHeart, faRunning, faSignOutAlt } from '@fortawesome/free-solid-svg-icons'

library.add(faSearch, faTrophy, faShoePrints, faHome, faUser, faMedal, faHeart, faRunning, faSignOutAlt)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.use(VueBottomSheet);
Vue.use(Carousel3d)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
