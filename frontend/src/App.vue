<template>
  <v-app>
    <NavBar 
      :userInfo="userInfo"
      :items="items"
      :messages="messages"
    />
    <!-- nav bar 수정하며 화면 보이는 부분 늘림-->
    <!-- 끝이라고 인식을 못해서 불러오질 않음-->
    <v-sheet
      id="scrolling-techniques-3"
      class="overflow-y-auto"
      v-scroll.self="onScroll"
      style="max-height: 700px">
      <router-view
        :userInfo="userInfo"
        :isScrollEnd="isScrollEnd"/>
    </v-sheet>
    <Footerbar
      :token="token"
      :userInfo="userInfo"
      @scrollToTop="onScrollToTop"
    />
  </v-app>
</template>

<script>
import Footerbar from './components/Footerbar';
import NavBar from './components/NavBar';

import { mapState, mapMutations } from 'vuex';

import UserApi from '@/api/UserApi.js'

export default {
  name: 'App',
  data() {
    return {
    isScrollEnd: false,
    messages: [],
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
    checkMessages() {
      console.log("checkMessages")
      if (this.userInfo) {
        UserApi.requestNotice(
          this.userInfo.userId,
          res => {
            console.log("체크메시지 응답성공", res)
            if (res.data.state === 'ok') {
              // API 요청 후 메세지 있다면
              this.messages = res.data.message
            } else {
              console.log(res)
            }
          },
          err => {
            console.error(err)
          }
        )
        
        this.messages = [
          { content: '1번', reviewId: 1, },
          { content: '2번', reviewId: 2, },
          { content: '3번', reviewId: 3, },
        ]
      }
    },
    tempSetListData() { 
      // 임시데이터 생성용
      const listData = [
        { title: 'Search', icon: 'mdi-search-web', destination: 'Restaurant'},
        { title: 'Map', icon: 'mdi-map', destination: 'Map' },
        { title: 'Feed', icon: 'mdi-format-align-justify', destination: 'Feed'},
        { title: 'Profile', icon: 'mdi-account', destination: 'MyProfile' },
        { title: 'Profile', icon: 'mdi-logout', destination: 'MyProfile' },
        // 로그인 로그아웃 if 분기를 위해 나눔
      ]
      this.$store.commit('nav/setItems', listData)
    },
    onScroll(e) {
      const s = e.target
      const maxLevel = s.scrollHeight - 700 // 최대 깊이
      const margin = 20 // 여유 마진
      // console.log(`현재위치:${s.scrollTop}, 스크롤 높이:${s.scrollHeight}, maxlevel:${maxLevel}`)
      if (maxLevel - margin - s.scrollTop < 0) {
        this.isScrollEnd = true
      } else {
        // console.log("스크롤엔드 값은 현재 : ", this.isScrollEnd)
        this.isScrollEnd = false
      }
    },
    onScrollToTop() {
      const target = document.querySelector('#scrolling-techniques-3')
      target.scrollTop = 0
    }
  },
  created() {
    console.log("생성시 토큰 체크")
    this.checkToken()
    this.checkUserInfo()
    this.checkMessages()
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
