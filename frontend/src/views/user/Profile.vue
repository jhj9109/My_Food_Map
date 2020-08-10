<template>
  <div>
    <UserProfileCard
      :profileUser="profileUser"
      @onFollow="onFollow"
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
  props: ['userInfo', 'isScrollEnd'],
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
      allReviews: [],
      loading: true,
      offset: 0,
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
    setReviews() {
      const profileUserId = this.$route.params.userId
      console.log("요청 id", profileUserId)
      ReviewApi.requestUserReview(
        profileUserId,
        res => {
          console.log("리뷰 요청 응답", res)
          if (res.data.state === 'ok') {
            this.allReviews = res.data.message
            console.log("리뷰 받아오기 성공")
            this.fetchReviews()
          } else {
            console.log("리뷰 받아오기 실패")
          }
        },
        err => {
          console.error(err)
        }
      )
    },
    fetchReviews() {
      const start = this.offset * 10
      const end = start + 9
      console.log("리뷰 데이터 갱신 요청", this.allReviews.slice(start, end))
      const newArray = this.allReviews.slice(start, end)
      // console.log(`fetchRestaurants 대상은 ${start}~${end}, 5개 슬라이싱`, newArray)
      this.reviews = [ ...this.allReviews, ...newArray ]
      this.offset += 1
      this.loading = false
    },
    onFollow() {
      console.log('팔로우', this.userInfo.userId, this.profileUser.id)
      UserApi.requestFollow(
        {
          followerId: this.userInfo.userId,
          followingId: this.profileUser.id,
          no: 0
        },
        res => {
          if (res.data.message === "Following -1") {
            this.profileUser.follower -= 1
            console.log("팔로워 숫자 -1")
          } else {
            if (res.data.message === "Following +1") {
              this.profileUser.follower += 1
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
  watch: {
    isScrollEnd: function(val) {
      // console.log("스크롤엔드 감지 :", val, this.loading)
      if (val && !this.loading) {
        this.loading = true
        // console.log("데이터 로딩 중", this.loading)
        this.fetchReviews()
      } else {
        // console.log("지나간다")
      }
    }
  },
  created() {
    this.fetchProfile()
    this.setReviews()
  }
}
</script>

<style>

</style>