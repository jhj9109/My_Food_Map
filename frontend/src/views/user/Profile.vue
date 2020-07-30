<template>
  <div>
<<<<<<< HEAD
=======
    <h1>User Profile Page</h1>
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
    <v-card
      max-width="600"
      min-height="120"
      class="mx-auto mb_custom"
      outlined="outlined"
<<<<<<< HEAD
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
              {{targetData.nickname}}님
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
            <v-btn text large color="error">Following: </v-btn> {{ targetData.Following }}
          </v-subheader>

          <v-btn class="mx-2" fab dark small color="primary">
            <v-icon dark @click="follow">mdi-plus</v-icon>
          </v-btn>

        </v-layout>
=======
      style="margin-top:15px;">
      <v-card-actions>
        <v-list-item>
          <v-icon
            size="70"
            color="blue"
            style="margin:8px 16px 8px 0px;padding:0px;">mdi-account</v-icon>
          <v-list-item-content>
            <v-list-item-title class="body-1">userInfo.nickname 님</v-list-item-title>
            <v-list-item-subtitle>이메일 : userInfo.email</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action style="width:30%;">
            <v-btn
              depressed="depressed"
              class="ma-2 widfull"
              outlined="outlined"
              color="primary">
              <v-icon left="left">mdi-pencil</v-icon>
              정보 수정
            </v-btn>
            <v-btn
              depressed="depressed"
              @click="logout()"
              class="ma-2 widfull"
              outlined="outlined"
              color="primary">
              <v-icon left="left">mdi-pencil</v-icon>
              비밀번호 수정
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-card-actions>
      <v-layout align-space-around justify-space-around row fill-height>
        <v-subheader><v-btn text large color="error">Follower</v-btn>: 0 <hr class="sero"> <v-btn text large color="error">Following</v-btn> : 0</v-subheader>
        <v-btn class="mx-2" fab dark small color="primary">
          <v-icon dark @click="follow">mdi-plus</v-icon>
        </v-btn>
      </v-layout>
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
    </v-card>
  </div>
</template>

<script>
<<<<<<< HEAD
import UserApi from '@/api/UserApi.js'

export default {
  name: 'Profile',
  props: ['userInfo'],
  data() {
    return {
      targetData: {
        id: "",
        email: "",
        nickname: "",
        follower: 0,
        following: 0,
      }
=======
export default {
  name: 'Profile',
  data() {
    return {
      userInfo: [],
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
    }
  },
  methods: {
    fetchProfile() {
<<<<<<< HEAD
      console.log("유저페이지")
      const userId = this.$route.params.userId
      UserApi.requestUserInfo(
        userId,
        res => {
          if (res.data.status == 'ok') {
            // UserPage 접근 성공
            this.targetData.id = res.data.id
            this.targetData.email = res.data.email
            this.targetData.nickname = res.data.nickname
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
    },
    follow() {
      console.log('팔로우')
      // UserApi.requestFollow(
      //   {
          
      //   }
      // )
=======
      console.log('프로필 페이지')
    //   const API_USER_INFO_URL = API_BASE_URL + '/user/'
    //   const config = {}
    //   http.get(API_USER_INFO_URL, config)
    //     .then(res => {
    //       this.userInfo = res.data
    //     })
    //     .catch(err => {
    //       console.error(err)
    //     })
    },
    follow() {
      console.log('팔로우')
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
    },
  },
  created() {
    this.fetchProfile()
  }
}
</script>

<style>

</style>