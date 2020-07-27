import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import routes from './routes'
import store from './store'


import 'es6-promise/auto'
Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(Vuex)

const router = new VueRouter({
  routes,
  mode: 'history',
});

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')

export default router
