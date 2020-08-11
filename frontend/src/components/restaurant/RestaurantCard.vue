<template>
  <div>
    <v-card
      :loading="loading"
      class="mx-auto my-0"
      max-width="375"
    >
      <v-img
          v-if="restaurantInfo.image"
          @click="onClick"
          height="250"
          :src="restaurantInfo.image"
      ></v-img>

      <v-card-title class="pt-2 pb-0" @click="onClick">{{ restaurantInfo.name }}</v-card-title>

      <v-card-text class="pb-2">
        <v-row
          align="center"
          class="mx-0"
        >

          <v-rating
            :value="restaurantInfo.grade"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          />
          <div class="grey--text ml-1">{{ restaurantInfo.value }} ({{ restaurantInfo.countgrade }})</div>
        </v-row>

        <!-- 업태명 정보 배치 수정-->
        <div class="subtitle-1 text-right">
          <template v-for="type in restaurantInfo.res_type">
              {{ type }}
          </template>
        </div>

        <!-- <div>{{ restaurantInfo.content }}</div> -->
      </v-card-text>
      <!-- 영업시간, 메뉴 위치 수정 -->
      <v-card-text class="text-right pt-0">
        {{ restaurantInfo.time }} <br>
        {{ restaurantInfo.menu }}
      </v-card-text>
    </v-card>
    <div class="text-right">
    </div>
  </div>
</template>

<script>
import RestaurantApi from '@/api/RestaurantApi.js'
  export default {
    name: "RestaurantCard",
    // props: ['id', 'title', 'content', 'imgUrl', 'types', 'rank', 'likeCount', 'deal_date'],
    props: ['restaurantInfo'],
    data: () => ({
      loading: false,
    }),

    methods: {
			onClick(){
        console.log(`Info :${this.restaurantInfo}`)
        console.log(`id :${this.restaurantInfo.idrestaurants} 음식점 페이지로 이동`)
        console.log(this.$router)
        console.log(this.$route)
        if (this.$route.fullPath === "/restaurant") {
            this.$router.push({ name: 'RestaurantDetail', params: { restaurantId: this.restaurantInfo.idrestaurants}}); //리스트 => 특정 음식점
        } else {
            this.$router.push({ name: 'RestaurantReview', params: { restaurantId: this.restaurantInfo.idrestaurants}}); //특정 음식점 => 해당 리뷰
        }
      },
      // 미리 작성해 놓은 Like
      // onLike(){
      //   const data = {
      //     userId: this.$store.state.user.userId,
      //     restaurantId: this.restaurantId,
      //   }
      //   RestaurantApi.requestLike(
      //     data,
      //     res => {
      //       if (res === "좋아요") {
      //         this.restaurantInfo.likeCount += 1
      //         console.log("좋아요+1")
      //       } else {
      //         if (res === "좋아요취소") {
      //           this.restaurantInfo.likeCount -= 1
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
      // }
    },
  }
</script>
<style>

</style>