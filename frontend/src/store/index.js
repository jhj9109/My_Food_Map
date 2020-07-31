import Vue from 'vue'
import Vuex from 'vuex'
import user from './user' 
import nav from './nav' 


Vue.use(Vuex)

export default new Vuex.Store({
  modules:{
    user,
    nav
  },
})
