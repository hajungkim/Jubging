import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Carousel3d from 'vue-carousel-3d'

Vue.use(Carousel3d)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
