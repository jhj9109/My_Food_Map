<template>
  <div>
    <v-card
      max-width="600"

      min-height="120"
      class="mx-auto mb_custom"
      outlined="outlined"
      style="margin-top:15px;"
    >
      <v-card-actions>
        <v-list-item>
          <v-icon
              size="70"
              color="blue"
              style="margin:8px 16px 8px 0px;padding:0px;">mdi-account
          >
          </v-icon>
          <v-list-item-content>
            <v-list-item-title class="body-1">
              {{targetData.id}}님
            </v-list-item-title>
            <v-list-item-subtitle>이메일 :
              {{targetData.email}}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        
      </v-card-actions>
        <v-layout align-space-around justify-space-around row fill-height>
            
            <v-subheader>
              <v-btn text large color="error">Follower: </v-btn> {{ targetData.follower }}<hr class="sero">
              <v-btn text large color="error">Following: </v-btn> {{ targetData.follower }}
            </v-subheader>
            <!-- <팔로잉 수> -->
        </v-layout>
    </v-card>
  </div>
</template>
      
<script>
import { mapState } from 'vuex';
import UserApi from '@/api/UserApi.js'

export default {
  data() {
    return {
      targetData: {
        id: "",
        follower: 0,
        following: 0,
      }
    }
  },
  computed:{
    ...mapState({
      userInfo: state => state.user.userInfo,
      })
  },
  methods:{

  },
  beforeMount(){
    console.log("유저페이지")
    const userId = this.$route.params.userId
    UserApi.requestUserInfo(
      userId,
      res => {
        if (res.data.status == 'ok') {
          // UserPage 접근 성공
          this.targetData.id = res.data.id
          this.targetData.email = res.data.email
          this.targetData.follower = res.data.follower
          this.targetData.following = res.data.following
        } else {
          // UserPage 접근 실패 === 올바른 주소 접근이 아님
          // replace : history 남기지 않음 => 404페이지에서 뒤로가기 버튼 올바르게 동작
          this.$router.replace({ name: 'NotFound'})
        }
      },
      err => {
        console.error(err)
        this.$router.replace({ name: 'ErrorPage'}) // 히스토리 남기지 않음
      }
    )
  }
};

</script>
<style scoped>
  hr.sero{height:100%; width:1px; border-right:1px solid black;}
</style>
