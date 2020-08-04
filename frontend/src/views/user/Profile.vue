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
          <!-- 아이콘 디자인 변경 -->
          <v-icon
              size="70"
              color="#F7B675"
              style="margin:4px 4px 8px 0px; padding:0px;">mdi-account-circle
          >
          </v-icon>
          <v-list-item-content>
            <v-list-item-title class="body-1">
              {{profileUser.nickname}}님
            </v-list-item-title>
          <!-- 팔로워 팔로잉 변경 -->
            <v-list-item-subtitle class="d-flex flex-column-reverse mb-1"
            >
              팔로워: {{profileUser.follower}}
              팔로잉: {{ profileUser.following }}
            </v-list-item-subtitle>
          <!-- 이메일 -> 자기소개로 변경  -->
            <v-list-item-subtitle 
            >
              {{profileUser.email}}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        
      <!-- 팔로워/팔로잉 디자인 수정 -->
      </v-card-actions>
        <v-layout class="mb-2 mr-3 justify-end">
          <!--
          <v-subheader>
            <v-btn text large color="error">Follower: {{ profileUser.follower }} </v-btn> 
            <v-btn text large color="error">Following: {{ profileUser.following }} </v-btn> 
          </v-subheader>
          -->
          <v-btn  @click="onFollow" dark small color=#F7B675>
            <v-icon> mdi-plus </v-icon>
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
      profileUser: {
        id: "",
        email: "",
        nickname: "",
        follower: 0,
        following: 0,
      }
    }
  },
  methods: {
    // 프로필 디자인을 위한 일시 정지
    // fetchProfile() {
    //   console.log("유저페이지")
    //   const userId = this.$route.params.userId
    //   UserApi.requestUserInfo(
    //     userId,
    //     res => {
    //       if (res.data.status == 'ok') {
    //         // UserPage 접근 성공
    //         this.profileUser.id = res.data.id
    //         this.profileUser.email = res.data.email
    //         this.profileUser.nickname = res.data.nickname
    //         this.profileUser.follower = res.data.follower
    //         this.profileUser.following = res.data.following
    //       } else {
    //         // UserPage 접근 실패 === 올바른 주소 접근이 아님
    //         // replace : history 남기지 않음 => 404페이지에서 뒤로가기 버튼 올바르게 동작
    //         this.$router.replace({ name: 'NotFound'})
    //       }
    //     },
    //     err => {
    //       console.error(err)
    //       this.$router.replace({ name: 'ErrorPage'}) // 히스토리 남기지 않음
    //     }
    //   )
    // },
    // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 디자인에 사용할 더미 데이터
    fetchProfile() {
            this.profileUser.id = 1
            this.profileUser.email = "이메일 대신 자기소개면 될 것 같아요. 만약 길어지면 밖으로 나가지 않고 밑으로 내려가게 or 최대 글자 수 제한"
            this.profileUser.nickname = "my_nickname"
            this.profileUser.follower = 1234
            this.profileUser.following = 5678
    },
    onFollow() {
      console.log('팔로우', this.userInfo.userId, this.profileUser.id)
      UserApi.requestFollow(
        {
          userId: this.userInfo.userId,
          followingId: this.profileUser.id
        },

        res => {
          if (res.data.message === "Following -1") {
            this.profileUser.follower -= 1
            console.log("팔로워 숫자 -1")
          } else {
            if (res.data.message === "Following +1") {
              this.profileUser.follwer += 1
              console.log("팔로워 숫자 +1")
            } else {
              // 성공외 다른 응답이 왔을때 동작
              console.log("팔로우 실패", res)
            }
          }
        },
        err => {
          console.error(err)
          // 라우팅 하지 않음
        }
      )
    },
  },
  created() {
    this.fetchProfile()
  }
}
</script>

<style>

</style>