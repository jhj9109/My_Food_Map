<template>
  <v-card
    :loading="loading"
    class="mx-auto my-3"
    max-width="374"
  > 
  <!-- 이미지 사이즈에 따라 유동적 사이즈 설정, 정사각형으로 보이도록 -->
    <v-img
      v-if="reviewInfo.image !== 'null'"
      height="374"
      :src="reviewInfo.image"
      @click="onClick"
    />
    
    <v-card-text>
      <!-- 추후 식당 이름으로 바꿔야할 부분, 식당 이름이 넘어오지 않아 수정 못함 -->
      <div class="title">
      {{ reviewInfo.resid }} 번 식당, 이름이 보여질 자리
      </div>
          <v-btn
            icon
            @click="onLike"
            class="float-right mr-3"
          >
            {{ reviewInfo.like_cnt }} like
            <v-icon>mdi-heart</v-icon>
          </v-btn>
        <v-row
          align="center" 
        >
        <!-- 디자인 수정 및 백그라운드 색상 설정 -->
          <v-rating
            class="ml-0 mb-1 mt-1"
            :value="reviewInfo.rank"
            color="amber"
            dense
            half-increments
            readonly
            size="20"
            empty-icon
          />
        </v-row>
      </div>
      <div
        @click="onClick"
        class="mt-2"
      >
      <!-- 리뷰 콘텐츠 수정 + 사용자명(nickname 넘어오지 않아 못넣음), 작성일-->
        {{ reviewInfo.content }} <br>
      </div>
      <div class="text-right">
        {{ reviewInfo.userid }}번 유저 님의 리뷰 <br>
        {{ reviewInfo.create_date }}
      </div>
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
        console.log(this.reviewInfo)
        // console.log(`id :${this.restaurantInfo.id} 음식점 페이지로 이동`)
        // this.$router.push({ name: 'RestaurantDetail', params: { restaurantId: this.restaurantInfo.id}}); //리뷰 => 리뷰 디테일?
      },
      // 미리 작성해 놓은 Like
      onLike(){
        const data = {
          userId: this.$store.state.user.userInfo.userId,
          reviewId: this.reviewInfo.no,
        }
        ReviewApi.requestLike(
          data,
          res => {
            if (res === "좋아요") {
              this.reviewInfo.likeCount += 1
              console.log("좋아요+1")
            } else {
              if (res === "좋아요취소") {
                this.reviewInfo.likeCount -= 1
                console.log("좋아요-1")
              } else {
                console.log("좋아요 요청 실패, res : ", res)
              }
            }
          },
          err => {
            console.error(err)
          }
        )
      },
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