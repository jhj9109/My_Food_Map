<template>
  <v-app>
    <NavBar 
      :userInfo="userInfo"
      :items="items"
    />
      <v-sheet
        id="scrolling-techniques-3"
        class="overflow-y-auto"
        v-scroll.self="onScroll"
        style="max-height: 650px"
      >
        <router-view
          :userInfo="userInfo"
          :isScrollEnd="isScrollEnd"
        />
      </v-sheet>
    <Footerbar
      :token="token"
      :userInfo="userInfo"
    />
  </v-app>
</template>

<script>
import Footerbar from './components/Footerbar';
import NavBar from './components/NavBar';

import { mapState, mapMutations } from 'vuex';

export default {
  name: 'App',
  data() {
    return {
    isScrollEnd: false,
    }
  },
  components: {
    Footerbar,
    NavBar,
  },
  computed: {
    ...mapState({
      token: state => state.user.token,
      userInfo: state => state.user.userInfo,
      items: state => state.nav.items,
    }),
  },
  methods: {
    ...mapMutations({
      setToken: 'user/setToken',
      setUserInfo: 'user/setUserInfo',
    }),
    checkToken() {
      // Vue에 token이 없지만, 로컬스토리지에 userInfo가 있다면 업데이트
      if(!this.token && !!localStorage.token) {
        this.setToken(localStorage.token)
      }
    },
    checkUserInfo() {
      // Vue에 userInfo가 없지만, 로컬스토리지에 userInfo가 있다면 업데이트
      if(!this.userInfo && !!localStorage.userInfo) {
        const storageObj = JSON.parse(localStorage.getItem('userInfo'))
        this.setUserInfo(storageObj)
      }
    },
    tempSetListData() { 
      // 임시데이터 생성용
      const listData = [
        { title: 'Home', icon: 'mdi-home' },
        { title: 'Map', icon: 'mdi-map' },
        { title: 'Profile', icon: 'mdi-comment-account-outline' },
      ]
      this.$store.commit('nav/setItems', listData)
    },
    onScroll(e) {
      const s = e.target
      const maxLevel = s.scrollHeight - 650 // 최대 깊이
      const margin = 10 // 여유 마진
      // console.log (`maxlevel:${maxLevel}, 위치:${s.scrollTop}`)
      if (maxLevel - margin - s.scrollTop < 0) {
        // console.log("끝에 도달했어")
        this.isScrollEnd = true
        // console.log("스크롤엔드 값은 현재 : ", this.isScrollEnd)
      } else {
        this.isScrollEnd = false
      }
    },
  },
  created() {
    console.log("생성시 토큰 체크")
    this.checkToken()
    this.checkUserInfo()
  },
  mounted() {
    // 임시데이터 생성용
    this.tempSetListData()
  },
  beforeUpdate() { 
    // 필요성에 대해서 아직 모르겠음
    console.log("업데이트전 토큰 체크")
    this.checkToken()
    this.checkUserInfo()
  }
};
</script>
