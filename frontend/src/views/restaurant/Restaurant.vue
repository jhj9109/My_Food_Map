<template>
  <div>
    <RestaurantCard
      v-for="restaurant in restaurants"
      :key="restaurant.idrestaurants"
      :restaurantInfo="restaurant"
    />
    <RestaurantCard
      :restaurantInfo="resta"
    />
      {{resta}}
  </div>

</template>

<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard';
import RestaurantApi from '@/api/RestaurantApi.js'
// import { mapState, mapMutations } from 'vuex';

export default {
  name: 'Restaurant',
  components: {
    RestaurantCard,
  },
  data() {
    return {
      restaurants: [],
      resta: {},
    }
  },
  methods: {
    // 아래는 임시 데이터 세팅을 위한 메소드
    tempSetData() {
      this.restaurants = [
        {
          idrestaurants: 111,
          name: "어썸한 가게",
          // content: "어썸한 우리가게로 오세요!!!",
          imgUrl: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
          res_type: ["이탈리안","레스토랑"],
          grade: 4.5,
          countgrade: 404,
          time: "10:00 ~ 21:00"
        },
        {
          idrestaurants: 222,
          name: "어썸한 가게",
          // content: "어썸한 우리가게로 오세요!!!",
          imgUrl: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
          res_type: ["이탈리안","레스토랑"],
          grade: 4.5,
          countgrade: 404,
          time: "10:00 ~ 21:00"
        },
        // {
        //   id: 2,
        //   title: "어썸한 두번째 가게",
        //   content: "어썸한 우리가게로 오세요!!!",
        //   imgUrl: "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80",
        //   types: ["이탈리안","레스토랑"],
        //   rank: 4.7,
        //   likeCount: 500,
        //   deal_date: "10:00 ~ 21:00"
        // },
      ]
    },
    realSetData() {
      console.log("realSetData 요청")
      RestaurantApi.requestList(
        res => {
          console.log("realSetData 콜백 성공")
          this.resta = res.data.message[0]
          console.log(this.resta)
        },
        err => {
          console.error(err)
          console.log("에러반응")
        }
      )
    }
  },
  mounted() {
    // 아래의 임시 데이터 세팅 메소드 대신, 실제 데이터 세팅 메소드 넣으면 됌
    this.tempSetData()
    this.realSetData()
  }
}
</script>
<style>

</style>