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
          <!-- 아이콘 디자인 변경, 위치 이동 사진 추가 유무에 따른 분기 필요-->
          <v-icon
              size="70"
              color="#F7B675"
              style="margin:4px 4px 0px -16px;">mdi-account-circle
          >
          </v-icon>
          <v-list-item-content>
            <v-list-item-title class="body-1">
              {{profileUser.nickname}}님
            </v-list-item-title>
          <!-- 팔로워 팔로잉 변경 -->
            <v-list-item-subtitle class="mb-1"
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
        
      <!-- 팔로워/팔로잉 디자인 수정 팔로우 했을 경우 팔로우 취소로 변경-->
      </v-card-actions>
        <v-layout class="mb-3 mr-3 justify-end">
          <!--
          <v-subheader>
            <v-btn text large color="error">Follower: {{ profileUser.follower }} </v-btn> 
            <v-btn text large color="error">Following: {{ profileUser.following }} </v-btn> 
          </v-subheader>
          -->
          <v-btn  @click="onFollow" dark small color=#F7B675>
            팔로우
          </v-btn>

        </v-layout>
    </v-card>
    <ReviewCard
      v-for="review in reviews"
      :key="review.id"
      :reviewInfo="review"
      @onFollow="onFollow"
    />
  </div>
</template>

<script>
import UserApi from '@/api/UserApi.js';
import ReviewCard from '@/components/review/ReviewCard';

export default {
  name: 'Profile',
  components: {
    ReviewCard
  },
  props: ['userInfo'],
  data() {
    return {
      profileUser: {
        id: "",
        email: "",
        nickname: "",
        follower: 0,
        following: 0,
      },
      reviews: null,
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
            //email을 소개문으로 바꾸고, 글자 수 제한 23개를 두면 될 것 같습니다(한국어가 다르게 처리되면 또 아닐수도)
            this.profileUser.email = "최대 글자 수 제한을 넣어둔 소개문 글자 23!"
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
    fetchReview() {
      // 임시 하드코딩
      this.reviews = [
        {
          content: "임시로 띄우는 리뷰입니다1",
          rank: 3.0,
          image: '',
        },
        {
          content: "임시로 띄우는 리뷰입니다2",
          rank: 4.0,
          image: '',
        },
        {
          content: "임시로 띄우는 리뷰입니다3",
          rank: 5.0,
          image: 'https://images.unsplash.com/photo-1531480197551-16ddf280ba62?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1054&q=80',
        },        {
          content: "임시로 띄우는 리뷰입니다4",
          rank: 2.0,
          image: 'https://images.unsplash.com/photo-1414235077428-338989a2e8c0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
        },
      ]
    }
  },
  created() {
    this.fetchProfile()
    this.fetchReview()
  }
}
</script>

<style>

</style>