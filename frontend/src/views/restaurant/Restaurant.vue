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
      :isScrollEnd=isScrollEnd
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
  props: ['isScrollEnd'],
  data() {
    return {
      restaurants: [],
      allRestaurants: [],
      loading: true,
      offset: 0,
    }
  },
  methods: {
    // 아래는 임시 데이터 세팅을 위한 메소드
    setRestaurants() {
      console.log("setRestaurants 요청")
      RestaurantApi.requestList(
        res => {
          // const start = this.offset*5
          // const end = start + 5
          // const newArray = res.data.message.slice(start, end)
          // console.log(`setRestaurants 콜백 성공 ${start}~${end}, 5개 슬라이싱`, newArray)
          // this.restaurants = [ ...this.restaurants, ...newArray ]
          // this.offset += 1
          // this.loading = false
          this.allRestaurants = res.data.message
          console.log("레스토랑 전체 데이터 받기 성공", this.allRestaurants)
          this.fetchRestaurants()
        },
        err => {
          console.error(err)
          console.log("에러반응")
        }
      )
    },
    fetchRestaurants() {
      console.log("레스토랑 데이터 갱신 요청", this.allRestaurants.slice(start, end))
      const start = this.offset * 5
      const end = start + 4
      const newArray = this.allRestaurants.slice(start, end)
      // console.log(`fetchRestaurants 대상은 ${start}~${end}, 5개 슬라이싱`, newArray)
      this.restaurants = [ ...this.restaurants, ...newArray ]
      this.offset += 1
      this.loading = false
    }
  },
  watch: {
    isScrollEnd: function(val) {
      // console.log("스크롤엔드 감지 :", val, this.loading)
      if (val && !this.loading) {
        this.loading = true
        // console.log("데이터 로딩 중", this.loading)
        this.fetchRestaurants()
      } else {
        // console.log("지나간다")
      }
    }
  },
  mounted() {
    this.setRestaurants()
  }
}
</script>
<style>

</style>