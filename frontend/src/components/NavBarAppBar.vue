<template>
  <v-app-bar
    color="red lighten-1"
    dark
    fixed
    scroll-target="#scrolling-techniques-3"
  >


    <v-btn to="/restaurant" class="ml-10 title" icon>
      <v-toolbar-title> My Food Diary </v-toolbar-title>
    </v-btn>

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
                color="white">
                로그아웃
            </v-btn>
        </v-list-item-action>
    </v-list-item>
    </div>

  <div v-else>
  <v-btn
      depressed="depressed"
      @click="login()"
      class="widfull"
      outlined="outlined"
      color="white">
      로그인
  </v-btn>
  </div>

    
    <v-btn icon style="margin-top:10px"
    @click.native.stop="setDrawerToggle">
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn>

    <v-badge dot style="margin-top:10px">
    <!--:value="userInfo.알람==true"
    넣어서 새로운 알람 있을때 불들어오게-->
      <v-icon>mdi-bell</v-icon>
    </v-badge>
      
    <template v-slot:extension>
      <v-tabs 
      fixed-tabs
      >
        <v-tab to="/map/map">Map</v-tab>
        <v-tab to="/restaurant">Home</v-tab>
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
      drawerToggle: state => state.nav.drawerToggle, //필요
      sampleData: state => state.nav.sampleData,
    })
  },
  
  methods:{
    ...mapActions({
      getUserInfo: 'user/getUserInfo',
    }),
    ...mapMutations({
      setDrawerToggle: 'nav/setDrawerToggle',
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