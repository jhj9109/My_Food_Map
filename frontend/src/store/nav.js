export default {
  namespaced: true,

  state: {
    drawerToggle: false,
    sampleData: "기본값",
    items: [],
  },
  mutations: {
    setDrawerToggle (state, data) {
      state.drawerToggle = data
      console.log("setDrawerToggle 작동")
      console.log(state.drawerToggle)
    },
    setItems (state, data) {
      state.items = data
      console.log("setItems 작동")
    }
  },
  actions: {
    onDrawerToggle({ commit }) {
      commit('setDrawerToggle', true)
      console.log("onDrawerToggle 작동")
    }
  }
}
  