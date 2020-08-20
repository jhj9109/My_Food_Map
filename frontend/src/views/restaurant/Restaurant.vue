<template>
  <div>
    <!-- 기존 주소 설정 삭제 / 검색 창 추가 -->
      <v-text-field
        v-model="message"        
        v-on:keyup.enter="search"
        solo-inverted
        flat
        hide-details
        style="margin-top: 5px;"
        label="원하는 키워드로 검색해보세요."
      ></v-text-field>  
      <v-spacer></v-spacer>
    <!-- 기존 주소 설정 삭제 / 검색 창 추가 -->
    <RestaurantCard
      v-for="restaurant in restaurants"
      :key="restaurant.idrestaurants"
      :restaurantInfo="restaurant"
      :userInfo="userInfo"
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
      restaurants: null,
      allRestaurants: null,
      loading: true,
      offset: 0,
      complete: true,
      message: '',
    }
  },
  methods: {
    // 아래는 임시 데이터 세팅을 위한 메소드
    setRestaurants() {
      console.log("setRestaurants 요청")
      RestaurantApi.requestList(
        res => {
          this.allRestaurants = res.data.message
          this.restaurants = []
          console.log("레스토랑 전체 데이터 받기 성공", this.allRestaurants)
          this.complete = false
          this.fetchRestaurants()
        },
        err => {
          console.error(err)
          console.log("에러반응")
        }
      )
    },
    fetchRestaurants() {
      const start = this.offset * 5 // 0,1,2,3,4 => 5개 , s = 0 , e = 5
      const end = this.allRestaurants.length <= start + 5 ? this.allRestaurants.length : start + 5
      this.complete = this.allRestaurants.length <= start + 5 ? true : this.complete
      console.log("리뷰 데이터 갱신 요청", this.allRestaurants.slice(start, end), this.complete)
      this.restaurants = [ ...this.restaurants, ...this.allRestaurants.slice(start, end) ]
      this.offset += 1
      this.loading = false
    },
    search() {
            http
                .post('/map/list', {dong: this.message})
                .then(({data}) => {
                    let msg = '동 불러오기에 실패하였습니다.';
                    if (data != null) {
                        this.restaurants=[],
                        this.allRestaurants=[],
                        this.allRestaurants = data;
                        console.log(this.message)

                        this.fetchRestaurants();
                    } else {
                        alert(msg);
                    }
                }
              );
   },
  },
  watch: {
    isScrollEnd: function(val) {
      console.log("스크롤엔드 감지 :", val, !this.complete, this.loading)
      if (val && !this.complete && !this.loading) {
        this.loading = true
        this.fetchRestaurants()
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