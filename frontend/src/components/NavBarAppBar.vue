<template>

  <v-app-bar
    color="#6A76AB"
    dark
    fixed
    src="https://picsum.photos/1920/1080?random"
    scroll-target="#scrolling-techniques-3"
  >


    <v-toolbar-title> My Food Diary </v-toolbar-title>

    <v-spacer></v-spacer>

    <div v-if="id">

    <v-list-item>
        <v-icon size="50" color="grey" style="margin:8px 16px 8px 0px;padding:0px;">mdi-account-circle</v-icon>
        <v-list-item-content>
            <v-list-item-title class="body-1">{{userInfo.nickname}}님</v-list-item-title>
        </v-list-item-content>
        <v-list-item-action style="width:30%; margin-right:10px">
            <v-btn
                depressed="depressed"
                @click="logout()"
                class="ma-2 widfull"
                outlined="outlined"
                color="primary">
                로그아웃
            </v-btn>
        </v-list-item-action>
    </v-list-item>
    </div>
  <div v-else>
  <v-btn
      depressed="depressed"
      @click="login()"
      class="ma-2 widfull"
      outlined="outlined"
      color="primary">
      로그인
  </v-btn>
  </div>

    
    <v-btn icon style="margin-top:10px">
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn>

    <v-badge dot style="margin-top:15px"
    @click.native.stop="setDrawerToggle">
    <!--:value="userInfo.알람==true"
    넣어서 새로운 알람 있을때 불들어오게-->
      <v-icon>mdi-bell</v-icon>
    </v-badge>
      
    <template v-slot:extension>
      <v-tabs 
      fixed-tabs
      >
        <v-tab to="/map/map">지도</v-tab>
        <v-tab>전체 게시글</v-tab>
        <v-tab>Tab 3</v-tab>
      </v-tabs>
    </template>
  </v-app-bar>

</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex'

export default {
  name: 'NavBarAppBar',

  computed:{
    ...mapState({
      id :state => state.user.id,
      userInfo: state => state.user.userInfo,
      drawerToggle: state => state.drawerToggle, //필요
      sampleData: state => state.sampleData,
    })
  },
  
  methods:{
    ...mapActions({
      getUserInfo: 'user/getUserInfo',
    }),
    ...mapMutations({
      setDrawerToggle: 'setDrawerToggle', //불필요?
    }),
    logout(){
      localStorage.clear();
      this.$router.push('/');
      this.$router.go('/');
    },
    login(){
      this.$router.push('/user/login');
    }
  },
  
  created(){
    this.getUserInfo()
  },

}
</script>