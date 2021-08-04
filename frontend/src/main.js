import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Carousel3d from 'vue-carousel-3d'
import VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
  faTrophy, faShoePrints, faHome, faUser, faMedal,
  faAngleLeft, faSearch, faRunning, faSignOutAlt, faComment, faUsers, faBell,
  faHeart as fasHeart, faCheckCircle, 
} from '@fortawesome/free-solid-svg-icons'
import { faHeart as farHeart , faCommentDots } from '@fortawesome/free-regular-svg-icons'

library.add(
  faTrophy, faShoePrints, faHome, faUser, faMedal,
  faAngleLeft, faSearch, faRunning, faSignOutAlt, faComment, faUsers, faBell,
  fasHeart, faCheckCircle
)
library.add( farHeart, faCommentDots )

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.use(VueBottomSheet);
Vue.use(Carousel3d)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
