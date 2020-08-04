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
      :key="restaurant.idrestaurants"
      :restaurantInfo="restaurant"
    />
  </div>

</template>

<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard';
import RestaurantApi from '@/api/RestaurantApi.js'

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
    setRestaurants() {
      console.log("setRestaurants 요청")
      RestaurantApi.requestList(
        res => {
          console.log("setRestaurants 콜백 성공, 5개 슬라이싱", res.data.message.slice(0,5))
          this.restaurants = res.data.message.slice(0,5)
        },
        err => {
          console.error(err)
          console.log("에러반응")
        }
      )
    }
  },
  mounted() {
    this.setRestaurants()
  }
}
</script>
<style>

</style>