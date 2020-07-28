<template>
  <div class="Footerbar">
    <v-card height="200px">
    <v-footer
      fixed
      flat
    >
      <v-card
        flat
        tile
        width="100%"
        class="amber darken-1 text-center"
      >
        <v-card-text>
          <v-btn :to=" this.islogin ? '/user/mypage' : '/user/login' ">
            <v-icon>mdi-account</v-icon>
          </v-btn>

          <v-btn to="/restaurant" class="mx-4" icon>
            <v-icon size="24px">mdi-home</v-icon>
          </v-btn>

           <v-btn to="/review/create" class="mx-4" icon >
            <!-- <v-icon size="24px" @click="Posting">mdi-lead-pencil</v-icon> -->
            <v-icon size="24px">mdi-lead-pencil</v-icon>
          </v-btn>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-card>
  <!-- 글쓰기 dialog 컴포넌트화 모르겠네욤.. ㅇ ㅡ ㅇ -->
  <v-row justify="center">
    <v-dialog v-model="dialog"  persistent width="unset" height="600px">
    
      <v-card  >
        <v-card-title>게시글 작성</v-card-title>
        <v-divider></v-divider>

        <v-container fluid style="padding-bottom:0px;">
          <v-textarea
           v-model="content"
            filled
            rows="9"
            value="리뷰 작성 ㄱ ㅡ ㄱ"
          ></v-textarea>
        </v-container>

        <v-divider></v-divider>

        <v-card-actions>
          <v-btn color="blue darken-1" text @click="dialog = false">close</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Photo</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Done</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>

  </div>  
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  data:()=>{
    return{
       icons: [
        'mdi-home',
        'mdi-lead-pencil',
      ],
      dialog:false,
      islogin:false,
      content: '',
      tag:[],
    }
  },
  components:{
    
  },

  created(){
    if(localStorage.getItem('id')!=null){
      this.islogin=true;
    }
    this.getUserInfo()
  },
    methods:{
    ...mapActions({
      getUserInfo:'user/getUserInfo',
    }),
    Posting(){
      this.dialog = true;
    }
  },

  computed:{
    ...mapState({
        id :state => state.user.id,
        userInfo: state => state.user.userInfo,
    })
  },
  

}
</script>
<style >
</style>
     