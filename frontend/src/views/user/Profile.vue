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
    </v-card>
  </div>
</template>

<script>
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
    }
  },
  methods: {
    fetchProfile() {
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
    },
  },
  created() {
    this.fetchProfile()
  }
}
</script>

<style>

</style>