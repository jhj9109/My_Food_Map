<template>
  <div>
    <v-card
      :loading="loading"
      class="mx-auto my-0"
      max-width="375"
    >


    <div v-if="restaurantInfo.image" style="text-align: center">
      <img @click="onClick" 
      height="374" 
      width="374"
      :src= "require('@/assets/' + restaurantInfo.image)">
    </div>

    <v-card-title class="pt-2 pb-0" @click="onClick">{{ restaurantInfo.name }}</v-card-title>

    <v-card-text class="pb-1">
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
            size="20"
            empty-icon
          />
          <div class="grey--text ml-1">{{ restaurantInfo.value }} ({{ restaurantInfo.countgrade }})</div>
          <!-- 핀 클릭 -> 북마크 기능 -->
          <v-icon size="24"> mdi-pin </v-icon>
          <div
          v-if="percent > '80'"
          class="title green--text">
            {{ percent }}%일치
          </div>
          <div
          v-else-if="percent > '60'"
          class="title amber--text">
            {{ percent }}%일치
          </div>
          <div
          v-else
          class="title red--text">
            {{ percent }}%일치
          </div>
        </v-row>
        <!-- 레스토랑 정보 : {{ restaurantInfo }} -->
        <!-- 업태명 정보 배치 수정-->
        <!-- 주소 추가 및 디자인 수정 -->
        <div class="subtitle-2 text-right mb-1">
          <template v-for="type in restaurantInfo.res_type">
              {{ type }}
          </template> <br>
        {{ restaurantInfo.doro.slice(0, 18) }} 
        </div>
        <hr>
        <!-- <div>{{ restaurantInfo.content }}</div> -->
      </v-card-text>
      <!-- 영업시간, 메뉴 위치 수정 -->
      <v-card-text class="text-right pt-0 pl-4 pr-4">
        {{ restaurantInfo.time }} <br>
        {{ restaurantInfo.menu }}
      </v-card-text>
    <!-- 리뷰용 버튼 추가, 식당 아이디 보내도록 만들어야 -->
    <div class="text-xs-center">
    <v-btn :to="{ name: 'ReviewCreate' }" class="mb-2 ml-2" fab dark small color="amber">
      <v-icon dark>mdi-pencil</v-icon>
    </v-btn>
    </div>
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
      percent: Math.floor(Math.random() * 100 + 1),
    }),

    methods: {
			onClick(){
        console.log(`Info :${this.restaurantInfo}`)
        console.log(`id :${this.restaurantInfo.idrestaurants} 음식점 페이지로 이동`)
        console.log(this.$router)
        console.log(this.$route)
        this.$router.push({ name: 'RestaurantReview', params: { restaurantId: this.restaurantInfo.idrestaurants}});
        // RestaurantDetail 건너띔
        // if (this.$route.fullPath === "/restaurant") {
        //     this.$router.push({ name: 'RestaurantDetail', params: { restaurantId: this.restaurantInfo.idrestaurants}}); //리스트 => 특정 음식점
        // } else {
        //     this.$router.push({ name: 'RestaurantReview', params: { restaurantId: this.restaurantInfo.idrestaurants}}); //특정 음식점 => 해당 리뷰
        // }
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
<style >
.underbar_gradient {
    background: linear-gradient(270deg, #fa709a, #f6d365 100%);
}
</style>