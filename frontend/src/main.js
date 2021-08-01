import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Carousel3d from 'vue-carousel-3d'
import VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faSearch, faTrophy, faShoePrints, faHome, faUser, faMedal, faAngleLeft} from '@fortawesome/free-solid-svg-icons'
import { faHeart as fasHeart, faRunning, faSignOutAlt, faComment, faUsers } from '@fortawesome/free-solid-svg-icons'
import { faHeart as farHeart , faCommentDots } from '@fortawesome/free-regular-svg-icons'
import { faBell } from '@fortawesome/free-solid-svg-icons'

library.add(faSearch, faTrophy, faShoePrints, faHome, faUser, faMedal)
library.add(fasHeart, faRunning, faSignOutAlt, faAngleLeft)
library.add(farHeart, faComment, faCommentDots, faUsers, faBell)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.use(VueBottomSheet);
Vue.use(Carousel3d)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
