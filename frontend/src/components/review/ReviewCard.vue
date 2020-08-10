<template>
  <v-card
    :loading="loading"
    class="mx-auto my-3"
    max-width="374"
  >
    <v-img
      v-if="reviewInfo.image"
      height="250"
      :src="reviewInfo.image"
    />
    
    <v-card-text>

      <div class="float-right">
        <v-row
          align="center"
          class="ml-3"
        >
          <v-rating
            :value="reviewInfo.rank"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          />
        </v-row>
      </div>
      <div> {{ reviewInfo.content }}</div>
    
    </v-card-text>
  </v-card>
</template>

<script>
import ReviewApi from '@/api/ReviewApi.js'
import UserApi from '@/api/UserApi.js'
  export default {
    name: "ReviewCard",
    props: ['reviewInfo'],
    data: () => ({
      loading: false,
    }),

    methods: {
			onClick(){
        // 클릭시 모달 => 도움이 됐어요
        console.log("onClick 발동")
        // console.log(`id :${this.restaurantInfo.id} 음식점 페이지로 이동`)
        // this.$router.push({ name: 'RestaurantDetail', params: { restaurantId: this.restaurantInfo.id}}); //리뷰 => 리뷰 디테일?
      },
      // 미리 작성해 놓은 Like
      // onLike(){
      //   const data = {
      //     userId: this.$store.state.user.userId,
      //     reviewId: this.reviewInfo.reviewId,
      //   }
      //   ReviewApi.requestLike(
      //     data,
      //     res => {
      //       if (res === "좋아요") {
      //         this.reviewInfo.likeCount += 1
      //         console.log("좋아요+1")
      //       } else {
      //         if (res === "좋아요취소") {
      //           this.reviewInfo.likeCount -= 1
      //           console.log("좋아요-1")
      //         } else {
      //           console.log("좋아요 요청 실패, res : ", res)
      //         }
      //       }
      //     },
      //     err => {
      //       console.error(err)
      //     }
      //   )
      // },
      // 미리 작성한 Follow
      // onFollow() {
      //   console.log('팔로우', this.userInfo.userId, this.profileUser.id)
      //   UserApi.requestFollow(
      //     {
      //       followerId: this.$store.state.user.userId,
      //       followingId: this.reviewInfo.userId,
      //       no: 0
      //     },
      //     res => {
      //       if (res.data.message === "Following -1") {
      //         this.reviewInfo.follower -= 1
      //         console.log("팔로워 숫자 -1")
      //       } else {
      //         if (res.data.message === "Following +1") {
      //           this.reviewInfo.follower += 1
      //           console.log("팔로워 숫자 +1")
      //         } else {
      //           // 성공외 다른 응답이 왔을때 동작
      //           console.log("팔로우 실패", res)
      //         }
      //       }
      //     },
      //     err => {
      //       console.error(err)
      //       // 라우팅 하지 않음
      //     }
      //   )
      // },
    },
  }
</script>
<style>

</style>