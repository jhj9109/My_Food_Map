<template>
  <v-app>
    <v-app-bar
      color="#6A76AB"
      dark
      fixed
      src="https://picsum.photos/1920/1080?random"
      scroll-target="#scrolling-techniques-3"
    >


      <v-toolbar-title> 제목 이미지</v-toolbar-title>

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
      @click.native.stop="drawerToggle = !drawerToggle">
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
    
    <v-navigation-drawer
      v-model="drawerToggle"
      fixed
      temporary
      right
      width="380"
    >
      <v-list-item>
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>알림 메시지</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense>

        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>


 <div style="width:100%;height:150px;"></div>
    <v-sheet
      id="scrolling-techniques-3"
      class="overflow-y-auto"
    >


<router-view></router-view>

<div style="width:100%;height:20px;"></div>
  <Footerbar />
    </v-sheet>
  </v-app>
</template>

<script>
import Footerbar from './components/Footerbar';
import { mapActions,mapState } from 'vuex'

export default {
  data(){
    return{
      drawerToggle: false,
      items: [
          { title: 'Home', icon: 'dashboard' },
          { title: 'About', icon: 'question_answer' },
        ],
    }
  },
  name: 'App',

   computed:{
      ...mapState({
        id :state => state.user.id,
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
    login(){
      this.$router.push('/user/login');
    }
    },
  created(){
    this.getUserInfo()
  },

  components: {
    Footerbar,
  },
};
</script>
