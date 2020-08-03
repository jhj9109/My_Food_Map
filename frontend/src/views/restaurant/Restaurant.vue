<template>
  <div>
    <!-- 기존 주소 설정 삭제 / 검색 창 추가 -->
      <v-text-field
        solo-inverted
        flat
        hide-details
        style="margin-top: 5px;"
        label="맛집 혹은 유저 정보를 검색해보세요."
      ></v-text-field>  
      <v-spacer></v-spacer>
    <!-- 기존 주소 설정 삭제 / 검색 창 추가 -->
    <RestaurantCard
      v-for="restaurant in restaurants"
      :key="restaurant.id"
      :restaurantInfo="restaurant"
    />
  </div>

</template>

<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard';

// import { mapState, mapMutations } from 'vuex';

export default {
  name: 'Restaurant',
  components: {
    RestaurantCard,
  },
  data() {
    return {
      restaurants: [],
    }
  },
  methods: {
    // 아래는 임시 데이터 세팅을 위한 메소드
    tempSetData() {
      this.restaurants = [
        {
          id: 1,
          title: "어썸한 가게",
          content: "어썸한 우리가게로 오세요!!!",
          imgUrl: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
          types: ["이탈리안","레스토랑"],
          rank: 4.5,
          likeCount: 404,
          deal_date: "10:00 ~ 21:00"
        },
        {
          id: 2,
          title: "어썸한 두번째 가게",
          content: "어썸한 우리가게로 오세요!!!",
          imgUrl: "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80",
          types: ["이탈리안","레스토랑"],
          rank: 4.7,
          likeCount: 500,
          deal_date: "10:00 ~ 21:00"
        },
      ]
    },
  },
  mounted() {
    // 아래의 임시 데이터 세팅 메소드 대신, 실제 데이터 세팅 메소드 넣으면 됌
    this.tempSetData()
  }
}
</script>
<style>

</style>