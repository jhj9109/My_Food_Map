<template>
  <v-app>
    <v-app-bar
      color="#6A76AB"
      dark
      shrink-on-scroll
      prominent
      fixed
      src="https://picsum.photos/1920/1080?random"
      fade-img-on-scroll
      scroll-target="#scrolling-techniques-3"
    >
      <template v-slot:img="{ props }">
        <v-img
          v-bind="props"
          gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"
        ></v-img>
      </template>

      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>KING</v-toolbar-title>

      <v-spacer></v-spacer>

      <div v-if="$store.state.id">
          <v-card
  max-width="444"
  min-width="244"
  min-height="120"
  class="mx-auto mb_custom"
  outlined
  style="margin-top:15px;"
  >
  <v-card-actions>
  <v-list-item>
        <v-icon size="70" color="grey" style="margin:8px 16px 8px 0px;padding:0px;">mdi-account-circle</v-icon>
    <v-list-item-content>
      <v-list-item-title class="body-1">{{userInfo.nickname}}님</v-list-item-title>
    </v-list-item-content>
    <v-list-item-action style="width:30%;">
      <v-btn  depressed class="ma-2 widfull" outlined color="primary">
        <v-icon left>mdi-pencil</v-icon> 수정
      </v-btn>
      <v-btn depressed  @click="logout()" class="ma-2 widfull" outlined color="primary">
        로그아웃
      </v-btn>
      </v-list-item-action>
  </v-list-item>
  </v-card-actions>
  </v-card>
      </div>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>

      <template v-slot:extension>
        <v-tabs 
        fixed-tabs
        >
          <v-tab>Tab 1</v-tab>
          <v-tab>Tab 2</v-tab>
          <v-tab>Tab 3</v-tab>
        </v-tabs>
      </template>
    </v-app-bar>

 <div style="width:100%;height:200px;"></div>
    <v-sheet
      id="scrolling-techniques-3"
      class="overflow-y-auto"
      max-height="600"
    >


<router-view></router-view>

<div style="width:100%;height:60px;"></div>
  <Footerbar />
    </v-sheet>
  </v-app>
</template>

<script>
import Footerbar from './components/Footerbar';
import { mapActions,mapState } from 'vuex'

export default {
  name: 'App',

   computed:{
      ...mapState({
        islogin :state => state.user.id,
        userInfo: state => state.user.userInfo,
      })
    },
    methods:{
    ...mapActions({
      getUserInfo: 'user/getUserInfo',
    }),
    logout(){
         localStorage.clear();
         this.$router.push('/');
         this.$router.go('/');
      },
    },
  created(){
    this.getUserInfo()
  },

  components: {
    Footerbar,
  },
};
</script>
