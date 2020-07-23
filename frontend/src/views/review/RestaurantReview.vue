<template>
  <div>
    <v-card
      v-for="review in reviews"
      :loading="loading"
      class="mx-auto my-3"
      max-width="374"
    >
      <v-img
        v-if="review.imgUrl"
        height="250"
        :src="review.imgUrl"
      ></v-img>

      <v-card-text>

        <div class="float-right">
          <v-row
            align="center"
            class="ml-3"
          >
            <v-rating
              :value="review.rank"
              color="amber"
              dense
              half-increments
              readonly
              size="14"
            ></v-rating>
          </v-row>
        </div>

        <div> {{ review.content }}</div>
      </v-card-text>
    </v-card>
    
  </div>
</template>

<script>
  export default {
    name: "RestaurantReview",
    data: () => ({
      loading: false,
      reviews: [
        {
          imgUrl: "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665__340.jpg",
          rank: 4.5,
          content: "이곳 가게 이름답게 어썸한 가게 인정? 인정"
        },
        {
          imgUrl: "",
          rank: 1.0,
          content: "위에 리뷰에 속았다. 분하다 여러분 속지 마세요. 이 가게 매우 어글리하고 맛없는 가게임!"
        },
        {
          imgUrl: "https://cdn.pixabay.com/photo/2016/10/22/20/34/wine-1761613__340.jpg",
          rank: 2.0,
          content: "이곳 가게 이름답게 어썸한 가게 인정? 인정"
        },
        {
          imgUrl: "https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466__340.jpg",
          rank: 1.0,
          content: "위에 리뷰에 속았다. 분하다 여러분 속지 마세요. 이 가게 매우 어글리하고 맛없는 가게임!"
        },
      ]
    }),

    methods: {
    },
  }
</script>
<style>

</style>