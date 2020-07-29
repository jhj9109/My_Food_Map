<template>
  <div class="user" id="logout">
  </div>
</template>

<script>
import {  mapActions } from 'vuex'

export default {
  methods: {
    ...mapActions ({
      logout: 'user/logout'
    })
  },
  // 아래와 동일한 기능 & 다른 타이밍
  // beforeRouteEnter(to, from, next) {
  //   console.log("로그아웃.vue 진입1 =>")
  //   console.log("this : ", this)
  //   const token = localStorage.token
  //   next(vm => {
  //       console.log("vm 진입", vm)
  //       vm.$store.dispatch('user/logout', {token : token})
  //       console.log("vm.$router.push")
  //       vm.$router.push({ name: 'Login'})
  //   })
  // },
  beforeMount() {
    const token = localStorage.token
    const data = {
      token: token
    }
    console.log("로그아웃.vue 진입2 => token값은 : ", token, data.token)
    this.$store.dispatch('user/logout', data)
  }

};
</script>


