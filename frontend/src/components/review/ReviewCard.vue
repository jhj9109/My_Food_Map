<template>
  <v-card
    :loading="loading"
    class="mx-auto my-3"
    max-width="374"
    elevation=24
  > 
  <!-- 이미지 사이즈에 따라 유동적 사이즈 설정, 정사각형으로 보이도록 -->
  <p>{{ reviewInfo }}</p>
    <v-img
      class="white--text align-end"
      @click="onClick" 
      v-if="reviewInfo.image !== 'null'"
      max-height="374"
      :src="reviewInfo.image">
      <!-- <v-card-title @click="toRestaurant" >파스타집</v-card-title> -->
    </v-img>
    
    <v-card-text class="pl-3">
      <!-- 추후 식당 이름으로 바꿔야할 부분, 식당 이름이 넘어오지 않아 수정 못함 -->      <!-- 개인 페이지에서는 식당, 식당 페이지에서는 리뷰를 보여줘야할듯 -->
<<<<<<< HEAD
      <v-btn @click="onLike" icon class="float-right mr-3">
        <v-icon :color="reviewInfo.like ? 'red' : ''">mdi-heart</v-icon> {{ reviewInfo.like_cnt }}
      </v-btn>
      <v-icon large color="blue darken-2" class="float-right mr-3">mdi-message-text</v-icon>
=======
      <div class="float-right">
        <v-btn @click="onLike" icon class="mr-3">
            <v-icon :color="reviewInfo.like ? 'red' : ''">mdi-heart</v-icon> {{ reviewInfo.like_cnt }}
        </v-btn>
        <v-btn icon class="mr-3">
          <v-icon color="dark darken-2">mdi-message-text</v-icon> {{ reviewInfo.comment_cnt }}
        </v-btn>
      </div>
>>>>>>> c2d91aa216ba79f6b2b90dd1ecc87027aa075071
      <div @click="toRestaurant" v-if="!showNickname" class="ml-1 title">
        {{ reviewInfo.resname }}<br>
      </div>
      <v-row justify="start">
        <div @click="toProfile" v-if="showNickname" class="sub-title mt-0">
<<<<<<< HEAD
          <v-avatar size="40" style="margin:5px;">
              <v-img :src="reviewInfo.user_image"></v-img>
           </v-avatar>{{ reviewInfo.nickname }}<br>
=======
          <v-list>
            <v-list-item>
              <v-list-item-avatar>
                <v-img :src="reveiwInfo.user_image"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-text="reviewInfo.nickname"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
>>>>>>> c2d91aa216ba79f6b2b90dd1ecc87027aa075071
        </div>
      </v-row>

      <v-row align="end">
      <!-- 디자인 수정 및 백그라운드 색상 설정 -->
        <v-rating
          class="ml-0 mb-1 mt-1 mr-1"
          :value="reviewInfo.reviewrank"
          color="amber"
          dense
          half-increments
          readonly
          size="20"
          empty-icon="mdi-star-outline"/>
        {{ reviewInfo.create_date }}
      </v-row>

      <div
        @click="onClick"
        class="ml-1 mt-1"
      >
      <!-- 리뷰 콘텐츠 수정 + 사용자명(nickname 넘어오지 않아 못넣음), 작성일-->
        {{ reviewInfo.content }} <br>
      </div>
      <div class="text-right">
        
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
      showNickname: false,
    }),
    methods: {
			onClick(){
        // 클릭시 모달 => 도움이 됐어요 or 리뷰 디테일
        // 리뷰 디테일
        this.$router.push({ name: 'ReviewDetail', params: { reviewId: this.reviewInfo.no}});
      },
      toProfile() {
        // console.log("toProfile 발동")
        this.$router.push({name : 'Profile', params : {nickname : this.reviewInfo.nickname}})
      },
      toRestaurant() {        
        this.$router.push({ name: 'RestaurantReview', params: { restaurantId: this.reviewInfo.resid}});
      },
      // 미리 작성해 놓은 Like
      onLike(){
        const data = {
          userid: this.$store.state.user.userInfo.userId,
          reviewid: this.reviewInfo.no,
        }
        ReviewApi.requestLike(
          data,
          res => {
            if (res.data.message === "Like -1") {
              this.reviewInfo.like_cnt -= 1
              this.reviewInfo.like = false;
              // console.log("좋아요-1")
            } else {
              if (res.data.message === "Like +1") {
                this.reviewInfo.like_cnt += 1
                this.reviewInfo.like = true;
                // console.log("좋아요+1")
              } else {
                // console.log("좋아요 요청 실패, res : ", res)
              }
            }
          },
          err => {
            // console.error(err)
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
    mounted() {
      this.showNickname = (this.$route.name === 'Profile' ||  this.$route.name === 'MyProfile') ? false : true
    }
  }
</script>
<style>

</style>