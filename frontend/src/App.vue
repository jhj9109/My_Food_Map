<template>
  <v-app>
    <v-card>
      <NavBarAppBar
        color="#6A76AB"
        dark
        fixed
        src="https://picsum.photos/1920/1080?random"
        scroll-target="#scrolling-techniques-3"
      />
      <NavBarDrawer
      />
    </v-card>

    <div style="width:100%;height:109px;"></div> 
      <v-sheet
        id="scrolling-techniques-3"
        class="overflow-y-auto"
      >


        <router-view></router-view>

      </v-sheet>
    <div style="width:100%;height:85px;"></div>
    <Footerbar />
  </v-app>
</template>

<script>
import Footerbar from './components/Footerbar';
import NavBarAppBar from './components/NavBarAppBar';
import NavBarDrawer from './components/NavBarDrawer';
import { mapState } from 'vuex'

export default {
  name: 'App',

  components: {
    Footerbar,
    NavBarAppBar,
    NavBarDrawer,
  },

  computed:{

    ...mapState({
      id :state => state.user.id,
      userInfo: state => state.user.userInfo,
    })
  },
  methods:{
    
    logout(){
      localStorage.clear();
      this.$router.push('/');
      this.$router.go('/');
    },
    
    login(){
      this.$router.push('/user/login');
    },
  },
};
</script>
