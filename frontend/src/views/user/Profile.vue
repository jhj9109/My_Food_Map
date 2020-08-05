<template>
  <div>
    <UserProfileCard
      :profileUser="profileUser"
    />
    <ReviewCard
      v-for="review in reviews"
      :key="review.id"
      :reviewInfo="review"
    />
  </div>
</template>

<script>
import UserApi from '@/api/UserApi.js';
import ReviewCard from '@/components/review/ReviewCard';
import UserProfileCard from '@/components/user/UserProfileCard';
import ReviewApi from '@/api/ReviewApi.js';

export default {
  name: 'Profile',
  components: {
    ReviewCard,
    UserProfileCard
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
    fetchProfile() {
      console.log("유저페이지")
      //const userId = this.$route.params.userId
      const userId = "b2@naver.com"//임시
      console.log(userId)
      UserApi.requestUserInfo(
        userId,
        res => {
          console.log("유저 정보 요청 응답 :",res)
          if (res.status === 200) {
            // UserPage 접근 성공
            this.profileUser.id = res.data.userid
            this.profileUser.email = res.data.email
            this.profileUser.nickname = res.data.nickname
            this.profileUser.follower = res.data.follower_cnt
            this.profileUser.following = res.data.following_cnt
            // 리뷰 fetch까지 진행
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
    // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 디자인에 사용할 더미 데이터
    // fetchProfile() {
    //         this.profileUser.id = 1
    //         //email을 소개문으로 바꾸고, 글자 수 제한 23개를 두면 될 것 같습니다(한국어가 다르게 처리되면 또 아닐수도)
    //         this.profileUser.email = "최대 글자 수 제한을 넣어둔 소개문 글자 23!"
    //         this.profileUser.nickname = "my_nickname"
    //         this.profileUser.follower = 1234
    //         this.profileUser.following = 5678
    //         this.fetchReview()
    // },
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
    // onFollow() {
    //   // 테스트용
    //   console.log("팔로우 emit 받음")
    //   this.profileUser.follower += 1
    // },
    fetchReview() {
      const profileUserId = this.$route.params.userId
      console.log("요청 id", profileUserId)
      ReviewApi.requestUserReview(
        profileUserId,
        res => {
          console.log("리뷰 요청 응답", res)
          if (res.data.state === 'ok') {
            this.reviews = res.data.message
            console.log("리뷰 받아오기 성공")
          } else {
            console.log("리뷰 받아오기 실패")
          }
        },
        err => {
          console.error(err)
        }
      )
    },
  },
  //   fetchReview() {
  //     // 테스트용
  //     this.reviews = [
  //       {
  //         content: "임시로 띄우는 리뷰입니다1",
  //         rank: 3.0,
  //         image: '',
  //       },
  //       {
  //         content: "임시로 띄우는 리뷰입니다2",
  //         rank: 4.0,
  //         image: '',
  //       },
  //       {
  //         content: "임시로 띄우는 리뷰입니다3",
  //         rank: 5.0,
  //         image: 'https://images.unsplash.com/photo-1531480197551-16ddf280ba62?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1054&q=80',
  //       },        {
  //         content: "임시로 띄우는 리뷰입니다4",
  //         rank: 2.0,
  //         image: 'https://images.unsplash.com/photo-1414235077428-338989a2e8c0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80',
  //       },
  //     ]
  //   },
  // },
  created() {
    this.fetchProfile()
    this.fetchReview()
  }
}
</script>

<style>

</style>